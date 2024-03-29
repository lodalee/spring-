package com.example.start.controller;

import com.example.start.domain.Members;
import com.example.start.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Members members = new Members();
        members.setName(form.getName());

        memberService.join(members);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Members> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
