package com.humingbird.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.humingbird.dto.ItemDto;
import com.humingbird.dto.ItemSearchDto;


public interface NewItemRepositoryCustom {
	
	
	//메인화면에 뿌리는 아이템을 가져온다.
	Page<ItemDto> getMainItemPage(ItemSearchDto itemserchDto,Pageable pageable);
}
