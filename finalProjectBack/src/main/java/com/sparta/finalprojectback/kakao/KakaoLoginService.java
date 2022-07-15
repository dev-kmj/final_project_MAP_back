package com.sparta.finalprojectback.kakao;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoOAuth kakaoOAuth;

    public String kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth.getUserInfo(authorizedCode);
        String kakaoId = Long.toString(userInfo.getId());   // kakaoId를 String을 변환시키기
        String email = "kakao";
        Long id;


        Optional<Member> member = memberRepository.findByEmail(email);

        String jwt = "";

        // 이미 가입한 카카오 회원이라면 정보로 토큰 발급
        if (member.isPresent()) {
            jwt = jwtTokenProvider.createToken(memberRepository.findByEmail(email).get().getUsername(),
                    memberRepository.findByEmail(email).get().getRoles());
            System.out.println(jwt);
        } else {
            id = memberRepository.save(Member.builder()
                    .username(kakaoId)
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .nickname(userInfo.getNickname())
                    .email(email)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build()).getId();

            jwt = jwtTokenProvider.createToken(memberRepository.findById(id).get().getUsername(),
                    memberRepository.findById(id).get().getRoles());
            System.out.println(jwt);
        }
        return jwt;
    }

}