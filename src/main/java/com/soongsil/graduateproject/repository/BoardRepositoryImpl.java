package com.soongsil.graduateproject.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.dto.BoardSearchCond;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.soongsil.graduateproject.domain.QBoard.*;
import static com.soongsil.graduateproject.domain.QMember.*;

@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> search(BoardSearchCond condition, long page) {
        log.info("call boardRepositoryImpl.search()");

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(condition.getTitleAndContent())) {
            builder.or(board.title.contains(condition.getTitleAndContent()));
            builder.or(board.content.contains(condition.getTitleAndContent()));
        }

        return queryFactory
                .select(board)
                .from(board)
                .join(board.member, member).fetchJoin()
                .where(builder)
                .offset((Math.max(1, page) -1) * 10)
                .limit(10)
                .orderBy(board.createdDate.desc())
                .fetch();
    }
}
