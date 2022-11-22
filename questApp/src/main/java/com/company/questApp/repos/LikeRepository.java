package com.company.questApp.repos;

import com.company.questApp.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
