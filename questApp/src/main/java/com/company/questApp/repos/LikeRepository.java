package com.company.questApp.repos;

import com.company.questApp.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByUserIdAndPostId(Long userId, Long postID);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);
}
