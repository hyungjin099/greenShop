package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberService.join(memberVO);

        return "redirect:/";
    }

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO){
        return "content/member/login";
    }

    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        return "content/member/login_result";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    //아이디 중복 확인
    @ResponseBody
    @PostMapping("/checkIdFetch")
    public boolean checkId(String memberId){
        return memberService.checkId(memberId);
    }

    //내 정보 관리 페이지
    @GetMapping("/info")
    public String memberInfo(){

        return "content/member/member_info";
    }

}
