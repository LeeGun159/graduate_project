package com.soongsil.graduateproject;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        initMemberService.init();
    }

    @Component
    static class InitMemberService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Member memberA = new Member("admin1", "aaa", "admin1", "aaa@aaa.com", "12334");
            Member memberB = new Member("admin2", "aaa", "admin2", "aaa@aaa.com", "12334");
            em.persist(memberB);
            em.persist(memberA);

            for (int i = 0; i < 100; i++) {
                Member selectedMember = i % 2 == 0 ? memberA : memberB;
                Board board = new Board(selectedMember ,"테스트" + i, "내용" + i);
                em.persist(board);
            }
        }
    }
}
