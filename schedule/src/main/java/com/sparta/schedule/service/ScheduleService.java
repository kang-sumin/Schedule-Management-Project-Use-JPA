package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private CommentRepository commentRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, CommentRepository commentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.commentRepository = commentRepository;
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

    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);

        return schedules.map(schedule -> new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getUserId(),
                schedule.getComments().size(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
                ));
    }

    private Schedule findScheduleById(Long id){
        return scheduleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("일정을 찾지 못했습니다."));
    }



}
