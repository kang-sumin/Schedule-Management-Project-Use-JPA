package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules/{schedulesId}")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponseDto createComment(@PathVariable("schedulesId") Long schedulesId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(schedulesId, commentRequestDto);
    }
}
