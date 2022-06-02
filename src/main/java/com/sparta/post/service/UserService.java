package com.sparta.post.service;


import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.models.User;
import com.sparta.post.models.UserRoleEnum;
import com.sparta.post.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {

        // 회원 ID 중복 확인
        try {
            if (requestDto == null) throw new NullPointerException("관리자 문의");
        } catch (NullPointerException e) {
            return e.getMessage();
        }
        try {
            /*
             *닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
             * 비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패
             */
            boolean state = validateNickAndPassword(requestDto);
            if (state) {
                String username = requestDto.getUsername();
                if (userRepository.existsUserByUsername(requestDto.getUsername())) {
                    throw new IllegalStateException("중복된 사용자 ID 가 존재합니다.");
                }

                // 패스워드 암호화
                String password = bCryptPasswordEncoder.encode(requestDto.getPassword());
                String email = requestDto.getEmail();
                String nickname = requestDto.getNickname();

                // 사용자 ROLE 확인
                UserRoleEnum role = UserRoleEnum.USER;
                if (requestDto.isAdmin()) {
                    if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                        throw new IllegalStateException("관리자 암호가 틀려 등록이 불가능합니다.");
                    }
                    role = UserRoleEnum.ADMIN;
                }

                User user = new User(username, nickname, password, email, role);
                userRepository.save(user);
                return "회원가입 성공";

            } else
                return "회원가입 실패";

        } catch (IllegalStateException | NullPointerException e) {
            return e.getMessage();
        }
    }

    public boolean validateNickAndPassword(SignupRequestDto dto) {
        final String nickPattern = "[a-zA-Z0-9]{3,}";

        if (!dto.getNickname().matches(nickPattern)) {
            throw new IllegalStateException("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성");
        } else if (dto.getPassword().contains(dto.getNickname()) || dto.getPassword().length() < 4) {
            throw new IllegalStateException("회원가입 실패");
        } else if (!dto.getPassword().equals(dto.getCheckPw())) {
            throw new IllegalStateException("입력한 비밀번호와 확인 비밀번호가 다릅니다.");
        } else if (userRepository.existsUserByNickname(dto.getNickname())) {
            throw new IllegalStateException("중복된 닉네임이 존재합니다.");
        } else {
            return true;
        }
    }

}