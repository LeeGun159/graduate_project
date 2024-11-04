package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //loginId로 멤버 조회 -> unique -> 1건만 조회 가능
    Optional<Member> findByLoginId(String loginId);
}
