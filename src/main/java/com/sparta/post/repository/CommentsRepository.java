package com.sparta.post.repository;

import com.sparta.post.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("select this from Comments this where this.post_id= :post_id order by this.createAt desc ")
    List<Comments> commentsList(@Param("post_id") Long post_id);

    Optional<Comments>findByIdAndUser_Id(Long id, Long user_id);
    @Transactional
    void deleteByIdAndUser_Id(Long id, Long user_id);
}
