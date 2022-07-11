package com.sparta.finalprojectback.community.model;

import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.member.Member;
import com.sparta.finalprojectback.member.Timestamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Community extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    private Member member;

    // 게시물 생성에 이용
    public Community(CommunityRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }

    // 게시물 수정에 이용
    public void updateCommunity(Long id, CommunityRequestDto requestDto, Member member) {
        this.id = id;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }
}