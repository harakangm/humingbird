package com.humingbird.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.humingbird.constant.Category;
import com.humingbird.entity.NewItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewItemFormDto {
	
	private Long id;
	
	@NotBlank(message = "브랜드명은 필수 입력 값입니다.")
	private String brandName;
	
	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String clotheName;
	
	private Category category;
	
	@NotBlank(message = "상품 상세설명은 필수 입력 값입니다.")
	private String detail;
	
	@NotNull(message = "가격은 필수 입력 값입니다.")
	private int price;
	
	
	private List<TopNewItemDto> topNewItemDtoList = new ArrayList<>();
	
	private List<BottomNewItemDto> bottomNewItemDtoList = new ArrayList<>();
	
	private List<OuterNewItemDto> outerNewItemDtoList = new ArrayList<>();
		
	private List<NewItemImgDto> NewItemImgDtoList = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public NewItem createNewItem() {
		return modelMapper.map(this, NewItem.class);
	}
	
	public static NewItemFormDto of(NewItem newItem) {
		return modelMapper.map(newItem, NewItemFormDto.class);
	}
}
