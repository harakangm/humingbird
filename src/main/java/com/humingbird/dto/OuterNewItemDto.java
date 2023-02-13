package com.humingbird.dto;

import org.modelmapper.ModelMapper;

import com.humingbird.constant.Size;
import com.humingbird.entity.OuterNewItem;
import com.humingbird.entity.TopNewItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OuterNewItemDto {

	private Long id;
	
	private int shouder;
	
	private int bust;
	
	private int topLength;
	
	private Size size;
	
	public static ModelMapper modelMapper = new ModelMapper();
	
	public OuterNewItem createNewItem() {
		return modelMapper.map(this, OuterNewItem.class);
	}
	
	public static OuterNewItemDto of(OuterNewItem topNewItem) {
		return modelMapper.map(topNewItem, OuterNewItemDto.class);
	}
}
