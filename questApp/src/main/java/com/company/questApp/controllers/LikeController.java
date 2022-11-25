package com.company.questApp.controllers;


import com.company.questApp.entites.Like;
import com.company.questApp.requests.LikeCreateRequest;
import com.company.questApp.responses.LikeResponse;
import com.company.questApp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(Optional<Long> userId,
                                          Optional<Long> postId){
       return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public Like addlike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createLike(likeCreateRequest);
    }

    @GetMapping("/{}likeId")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }
    @DeleteMapping("/likeId")
    public void deleteLike(@PathVariable Long likeId){
        likeService.deleteLikeById(likeId);
    }

}
