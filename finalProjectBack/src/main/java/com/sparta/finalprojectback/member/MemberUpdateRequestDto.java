package com.sparta.finalprojectback.member;

import lombok.*;

import javax.validation.Valid;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class MemberUpdateRequestDto {
    private String email;
    private String nickname;
}
