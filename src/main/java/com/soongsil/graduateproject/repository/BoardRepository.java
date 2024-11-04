package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Query("select b from Board b join fetch b.member m where b.id = :id")
    Optional<Board> findBoardById(@Param("id") Long id);
}
