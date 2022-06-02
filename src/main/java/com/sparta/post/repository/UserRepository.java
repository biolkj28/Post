package com.sparta.post.repository;

import com.sparta.post.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsUserByNickname(String nickname);
    boolean existsUserByUsername(String username);
}