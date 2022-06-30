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
public class PostsController{
    private final PostsRepository postsRepository;
    private final PostsService postsService;
    private final JwtTokenProvider tokenProvider;
    @Autowired
    PostsRepository lRepo;

    @PostMapping("/user/posts")
    public HttpStatus createPosts(HttpServletRequest request) {
        String username = tokenProvider.getUserPk(request.getHeader("X-AUTH-TOKEN"));
        Posts posts = new Posts(username);
        postsRepository.save(posts);
        return (HttpStatus.OK);
    }
    @GetMapping("/user/posts?orderBy=like")
    public ResponseEntity<List<Posts>>readPostsByUsername() {
        return new ResponseEntity<List<Posts>>(postsRepository.findByOrderByLikesAtDesc(),HttpStatus.OK);
    }
    @GetMapping("/user/posts/{id}")
    public ResponseEntity<List<Posts>> getPostsByUsername(@AuthenticationPrincipal Member member){
        return new ResponseEntity<List<Posts>>(lRepo.findByUsername(member.getUsername()), HttpStatus.OK);
    }
    @PutMapping("/user/posts/{id}")
    public HttpStatus updatePosts(@PathVariable int id, @RequestBody PostsRequestDto requestDto, @AuthenticationPrincipal Member member) {
        List<Posts> posts = postsRepository.findByUsername(member.getUsername());
        Long updateId = posts.get(id).getId();
        postsService.update(updateId, requestDto);
        return (HttpStatus.OK);
    }
    @DeleteMapping("/user/posts/{id}")
    public HttpStatus deletePosts(@PathVariable int id, @AuthenticationPrincipal Member member){
        List<Posts> posts = postsRepository.findByUsername(member.getUsername());
        Long deleteId = posts.get(id).getId();
        postsRepository.deleteById(deleteId);
        return (HttpStatus.OK);
    }
}