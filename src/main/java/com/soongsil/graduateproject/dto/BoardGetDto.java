package com.soongsil.graduateproject.dto;

import com.soongsil.graduateproject.domain.Board;
import lombok.Data;

@Data
public class BoardGetDto {

    private Long id;

    private String title;

    private String content;

    private String loginId;

    public BoardGetDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.loginId = board.getMember().getLoginId();
    }
}
