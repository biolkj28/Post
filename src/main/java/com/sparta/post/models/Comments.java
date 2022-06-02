package com.sparta.post.models;


import com.sparta.post.dto.CommentsRequestDto;
import com.sparta.post.dto.CommentsUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comments extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String comments;


    @Column(nullable = false)
    private Long post_id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Comments(CommentsRequestDto dto, User user) {
        this.comments = dto.getComments();
        this.post_id = dto.getPost_id();
        this.user = user;
    }
    public void update(CommentsUpdateDto dto){
        this.comments = dto.getComments();
    }

}
