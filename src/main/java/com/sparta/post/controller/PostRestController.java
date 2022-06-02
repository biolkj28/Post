package com.sparta.post.controller;

import com.sparta.post.models.Message;
import com.sparta.post.models.Post;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostUpdateDto;
import com.sparta.post.models.UserRoleEnum;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository repository;
    private final PostService service;


    @GetMapping("/api/post")
    public List<Post> readAllPost() {
        return repository.findAllByOrderByCreateAtDesc();
    }


    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/api/createPost")
    public Map<String, String> createPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetailsImpl details) {
        Map<String, String> map = new HashMap<>();
        if (details != null) {
            Post post = new Post(dto, details);
            repository.save(post);
            map.put("message", Message.SUCCESS.getMsg());

        } else {
            map.put("denied", Message.Denied.getMsg());
        }
        return map;
    }
    @Secured(UserRoleEnum.Authority.USER)
    @PutMapping("/api/updatePost/{id}")
    public Map<String, String> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto dto, @AuthenticationPrincipal UserDetailsImpl details) {
        Map<String, String> map = new HashMap<>();
        if (details != null) {
            map.put("message",service.update(id, dto, details));
        } else {
            map.put("denied", Message.Denied.getMsg());
        } return map;
    }
    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/api/deletePost/{id}")
    public Map<String, String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl details) {
        Map<String, String> map = new HashMap<>();
        if (details != null) {
            Optional<Post> post = repository.findByIdAndUserId(id, details.getUser().getId());
            repository.deleteById(id);
            map.put("message",Message.SUCCESS.getMsg());
        } else {
            map.put("denied",Message.Denied.getMsg());
        }
        return map;
    }
}
