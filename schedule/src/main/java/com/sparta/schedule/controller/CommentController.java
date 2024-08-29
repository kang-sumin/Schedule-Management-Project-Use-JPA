package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/comments/{id}")
    public CommentResponseDto getComment(@PathVariable("schedulesId") Long schedulesId, @PathVariable("id") Long id) {
        return commentService.getComment(schedulesId, id);
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> getComments(@PathVariable("schedulesId") Long schedulesId) {
        return commentService.getComments(schedulesId);
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDto updateComment(@PathVariable("schedulesId") Long schedulesId, @PathVariable("id") Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(schedulesId, id, commentRequestDto);
    }

    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable("schedulesId") Long schedulesId, @PathVariable("id") Long id) {
        return commentService.deleteComment(schedulesId, id);
    }
}
