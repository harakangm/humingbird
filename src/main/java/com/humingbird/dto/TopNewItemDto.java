package com.humingbird.dto;

import org.modelmapper.ModelMapper;

import com.humingbird.constant.Size;
import com.humingbird.entity.NewItem;
import com.humingbird.entity.TopNewItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopNewItemDto {
	
	private Long id;
	
	private int shouder;
	
	private int bust;
	
	private int topLength;
	
	private Size size;
	
	public static ModelMapper modelMapper = new ModelMapper();
	
	public TopNewItem createNewItem() {
		return modelMapper.map(this,TopNewItem.class);
	}
	
	public static TopNewItemDto of(TopNewItem topNewItem) {
		return modelMapper.map(topNewItem, TopNewItemDto.class);
	}
}
