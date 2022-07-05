package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.member.MemberRepository;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.model.Category;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import com.sparta.finalprojectback.post.repository.PostRepository;
import com.sparta.finalprojectback.s3.service.FileService;
import com.sparta.finalprojectback.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ScheduleRepository scheduleRepository;
    private final FileService fileService;

    @Override
    public ResponseEntity<Long> createPost(Member member) {
        Long createdPostId = postRepository.save(Post.builder()
                .title("입력 없음")
                .image("입력 없음")
                .category(null)
                .likes(0)
                .content("입력 없음")
                .isComplete(false)
                .period(0)
                .member(member)
                .build()).getId();

        return new ResponseEntity<>(createdPostId, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deletePost(Member member, Long postId) {

        // 나중에 수정할 부분
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );
        if(post.getMember().getId() != member.getId()){
            return new ResponseEntity<>("유저가 생성한 게시물만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST);
        }
        scheduleRepository.deleteAllByPost_Id(postId);
        fileService.deleteImage(postId);
        postRepository.deleteById(postId);


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updatePost(Member member, Long postId,  PostRequestDto requestDto) {

        Post targetPost = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디입니다.")
        );

        if(targetPost.getMember().getId() != member.getId()){
            return new ResponseEntity<>("유저가 생성한 게시물만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST);
        }

        Category category = null;
        if(requestDto.getCategory() == "food"){ //        FOOD 맛집투어
            category = Category.FOOD;
        }else if(requestDto.getCategory() == "healing"){ //        HEALING 힐링여행
            category = Category.HEALING;
        }else if(requestDto.getCategory() == "scenery"){ //        SCENERY 풍경
            category = Category.SCENERY;
        }else if(requestDto.getCategory() == "cafe"){ //        CAFE 카페투어
            category = Category.CAFE;
        }else{                              //        ATTRACTION 관광지
            category = Category.ATTRACTION;
        }

        targetPost.updatePost(requestDto.getTitle(), category, requestDto.getContent(), true);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostResponseDto>> readMyPost(Member member) {
        List<Post> myPosts = postRepository.findAllByMember_Id(member.getId());
        return getListResponseEntity(myPosts);
    }

    @Override
    public ResponseEntity<List<PostResponseDto>> readAllPost() {
        List<Post> allPosts = postRepository.findAll();
        return getListResponseEntity(allPosts);
    }

    @NotNull
    private ResponseEntity<List<PostResponseDto>> getListResponseEntity(List<Post> allPosts) {
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : allPosts) {
            responseDtoList.add(new PostResponseDto(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCategory(),
                    post.getMember().getNickname(),
                    post.getCreatedAt()));

        }
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
}
