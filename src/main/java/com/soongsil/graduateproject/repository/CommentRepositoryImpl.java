package com.soongsil.graduateproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soongsil.graduateproject.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

import static com.soongsil.graduateproject.domain.QBoard.board;
import static com.soongsil.graduateproject.domain.QComment.*;
import static com.soongsil.graduateproject.domain.QMember.*;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Comment> findCommentList(Long boardId) {
        return queryFactory
                .select(comment)
                .from(comment)
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .where(board.id.eq(boardId))
                .fetch();
    }
}
