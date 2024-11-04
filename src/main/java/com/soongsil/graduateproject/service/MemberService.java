package com.soongsil.graduateproject.service;

import com.soongsil.graduateproject.domain.Member;
import com.soongsil.graduateproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 조회
    public Member findMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 정보입니다."));
        return member;
    }

    //회원 가입
    @Transactional
    public Member join(Member member){
        memberRepository.save(member);
        return member;
    }

    //로그인
    public Member login(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if(member == null){
            return null;
        }
        return member.loginLogic(password);
    }


    //회원 탈퇴
    @Transactional
    public boolean delete(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null){
            return false;
        }
        member.setDeleteMember();
        return true;
    }

    //회원 정보 수정
    @Transactional
    public Member update(Long id, String password, String name, String mail, String phoneNumber){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null){
            return null;
        }
        member.updateMember(password, name, mail, phoneNumber);
        return member;
    }
}
