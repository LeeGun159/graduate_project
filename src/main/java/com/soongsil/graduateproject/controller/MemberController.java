package com.soongsil.graduateproject.controller;

import com.soongsil.graduateproject.domain.Member;
import com.soongsil.graduateproject.dto.MemberLoginDto;
import com.soongsil.graduateproject.dto.MemberSaveDto;
import com.soongsil.graduateproject.dto.MemberUpdateDto;
import com.soongsil.graduateproject.service.MemberService;
import com.soongsil.graduateproject.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 폼으로 이동
    @GetMapping("/signup")
    public String createForm(Model model){
        model.addAttribute("memberSaveDto", new MemberSaveDto());
        return "members/signup";
    }

    //회원가입
    @PostMapping("/signup")
    public String saveMember(@ModelAttribute @Valid MemberSaveDto memberSaveDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "members/signup";
        }

        Member member = memberSaveDto.toEntity();
        memberService.join(member);

        return "redirect:/";
    }

    //회원 탈퇴
    @PostMapping("members/delete")
    public String delete(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            memberService.delete(memberId);
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원 수정
    @GetMapping("members/update")
    public String updateForm(@ModelAttribute MemberUpdateDto memberUpdateDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Member member = memberService.findMember(memberId);
            memberUpdateDto.toDto(member);
        }
        return "members/updateMemberForm";
    }

    @PostMapping("members/update")
    public String update(@Valid @ModelAttribute MemberUpdateDto memberUpdateDto, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "members/updateMemberForm";
        }

        HttpSession session = request.getSession(false);
        if(session != null){
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Member updateMember = memberService.update(
                    memberId,
                    memberUpdateDto.getPassword(),
                    memberUpdateDto.getName(),
                    memberUpdateDto.getMail(),
                    memberUpdateDto.getPhoneNumber());

            session.setAttribute(SessionConst.LOGIN_MEMBER, updateMember);
        }
        return "redirect:/";
    }

    //로그인 폼
    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberLoginDto memberLoginDto){
        return "login/loginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = memberService.login(memberLoginDto.getLoginId(), memberLoginDto.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 틀렸습니다");
            return "login/loginForm";
        }

        if(!loginMember.isSignUp()){
            bindingResult.reject("DeleteMember", "탈퇴한 회원입니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getId());

        return "redirect:/";
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
