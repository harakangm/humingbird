package com.humingbird.dto;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String email;
	
	@NotEmpty(message =" 비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16 , message = "비밀번호는 8자 이상입니다.")
	private String password;
	
	private int waistline;
		
	private int upperThight;
	
	private int legLength;
	
	private int bust;
	
	private int Sholder;
	
	private int topLenght;
}
