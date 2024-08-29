package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Long userId;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userId = schedule.getUserId();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    public ScheduleResponseDto(Long id, String title, String contents, int count, LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.commentCount = count;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
