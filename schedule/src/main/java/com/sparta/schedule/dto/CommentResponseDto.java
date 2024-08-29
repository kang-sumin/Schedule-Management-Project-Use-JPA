package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long userId;
    private Long scheduleId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.userId = comment.getUserId();
        this.scheduleId = comment.getSchedule().getId();
    }
}
