package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.model.Category;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import com.sparta.finalprojectback.post.repository.PostRepository;
import com.sparta.finalprojectback.postComment.repository.PostCommentRepository;
import com.sparta.finalprojectback.s3.service.FileService;
import com.sparta.finalprojectback.schedule.repository.ScheduleRepository;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
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

    private final PostCommentRepository postCommentRepository;
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
                .views(0)
                .build()).getId();

        return new ResponseEntity<>(createdPostId, HttpStatus.valueOf(StatusCode.CREATED));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deletePost(Member member, Long postId) {

        // 나중에 수정할 부분
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );
        if(post.getMember().getId() != member.getId()){
            return new ResponseEntity<>("유저가 생성한 게시물만 삭제할 수 있습니다.", HttpStatus.valueOf(StatusCode.BAD_REQUEST));
        }
        scheduleRepository.deleteAllByPost_Id(postId);
        postCommentRepository.deleteAllByPost_Id(postId);
        fileService.deleteImage(postId);
        postRepository.deleteById(postId);


        return new ResponseEntity<>(ResponseMessage.DELETE_POST, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    @Transactional
    public ResponseEntity<String> updatePost(Member member, Long postId,  PostRequestDto requestDto) {

        Post targetPost = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디입니다.")
        );

        if(targetPost.getMember().getId() != member.getId()){
            return new ResponseEntity<>("유저가 생성한 게시물만 수정할 수 있습니다.", HttpStatus.valueOf(StatusCode.BAD_REQUEST));
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
        }else if(requestDto.getCategory() == "attraction"){//        ATTRACTION 관광지
            category = Category.ATTRACTION;
        }else if(requestDto.getCategory() == "date"){ //        DATE 데이트
            category = Category.ATTRACTION;
        }

        targetPost.updatePost(requestDto.getTitle(), category, requestDto.getPeriod(), true);
        return new ResponseEntity<>(ResponseMessage.UPDATE_POST, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    public ResponseEntity<List<PostResponseDto>> readMyPost(Member member) {
        List<Post> myPosts = postRepository.findAllByMember_Id(member.getId());
        return getListResponseEntity(myPosts);
    }

    @Override
    public ResponseEntity<List<PostResponseDto>> readAllPost(boolean isComplete) {

        // isComplete 이용해서 작성완료된 게시물만 반환
        List<Post> allPosts = postRepository.findPostsByIsComplete(!isComplete);

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
                    post.getCreatedAt(),
                    post.getPeriod(),
                    post.getImage(),
                    post.getLikes(),
                    post.getViews()));

        }
        return new ResponseEntity<>(responseDtoList, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    @Transactional
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시물이 없습니다."));
    }

    @Override
    public ResponseEntity<PostResponseDto> readPostInfo(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () ->  new IllegalArgumentException("해당하는 게시물이 없습니다.")
        );
        return new ResponseEntity<>(
                new PostResponseDto(post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCategory(),
                        post.getMember().getNickname(),
                        post.getCreatedAt(),
                        post.getPeriod(),
                        post.getImage(),
                        post.getLikes(),
                        post.getViews()),
                HttpStatus.valueOf(StatusCode.OK)
        );
    }
    @Override
    @Transactional
    public ResponseEntity<PostResponseDto> countingView(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () ->  new IllegalArgumentException("해당하는 게시물이 없습니다.")
        );
        post.countingView(1);
        return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.OK));
    }
}
