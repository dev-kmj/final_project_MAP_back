package com.sparta.finalprojectback.member;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @PostMapping("/join")
    public Long join(@RequestBody MemberJoinRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody MemberLoginRequestDto requestDto) {
        return memberService.login(requestDto);
    }

    //TEST CODE

    @GetMapping("user/test")
    public Map userResponseTest(HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        String userId = jwtTokenProvider.getUserPk(token);
        Map<String, String> result = new HashMap<>();
        result.put("user ok", userId);
        return result;
    }

    @GetMapping("admin/test")
    public Map adminResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "admin ok");
        return result;
    }

}
