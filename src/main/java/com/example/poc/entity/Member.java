package com.example.poc.entity;

import com.example.poc.constant.Role;
import com.example.poc.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {

    @Id @Column(name = "memeber_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto dto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        String password = passwordEncoder.encode(dto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);

        return member;
    }
}
