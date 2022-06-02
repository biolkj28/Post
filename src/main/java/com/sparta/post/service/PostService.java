package com.sparta.post.service;

import com.sparta.post.models.Message;
import com.sparta.post.models.Post;
import com.sparta.post.dto.PostUpdateDto;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    @Transactional
    public String update(Long id, PostUpdateDto dto, UserDetailsImpl details) {
        try {
            Post post = repository.findByIdAndUserId(id,details.getUser().getId()).orElseThrow(
                    () -> new IllegalStateException("수정 권한이 없습니다.")
            );
            post.update(dto);
        } catch (IllegalStateException e) {
            return Message.NO_ROLE.getMsg();
        }

        return Message.SUCCESS.getMsg();
    }

}
