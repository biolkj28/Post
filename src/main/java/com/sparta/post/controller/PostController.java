package com.sparta.post.controller;

import com.sparta.post.models.Post;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository repository;
    private final CommentsService service;

    @GetMapping("/detail/page/{id}")
    public String readPost(@PathVariable Long id, Model model) {

        Post post = repository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재 하지 않습니다.")
        );
        model.addAttribute("post",post);
        model.addAttribute("comments",service.commentsList(id));
        return "detail";
    }
}
