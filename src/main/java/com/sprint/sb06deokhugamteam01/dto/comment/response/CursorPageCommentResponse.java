package com.sprint.sb06deokhugamteam01.dto.comment.response;

import com.sprint.sb06deokhugamteam01.dto.comment.CommentDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CursorPageCommentResponse(
        List<CommentDto> content,
        String nextCursor,
        LocalDateTime nextAfter,
        int size,
        int totalElements,
        boolean hasNext
) {}
