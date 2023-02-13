package com.humingbird.dto;

import com.humingbird.entity.Member;

public class MemberSessionDto {
	
	private String name;
	
	// entity -> dto
	public MemberSessionDto(Member member) {
		this.name = member.getName();
	}
}
