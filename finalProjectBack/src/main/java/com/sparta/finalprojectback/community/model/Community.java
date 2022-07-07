package com.sparta.finalprojectback.community.model;

import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.member.*;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Community extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 길이 255 -> 500으로 늘려줌
    private String title;

    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    private Member member;


    public Community(CommunityRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }
}