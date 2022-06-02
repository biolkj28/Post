package com.sparta.post.service;

import com.sparta.post.dto.CommentsResponseDto;
import com.sparta.post.dto.CommentsUpdateDto;
import com.sparta.post.models.Comments;
import com.sparta.post.dto.CommentsRequestDto;
import com.sparta.post.models.Message;
import com.sparta.post.repository.CommentsRepository;
import com.sparta.post.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository repository;

    @Transactional
    public List<CommentsResponseDto> commentsList(Long postId) {
        List<CommentsResponseDto> res = new LinkedList<>();
        if (postId != null) {
            for (Comments comments : repository.commentsList(postId)) {
                CommentsResponseDto dto = new CommentsResponseDto(comments);
                res.add(dto);
            }
        }
        return res;
    }

    public String create(CommentsRequestDto dto, UserDetailsImpl details) {
        try {
            if (dto.getComments().isEmpty()) throw new NullPointerException();
            Comments comment = new Comments(dto, details.getUser());
            repository.save(comment);
            return Message.valueOf("SUCCESS").getMsg();

        } catch (NullPointerException e) {

            return "댓글을 작성해 주세요!";
        }
    }

    @Transactional
    public String update(Long id, CommentsUpdateDto requestDto, UserDetailsImpl details) {

        try {
            Comments comment = repository.findByIdAndUser_Id(id, details.getUser().getId())
                    .orElseThrow(IllegalStateException::new);

            comment.update(requestDto);
            return Message.valueOf("SUCCESS").getMsg();


        } catch (IllegalStateException e) {
            return Message.valueOf("NO_ROLE").getMsg();
        }
    }

    public String delete(Long id, UserDetailsImpl details) {

        try {

            repository.deleteByIdAndUser_Id(id, details.getUser().getId());
            return Message.valueOf("SUCCESS").getMsg();

        } catch (NullPointerException e) {
            return Message.NO_ROLE.getMsg();
        }
    }


}
