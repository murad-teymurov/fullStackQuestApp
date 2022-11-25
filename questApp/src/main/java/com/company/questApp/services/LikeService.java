package com.company.questApp.services;

import com.company.questApp.entites.Like;
import com.company.questApp.entites.Post;
import com.company.questApp.entites.User;
import com.company.questApp.repos.LikeRepository;
import com.company.questApp.requests.LikeCreateRequest;
import com.company.questApp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private final UserService userService;
    private final PostService postService;


    public LikeService(LikeRepository likeRepository, UserService userService,PostService postService){
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postID){
        List<Like> list;
        if(userId.isPresent() && postID.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postID.get());
        }else if(userId.isPresent()){
            list = likeRepository.findByUserId(userId.get());
        }else if(postID.isPresent()){
            list = likeRepository.findByPostId(postID.get());
        }
        list = likeRepository.findAll();

        return list.stream().map(res -> new LikeResponse(res)).collect(Collectors.toList());
    }

    public Like createLike(LikeCreateRequest request) {
        User user = userService.getById(request.getUserId());
        Post post = postService.getByIdPost(request.getPostId());

        if(user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }
        return null;
    }

    public void deleteLikeById(Long likeId){
        likeRepository.deleteById(likeId);
    }

    public Like getLikeById(Long likeId){
        return likeRepository.findById(likeId).orElse(null);
    }
}
