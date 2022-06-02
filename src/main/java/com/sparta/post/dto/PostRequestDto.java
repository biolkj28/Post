package com.sparta.post.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostRequestDto {
    private String title;
    private String contents;
}
