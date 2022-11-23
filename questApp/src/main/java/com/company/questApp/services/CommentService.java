package com.company.questApp.services;

import com.company.questApp.entites.Comment;
import com.company.questApp.entites.Post;
import com.company.questApp.entites.User;
import com.company.questApp.repos.CommentRepository;
import com.company.questApp.requests.CommentCreateRequest;
import com.company.questApp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository,UserService userService,PostService postService){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId){
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createCommentByParam(CommentCreateRequest commentCreateRequest){
       User user = userService.getById(commentCreateRequest.getUserid());
       Post post = postService.getByIdPost(commentCreateRequest.getPostId());

       if(user != null && post != null){
           Comment comment = new Comment();
           comment.setId(commentCreateRequest.getId());
           comment.setText(commentCreateRequest.getText());
           comment.setPost(post);
           comment.setUser(user);
           return commentRepository.save(comment);
       }
       return null;
    }

    public Comment updateCommentWithParams(Long commentId, CommentUpdateRequest commentUpdateRequest){
       Optional<Comment> opComment = commentRepository.findById(commentId);

       if(opComment.isPresent()){
           Comment comment = opComment.get();
           comment.setText(commentUpdateRequest.getText());
           return commentRepository.save(comment);
       }
       return null;
    }

    public void deleteById(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
