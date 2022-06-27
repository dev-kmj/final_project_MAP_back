package com.sparta.finalprojectback.member;

public interface MemberService {

    public Long join(MemberJoinRequestDto requestDto);
    public String login(MemberLoginRequestDto requestDto);
}
