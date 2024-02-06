package com.example.start.service;

import com.example.start.domain.Members;
import com.example.start.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Members members){
        validateDuplicateMember(members); //중복 회원 검증
        memberRepository.save(members);
        return members.getId();
    }

    private void validateDuplicateMember(Members members) {
        memberRepository.findByName(members.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Members> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Members> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}



