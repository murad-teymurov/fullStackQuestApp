package com.company.questApp.controllers;

import com.company.questApp.entites.Post;
import com.company.questApp.requests.PostCreateRequest;
import com.company.questApp.requests.PostUpdateRequest;
import com.company.questApp.responses.PostResponse;
import com.company.questApp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @GetMapping("{userId}")
    public Post getByIdPost(@PathVariable Long userId){
        return postService.getByIdPost(userId);
    }

    @PostMapping
    public Post addPost(@RequestBody PostCreateRequest newPost){
        return postService.createPost(newPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId,@RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePost(postId,postUpdateRequest);
    }




}
