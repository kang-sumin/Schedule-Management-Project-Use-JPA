package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    /**
     * @param scheduleRequestDto Body로 들어온 일정 JSON
     * @return 저장된 일정
     */
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    /**
     * @param id
     * @return 조회된 일정
     */
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findScheduleById(id);

        return new ScheduleResponseDto(schedule);
    }

    /**
     * @param id
     * @param scheduleRequestDto 수정될 Body JSON
     * @return 수정된 일정
     */
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = findScheduleById(id);

        schedule.update(scheduleRequestDto);
        return new ScheduleResponseDto(schedule);
    }

    private Schedule findScheduleById(Long id){
        return scheduleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("일정을 찾지 못했습니다."));
    }
}
