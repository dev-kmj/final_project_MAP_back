package com.sparta.finalprojectback.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ScheduleResponseDto {

    private String placeName;
    private String address;
    private String link;
    private float x;
    private float y;
    private int date;

}