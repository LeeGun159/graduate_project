package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.domain.Comment;
import com.soongsil.graduateproject.domain.Member;
import com.soongsil.graduateproject.repository.BoardRepository;
import com.soongsil.graduateproject.repository.CommentRepository;
import com.soongsil.graduateproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void save(Long memberId, Long boardId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 사용자 입력입니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시판 입력입니다."));
        Comment comment = new Comment(content, board, member);
        commentRepository.save(comment);
    }

    public List<Comment> findCommentListByBoardId(Long boardId) {
        return commentRepository.findCommentList(boardId);
    }

}
