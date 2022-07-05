package com.sparta.finalprojectback.member;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public Long join(MemberJoinRequestDto requestDto);
    public String login(MemberLoginRequestDto requestDto);
    ResponseEntity<List<MemberResponseDto>> findUser();
    ResponseEntity<String> deleteUser(Member member, Long memberId);
}
