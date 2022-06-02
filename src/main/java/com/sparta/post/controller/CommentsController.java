package com.sparta.post.controller;


import com.sparta.post.dto.CommentsRequestDto;
import com.sparta.post.dto.CommentsUpdateDto;
import com.sparta.post.models.Message;
import com.sparta.post.models.UserRoleEnum;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService service;
    private final LinkedHashMap<String, String> map;
    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/api/comments/writeComments")
    public Map<String, String> writeComments(@RequestBody CommentsRequestDto dto, @AuthenticationPrincipal UserDetailsImpl details) {
        System.out.println(dto.getComments());
        if (details != null) {
            map.put("message", service.create(dto, details));

        } else {
            map.put("denied",Message.Denied.getMsg());
        }
        return map;
    }
    @Secured(UserRoleEnum.Authority.USER)
    @PutMapping("/api/comments/updateComments/{id}")
    public Map<String, String> updateComments(
            @PathVariable Long id,
            @RequestBody CommentsUpdateDto dto,
            @AuthenticationPrincipal UserDetailsImpl details) {

        if (details != null) {
            map.put("message",service.update(id, dto, details));
        } else {
            map.put("denied",Message.Denied.getMsg());
        }
        return map;
    }
    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/api/comments/deleteComments/{id}")
    public Map<String, String> deleteComments(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl details) {
        Map<String, String> map = new HashMap<>();
        if (details != null) {
            map.put("message", service.delete(id, details));
        } else {
            map.put("denied",Message.Denied.getMsg());
        }
        return map;
    }
}
