package com.humingbird.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humingbird.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByEmail(String email);
}
