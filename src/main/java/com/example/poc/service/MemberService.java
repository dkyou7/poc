package com.example.poc.service;

import com.example.poc.entity.Member;
import com.example.poc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입되어있는 회원입니다..!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder().username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString()).build();
    }

    public void process_updateScale(String email, String scale) {
        Member byEmail = memberRepository.findByEmail(email);
        byEmail.setScale(scale);
        System.out.println("byEmail.getScale() = " + byEmail.getScale());
    }

    public String findScaleByEmail(String email) {
        Member byEmail = memberRepository.findByEmail(email);
        return byEmail.getScale();
    }
}
