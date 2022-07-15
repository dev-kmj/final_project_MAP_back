package com.sparta.finalprojectback.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;


    // 프론트에서 인가코드를 받아오는 API
    // 일단 print로 확인 완료 (코드, 토큰 둘 다 출력)
    @GetMapping("/auth/kakao/callback")
    public void kakaoCallback(@RequestParam String code) {
//        System.out.println("Authorized code : " + code);
//        String accessToken = kakaoOAuth.getAccessToken(code);
//        System.out.println("accessToken: " + accessToken);
        kakaoLoginService.kakaoLogin(code);
    }




}