package com.soongsil.graduateproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardPostDto {

    @NotEmpty(message = "제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수입니다.")
    private String content;

}
