package com.humingbird.dto;

import org.modelmapper.ModelMapper;

import com.humingbird.entity.NewItemImg;

public class NewItemImgDto {
	
	private Long id;
	
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	private String repimgYn; //대표 이미지 여부
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//맵핑해줌
	public static NewItemImgDto of(NewItemImg itemImg) {
		return modelMapper.map(itemImg, NewItemImgDto.class);
	}
}
