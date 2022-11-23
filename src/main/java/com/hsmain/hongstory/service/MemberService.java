package com.hsmain.hongstory.service;

import com.hsmain.hongstory.domain.Member;
import com.hsmain.hongstory.repository.MemberRepository;
import com.hsmain.hongstory.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName()).ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        memberRepository.save(member);
        return member.getId();
    }


    public List<Member> findMembers(){
       return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
