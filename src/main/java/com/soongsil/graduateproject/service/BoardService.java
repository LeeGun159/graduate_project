package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.domain.Member;
import com.soongsil.graduateproject.dto.BoardSearchCond;
import com.soongsil.graduateproject.repository.BoardRepository;
import com.soongsil.graduateproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public List<Board> findBoardsAll() {
        List<Board> list = boardRepository.findAll();
        return list;
    }

    @Transactional
    public Long post(Long memberId, String title, String content){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        Board board = new Board(member, title, content);
        boardRepository.save(board);
        return board.getId();
    }

    public Board findOne(Long id) {
        return boardRepository.findBoardById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    public List<Board> findList(BoardSearchCond condition, Long page) {
        return boardRepository.search(condition, page);
    }

}
