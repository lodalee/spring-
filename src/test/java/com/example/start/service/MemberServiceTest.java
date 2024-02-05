package com.example.start.service;

import com.example.start.domain.Members;
import com.example.start.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Members members = new Members();
        members.setName("spring");

        //when
        Long saveId = memberService.join(members);

        //then
        Members findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(members.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Members members1 = new Members();
        members1.setName("spring");

        Members members2 = new Members();
        members2.setName("spring");

        //when
        memberService.join(members1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(members2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(members2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}