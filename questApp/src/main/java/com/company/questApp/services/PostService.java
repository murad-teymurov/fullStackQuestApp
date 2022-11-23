package com.company.questApp.services;

import com.company.questApp.entites.Post;
import com.company.questApp.entites.User;
import com.company.questApp.repos.PostRepository;
import com.company.questApp.repos.UserRepository;
import com.company.questApp.requests.PostCreateRequest;
import com.company.questApp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;


    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId ){
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post createPost(PostCreateRequest postCreateRequest){

       User user =  userService.getById(postCreateRequest.getUserId());
       if(user == null){
           return null;
       }
       Post toSave = new Post();
       toSave.setId(postCreateRequest.getId());
       toSave.setText(postCreateRequest.getText());
       toSave.setTitle(postCreateRequest.getTitle());
       toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post getByIdPost(Long postId){
       return postRepository.findById(postId).orElse(null);
    }


    public void deletePostById(Long postId){
        postRepository.deleteById(postId);
    }

    public Post updatePost(Long postId, PostUpdateRequest postUpdateRequest){
       Post post =  getByIdPost(postId);
       if(post == null){
           return null;
       }
       post.setText(postUpdateRequest.getText());
       post.setTitle(postUpdateRequest.getTitle());
       postRepository.save(post);
       return post;

    }
}
