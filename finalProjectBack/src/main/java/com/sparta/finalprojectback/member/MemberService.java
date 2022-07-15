package com.sparta.finalprojectback.member;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public Long join(MemberJoinRequestDto requestDto);
    public String login(MemberLoginRequestDto requestDto);
    public MemberResponseDto myInfo(Member member);
    ResponseEntity<List<MemberResponseDto>> findUser();
    ResponseEntity<String> deleteUser(Member member, Long memberId);
    ResponseEntity<String> findOverlapUsername(String username);
    ResponseEntity<String> findOverlapNickname(String nickname);
    ResponseEntity<String> findOverlapEmail(String email);
    ResponseEntity<String> modifyUser(Member member, MemberUpdateRequestDto requestDto);
}
