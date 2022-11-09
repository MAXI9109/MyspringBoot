package com.shopping.controller;


import com.shopping.Service.MemberService;
import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor //for MemberController02
@RequestMapping(value = "/members")
public class MemberController {
    private final String urlPrefix = "/member";


    @GetMapping(value = "/new")
    public String insertForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return urlPrefix + "/meInsertForm";
    }

    private final MemberService memberService;
    private final PasswordEncoder passEncoder;

    //@Valid는 command 객체에 유효성 검사를 진행해줍니다.
    // bindingResult는 유효성 검사에 문제가 있으면, 해당정보가 들어 있습니다.
    @PostMapping(value = "/new")
    public String insertForm(@Valid MemberFormDto dto, BindingResult error, Model model) {// MemberController02
        if (error.hasErrors()) { //유효성 검사를 충족하지 못하면 다시 가입 페이지로 이동동
            return urlPrefix + "/meInsertForm";

        }

        try {
            Member member = Member.createMember(dto, passEncoder);
            this.memberService.saveMember(member);
        } catch (IllegalStateException err) {
            model.addAttribute("errorMessage", err.getMessage());
            return urlPrefix + "/meInsertForm";
        }


        //메인 페이지로 이동
        return "redirect:/"; // response.sendRedirect() ;

    }
        //MemberController03
        // form 태그와 SecurityConfig 파일에 정의 되어 있습니다.
        @GetMapping(value = "/login")
        public String loginMember(){
            return "/member/memberLoginForm" ;
        }

        //MemberController03
        //SecurityConfig 파일에 정의 되어 있습니다.
        @GetMapping(value = "/login/error")
        public String loginError(Model model){

            model.addAttribute("loginErrorMsg","뭔들 확인해라 틀렷으니까." );

            return "/member/memberLoginForm" ;
        }
}

