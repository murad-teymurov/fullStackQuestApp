package com.company.questApp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long id;
    private Long userid;
    private Long postId;
    private String text;
}
