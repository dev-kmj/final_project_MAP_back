package com.sparta.finalprojectback.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;


    // 프론트에서 인가코드를 받아오는 API
    // 일단 print로 확인 완료 (코드, 토큰 둘 다 출력)
    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) {
//        System.out.println("Authorized code : " + code);
//        String accessToken = kakaoOAuth.getAccessToken(code);
//        System.out.println("accessToken: " + accessToken);
        return kakaoLoginService.kakaoLogin(code);
    }
}