package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    /**
     *
     * @param schedulesId
     * @param commentRequestDto
     * @return 저장된 댓글
     */
    public CommentResponseDto createComment(Long schedulesId, CommentRequestDto commentRequestDto) {
        Schedule schedule = findScheduleById(schedulesId);

        Comment comment = new Comment(commentRequestDto, schedule);

        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    /**
     *
     * @param schedulesId
     * @param id
     * @return 단건 조회된 댓글
     */
    public CommentResponseDto getComment(Long schedulesId, Long id) {
        findScheduleById(schedulesId);
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글을 찾지 못했습니다."));

        return new CommentResponseDto(comment);
    }


    public List<CommentResponseDto> getComments(Long schedulesId) {
        findScheduleById(schedulesId);
        return commentRepository
                .findAll()
                .stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    private Schedule findScheduleById(Long id){
        return scheduleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("일정을 찾지 못했습니다."));
    }
}
