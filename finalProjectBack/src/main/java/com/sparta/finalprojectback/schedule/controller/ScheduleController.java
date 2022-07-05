package com.sparta.finalprojectback.schedule.controller;

import com.sparta.finalprojectback.schedule.dto.ScheduleRequestDto;
import com.sparta.finalprojectback.schedule.dto.ScheduleResponseDto;
import com.sparta.finalprojectback.schedule.model.Schedule;
import com.sparta.finalprojectback.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성
    @PostMapping("user/schedule")
    ResponseEntity<Long> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }

    //일정 조회
    @GetMapping("user/plan/post/{postId}/schedules")
    ResponseEntity<List<ScheduleResponseDto>> readSchedule(@PathVariable Long postId){
        return scheduleService.readSchedule(postId);
    }

    //일정 삭제
    @DeleteMapping("user/schedule/{scheduleId}")
    ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }

}
