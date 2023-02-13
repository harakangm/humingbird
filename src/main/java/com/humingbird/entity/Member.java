package com.humingbird.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.humingbird.constant.Role;
import com.humingbird.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity implements UserDetails{
	
	@Id
	@Column(name="memer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//허리둘레
	private int waistline;
	
	//허벅지둘레
	private int Upper_Thight;
	
	//다리길이
	private int leg_length;
	
	//가슴둘레
	private int bust;
	
	//어깨넓이
	private int Sholder;
	
	//상의길이
	private int top_lenght;
	
	public static Member createMember(MemberFormDto memberFormDto,PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setWaistline(memberFormDto.getWaistline());
		member.setUpper_Thight(memberFormDto.getUpperThight());
		member.setLeg_length(memberFormDto.getLegLength());
		member.setBust(memberFormDto.getBust());
		member.setSholder(memberFormDto.getSholder());
		member.setTop_lenght(memberFormDto.getTopLenght());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		
		member.setRole(Role.USER);
		
		return member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(role.toString()));
		return auth;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
