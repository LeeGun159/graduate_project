package com.soongsil.graduateproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentPostDto {
    private String memberName;
    private String content;
}
