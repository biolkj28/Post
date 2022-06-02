package com.sparta.post.models;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostUpdateDto;
import com.sparta.post.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestDto dto, UserDetailsImpl userDetails) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.writer = userDetails.getUsername();
        this.userId = userDetails.getUser().getId();
    }

    public void update(PostUpdateDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
