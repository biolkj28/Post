package com.sparta.post.security;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FormLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException, IOException {
        String error = null;

        if (exception instanceof BadCredentialsException) {
            error = "닉네임 또는 패스워드를 확인해주세요";
        }else if(exception instanceof InternalAuthenticationServiceException) {
            error = "존재하지 않는 아이디 입니다.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            error = "인증이 거부되었습니다.";
        }else if(exception instanceof UsernameNotFoundException){
            error = "존재 하지 않는 계정입니다.";
        }
        System.out.println(exception.getClass().getName());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject object = new JSONObject();
        object.put("message", error);
        response.getWriter().write(object.toString());

    }
}