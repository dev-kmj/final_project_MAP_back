package com.sparta.finalprojectback.schedule;

import com.sparta.finalprojectback.post.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {

    private String link;
    private String address;
    private float coordinateX;
    private float coordinateY;
    private int day;
    private Posts posts;
}
