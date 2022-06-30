package com.sparta.finalprojectback.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> deleteScheduleById(Long id);
}
