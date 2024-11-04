package com.soongsil.graduateproject.dto;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.domain.Member;
import lombok.Data;

@Data
public class BoardSaveDto {
    private String title;
    private String content;

    public Board toEntity(Member member){
        Board board = new Board(member, title, content);
        return board;
    }
}
