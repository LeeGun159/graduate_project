package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Member;
import com.soongsil.graduateproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 가입 테스트")
    void joinTest(){
        //given
        Member member = new Member("aaa", "1234", "tamtam", "xxx@xxx.com", "1234556");

        //when
        Member savedMember = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findById(savedMember.getId()).get());
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    void deleteTest(){
        //given
        Member member = new Member("aaa", "1234", "tamtam", "xxx@xxx.com", "1234556");
        Member savedMember = memberService.join(member);

        //when
        memberService.delete(savedMember.getId());

        //then
        assertThat(member.isSignUp()).isFalse();
    }

    @Test
    @DisplayName("회원 탈퇴 및 수정 예외 테스트")
    void exceptionTest(){
        //given
        Member member = new Member("aaa", "1234", "tamtam", "xxx@xxx.com", "1234556");
        Member savedMember = memberService.join(member);

        //when
        //회원 탈퇴 예외
        assertThat(memberService.delete(1231241254L)).isFalse();
        //회원 수정 예외
//        assertThat(memberService.update(124312345L)).isFalse();

        //then
        //위에서 예외를 잡음
    }
}