package com.sparta.finalprojectback.schedule.service;

import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.PostRepository;
import com.sparta.finalprojectback.schedule.dto.ScheduleRequestDto;
import com.sparta.finalprojectback.schedule.dto.ScheduleResponseDto;
import com.sparta.finalprojectback.schedule.model.Schedule;
import com.sparta.finalprojectback.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PostRepository postRepository;

    @Override
    public ResponseEntity<Long> createSchedule(ScheduleRequestDto requestDto) {
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );
        Long scheduleId =  scheduleRepository.save(Schedule.builder()
                .post(post)
                .placeName(requestDto.getPlaceName())
                .address(requestDto.getAddress())
                .link(requestDto.getLink())
                .date(requestDto.getDate())
                .x(requestDto.getX())
                .y(requestDto.getY())
                .phone(requestDto.getPhone())
                .build()).getId();

        return new ResponseEntity<>(scheduleId, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ScheduleResponseDto>> readSchedule(Long postId) {

        List<Schedule> scheduleList = scheduleRepository.findAllByPost_Id(postId);
        List<ScheduleResponseDto> resultList = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            resultList.add(ScheduleResponseDto.builder()
                    .id(schedule.getId())
                    .placeName(schedule.getPlaceName())
                    .link(schedule.getLink())
                    .date(schedule.getDate())
                    .address(schedule.getAddress())
                    .x(schedule.getX())
                    .y(schedule.getY())
                    .phone(schedule.getPhone())
                    .postId(schedule.getPost().getId())
                    .build());
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteSchedule(Long scheduleId) {

        scheduleRepository.deleteById(scheduleId);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
