package com.humingbird.dto;


import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
	private Long id;
	
	private String brandName;
	
	private String clotheName;
	
	private String detail;
	
	private Integer price;
	
	private String imgUrl;
	
	@QueryProjection 
	public ItemDto(Long id, String brandName, String clotheName, String detail, Integer price, String imgUrl) {
		this.id = id;
		this.brandName = brandName;
		this.clotheName = clotheName;
		this.detail = detail;
		this.price = price;
		this.imgUrl = imgUrl;
	}
}
