package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class CommentsRequestDto {
    private String comments;
    private Long post_id;
}
