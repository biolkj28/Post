package com.sparta.post.dto;

import com.sparta.post.models.Comments;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
public class CommentsResponseDto {
    private final Long id;
    private final String comments;
    private final String writer;
    private final LocalDateTime createAt;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.comments = comments.getComments();
        this.writer = comments.getUser().getUsername();
        this.createAt = comments.getCreateAt();
    }
}
