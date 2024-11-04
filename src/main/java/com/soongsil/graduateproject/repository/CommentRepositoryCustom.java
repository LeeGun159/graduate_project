package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findCommentList(Long boardId);
}
