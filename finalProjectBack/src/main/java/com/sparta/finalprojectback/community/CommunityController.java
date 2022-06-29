package com.sparta.finalprojectback.community;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommunityController {

    private final CommunityRepository communityRepository;
    private final CommunityService communityService;
    private final JwtTokenProvider tokenProvider;

    // 변수로 선언
    String username;


    // == 상태코드 반환하도록 수정중 ==//

    // 커뮤니티 게시물 작성
    @PostMapping("/trip/user/community")
    public HttpStatus createCommunity(HttpServletRequest request, @RequestBody CommunityRequestDto requestDto) {
        username = tokenProvider.getUserPk(request.getHeader("X-AUTH-TOKEN"));
        Community community = new Community(requestDto, username);
        communityRepository.save(community);
        return (HttpStatus.OK);
    }

    /* 읽기 */
    // 커뮤니티 게시물 목록 불러오기
    @GetMapping("/trip/user/community")
    public List<Community> getCommunityList() {
        return communityRepository.findAllByOrderByCreatedAtDesc();
    }


    // 내가 쓴 게시물만 불러오기
    @GetMapping("/trip/user/mypage/community")
    public List<Community> getMyCommunityList() {
        return communityRepository.findByUsername(username);
    }


    // 커뮤니티 게시물 수정
    @PutMapping("/trip/user/community/{index}")
    public HttpStatus updateCommunity(@PathVariable int index, @AuthenticationPrincipal Member member, @RequestBody CommunityRequestDto requestDto) {
        List<Community> communities = communityRepository.findByUsername(member.getUsername());
        Long updateId = communities.get(index).getId();
        communityService.updateCommunity(updateId, requestDto);
        return (HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/trip/user/community/{index}")
    public HttpStatus deleteCommunity(@PathVariable int index, @AuthenticationPrincipal Member member) {
        List<Community> communities = communityRepository.findByUsername(member.getUsername());
        Long deleteId = communities.get(index).getId();
        communityService.deleteCommunity(deleteId);
        return (HttpStatus.OK);
    }

}
