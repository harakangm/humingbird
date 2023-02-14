package com.humingbird.dto;

import com.humingbird.constant.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
	private String searchDateType;
	private String searchBy;
	private String searchQuery = "";
	private Category category;
}
