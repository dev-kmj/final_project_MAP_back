package com.sparta.finalprojectback.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    Long kakaoId;
    String email;
    String nickname;
}