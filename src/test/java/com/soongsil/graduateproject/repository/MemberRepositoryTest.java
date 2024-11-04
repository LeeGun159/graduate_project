package com.soongsil.graduateproject.repository;

import com.soongsil.graduateproject.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    void join(){
        Member member = new Member("aaa", "1234", "tamtam", "xxx@xxx.com", "1234556");

        Member savedMember = memberRepository.save(member);

        Assertions.assertThat(member).isEqualTo(savedMember);
    }

}