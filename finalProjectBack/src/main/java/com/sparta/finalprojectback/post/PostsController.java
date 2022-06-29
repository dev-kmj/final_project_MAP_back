package com.sparta.finalprojectback.post;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsRepository postsRepository;
    private final PostsService postsService;
    private final JwtTokenProvider tokenProvider;
    String username;
    @Autowired
    PostsRepository lRepo;

    @PostMapping("/trip/user/posts")
    public HttpStatus createPosts(HttpServletRequest request, @RequestBody PostsRequestDto requestDto) {
        username = tokenProvider.getUserPk(request.getHeader("X-AUTH-TOKEN"));
        Posts posts = new Posts(requestDto, username);
        postsRepository.save(posts);
        return (HttpStatus.OK);
    }

    @GetMapping("/trip/user/posts")
    public ResponseEntity<List<Posts>>readPosts() {
        return new ResponseEntity<List<Posts>>(postsRepository.findAllByOrderByModifiedAtDesc(),HttpStatus.OK);
    }

    @PutMapping("/trip/posts/{index}")
    public HttpStatus updatePosts(@PathVariable int index, @RequestBody PostsRequestDto requestDto, @AuthenticationPrincipal Member member) {
        List<Posts> posts = postsRepository.findByUsername(member.getUsername());
        Long updateId = posts.get(index).getId();
        postsService.update(updateId, requestDto);
        return (HttpStatus.OK);
    }
    @DeleteMapping("/trip/posts/{index}")
    public HttpStatus deletePosts(@PathVariable int index, @AuthenticationPrincipal Member member){
        List<Posts> posts = postsRepository.findByUsername(member.getUsername());
        Long deleteId = posts.get(index).getId();
        postsRepository.deleteById(deleteId);
        return (HttpStatus.OK);
    }

    @GetMapping("/trip/mypage/posts")
    public ResponseEntity<List<Posts>> getPostsByUsername(@AuthenticationPrincipal Member member){
        return new ResponseEntity<List<Posts>>(lRepo.findByUsername(member.getUsername()), HttpStatus.OK);
    }
}