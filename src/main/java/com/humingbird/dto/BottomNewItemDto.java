package com.humingbird.dto;

import org.modelmapper.ModelMapper;

import com.humingbird.constant.Size;
import com.humingbird.entity.BottomNewItem;
import com.humingbird.entity.NewItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BottomNewItemDto {
	
	private Long id;
	
	private int waistline;
	
	private int upperThight;
	
	private int legLength;
	
	private Size size;
	
	public static ModelMapper modelMapper = new ModelMapper();
	
	public BottomNewItem createBottomNewItem() {
		return modelMapper.map(this, BottomNewItem.class);
	}
	
	public static BottomNewItemDto of(BottomNewItem bottomNewItem) {
		return modelMapper.map(bottomNewItem, BottomNewItemDto.class);
	}
}
