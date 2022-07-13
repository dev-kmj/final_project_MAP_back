package com.sparta.finalprojectback.member;

import lombok.*;

import javax.validation.Valid;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class MemberUpdateRequestDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private String password;
}
