package com.sparta.finalprojectback.kakao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "카카오 기능")
@RequiredArgsConstructor
@RestController
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;
    @ApiOperation("카카오 로그인")
    @GetMapping("/kakao/login")
    public String kakaoCallback(@RequestParam String code) {
        return kakaoLoginService.kakaoLogin(code);
    }
}