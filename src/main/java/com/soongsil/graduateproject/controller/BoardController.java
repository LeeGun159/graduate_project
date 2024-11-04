package com.soongsil.graduateproject.controller;

import com.soongsil.graduateproject.domain.Board;
import com.soongsil.graduateproject.dto.BoardGetDto;
import com.soongsil.graduateproject.dto.BoardPostDto;
import com.soongsil.graduateproject.dto.BoardSearchCond;
import com.soongsil.graduateproject.service.BoardService;
import com.soongsil.graduateproject.service.MemberService;
import com.soongsil.graduateproject.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/posts")
    public String list(Model model, @ModelAttribute BoardSearchCond condition, @RequestParam(defaultValue = "1") long page) {
        List<Board> boardList = boardService.findList(condition, page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);
        return "board/posts";
    }

    @GetMapping("/posts/write")
    public String writeForm(Model model) {
        model.addAttribute("boardForm", new BoardPostDto());
        return "board/postsWrite";
    }

    @PostMapping("/posts/write")
    public String write(@Valid @ModelAttribute(name = "boardForm") BoardPostDto boardPostDto, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "board/postsWrite";
        }

        HttpSession session = request.getSession(false);
        if(session != null){
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Long boardId = boardService.post(memberId, boardPostDto.getTitle(), boardPostDto.getContent());
            return "redirect:/posts/" + boardId;
        }
        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String findOne(@PathVariable Long id, Model model, HttpServletRequest request) {
        Board board = boardService.findOne(id);
        BoardGetDto boardGetDto = new BoardGetDto(board);

        // session 에 있는 로그인 정보 가져와서 보내기 (댓글 작성시 댓글 등록 정보에 추가하기 위함)
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
            String username = memberService.findMember(memberId).getName();
            model.addAttribute("username", username); // user 정보 담기
        }

        model.addAttribute("boardDto", boardGetDto);
        return "board/postsDetail";
    }

}
