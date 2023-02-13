package com.humingbird.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ui.Model;

import com.humingbird.dto.MemberFormDto;
import com.humingbird.service.MemberService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MemberService memberService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		//로그인 설정
		
		http.formLogin()
		.loginPage("/members/login")
		.defaultSuccessUrl("/")
		.usernameParameter("email")
		.failureUrl("/members/login/error")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃
		.logoutSuccessUrl("/");
		
		
		
		
		//페이지의 접근에 관한 설정
		http.authorizeRequests()
		    .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
		    .mvcMatchers("/" ,"/newItem/**" ,"/members/**", "/item/**", "/images/**").permitAll() //모든 사용자가 로그인(인증) 없이 접근할 수 있도록 설정
		    .mvcMatchers("/admin/**").hasRole("ADMIN") // '/admin' 으로 시작하는 경로는 계정이 ADMIN role일 경우에만 접근 가능하도록 설정
		    .anyRequest().authenticated(); //그 외에 페이지는 모두 로그인(인증)을 받아야 한다.
		
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}
