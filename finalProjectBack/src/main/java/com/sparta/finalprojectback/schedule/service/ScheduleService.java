package com.sparta.finalprojectback.schedule.service;

import com.sparta.finalprojectback.schedule.dto.ScheduleRequestDto;
import com.sparta.finalprojectback.schedule.model.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ScheduleService {

    ResponseEntity<Long> createSchedule(ScheduleRequestDto requestDto);


    ResponseEntity<List<Schedule>> readSchedule(Long postId);


    ResponseEntity<String> deleteSchedule(Long scheduleId);

}
