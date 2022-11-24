package com.company.questApp.services;

import com.company.questApp.entites.Post;
import com.company.questApp.entites.User;
import com.company.questApp.repos.PostRepository;
import com.company.questApp.repos.UserRepository;
import com.company.questApp.requests.PostCreateRequest;
import com.company.questApp.requests.PostUpdateRequest;
import com.company.questApp.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;


    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId ){

        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }else{
            list = postRepository.findAll();
        }
        return list.stream().map((p) -> new PostResponse(p)).collect(Collectors.toList());
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
