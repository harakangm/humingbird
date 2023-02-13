package com.humingbird.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;


@EntityListeners(value = {AuditingEntityListener.class}) //Auditing을 적용하기 위해
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스한테 매핑정보만 제공
@Getter
@Setter
public class BaseTimeEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;
	
	@LastModifiedDate
	private LocalDateTime upDateTime;
}
