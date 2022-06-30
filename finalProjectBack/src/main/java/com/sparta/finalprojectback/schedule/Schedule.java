package com.sparta.finalprojectback.schedule;

import com.sparta.finalprojectback.post.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    // JPA에서 String 기본 길이 = 255

    // 카카오 api 이용한 장소 링크
    @Column(name = "link", nullable = false)
    private String link;

    @Column(length = 100, name = "address", nullable = false)
    private String address;

    @Column(name = "x", nullable = false)
    private float coordinateX;

    @Column(name = "y", nullable = false)
    private float coordinateY;

    @Column(name = "day", nullable = false)
    private int day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", referencedColumnName = "post_id")
    private Posts posts;

    // 일정 생성에 이용
    public Schedule(ScheduleRequestDto requestDto, Posts posts) {
        this.link = requestDto.getLink();
        this.address = requestDto.getAddress();
        this.coordinateX = requestDto.getCoordinateX();
        this.coordinateY = requestDto.getCoordinateY();
        this.day = requestDto.getDay();
        this.posts = posts;
    }

    // 일정 수정에 이용
    public void updateSchedule(ScheduleRequestDto requestDto) {
        this.id = id;
        this.link = requestDto.getLink();
        this.address = requestDto.getAddress();
        this.coordinateX = requestDto.getCoordinateX();
        this.coordinateY = requestDto.getCoordinateY();
        this.day = requestDto.getDay();
    }
}
