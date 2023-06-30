package com.example.poc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberFormDto {

    @NotEmpty(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식을 준수해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수값입니다.")
    @Length(min = 1,message = "최소 2자리 이상 입력해주세요.")
    private String password;
}
