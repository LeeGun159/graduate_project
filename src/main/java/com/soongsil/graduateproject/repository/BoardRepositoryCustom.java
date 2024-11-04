package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.dto.BoardSearchCond;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> search(BoardSearchCond condition, long page);

}
