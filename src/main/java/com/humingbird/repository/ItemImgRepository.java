package com.humingbird.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humingbird.entity.NewItemImg;

public interface ItemImgRepository extends JpaRepository<NewItemImg, Long>{
	List<NewItemImg> findByItemIdOrderByIdAsc(Long itemId);
	
	//상품의 대표 이미지를 찾음
	NewItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);
}
