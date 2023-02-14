package com.humingbird.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.humingbird.constant.Category;
import com.humingbird.dto.ItemDto;
import com.humingbird.dto.ItemSearchDto;
import com.humingbird.dto.NewItemFormDto;
import com.humingbird.dto.NewItemImgDto;
import com.humingbird.dto.OuterNewItemDto;
import com.humingbird.dto.TopNewItemDto;
import com.humingbird.entity.BottomNewItem;
import com.humingbird.entity.NewItem;
import com.humingbird.entity.NewItemImg;
import com.humingbird.entity.OuterNewItem;
import com.humingbird.entity.TopNewItem;
import com.humingbird.repository.BottomNewItemRepository;
import com.humingbird.repository.ItemImgRepository;
import com.humingbird.repository.NewItemRepository;
import com.humingbird.repository.OuterNewItemRepository;
import com.humingbird.repository.TopNewItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NewItemService {
	private final NewItemRepository newItemRepository;
	private final BottomNewItemRepository bottomNewItemRepository;
	private final OuterNewItemRepository outerNewItemRepository;
	private final TopNewItemRepository topNewItemRepository;
	private final NewItemImgService newItemImgService;
	private final ItemImgRepository itemImgRepository;
	
	//신상 정보 이미지 등록 메소드
	public Long saveImg(List<MultipartFile> itemImgFileList , NewItem newItem) throws Exception {		
		//이미지 등록
		System.out.println(itemImgFileList.size());
		for(int i = 0;  i<itemImgFileList.size(); i++ ) {
			NewItemImg newItemImg = new NewItemImg();
			newItemImg.setNewitem(newItem); // 외래키등록
			
			//첫번째 이미지 일때 대표 상품 이미지 여부 지정
			if(i == 0) {

				newItemImg.setRepimgYn("Y");
			}else {
				newItemImg.setRepimgYn("N");
			}
	
			newItemImgService.saveItemImg(newItemImg, itemImgFileList.get(i));

		}
		
		return newItem.getId();
	}
	
	//하의 정보 db에 저장
	public void saveBottomItem(NewItem newItem,ArrayList<BottomNewItem> bottomNewItemList,List<MultipartFile> itemImgFileList) throws Exception {
		newItemRepository.save(newItem);
		saveImg(itemImgFileList,newItem);
		for(BottomNewItem bottomNewItem : bottomNewItemList) {
			bottomNewItemRepository.save(bottomNewItem);
		}
	}
	
	//상의 정보 db에 저장
	public void saveTopItem(NewItem newItem,ArrayList<TopNewItem> topNewItemList,List<MultipartFile> itemImgFileList) throws Exception {
		newItemRepository.save(newItem);
		saveImg(itemImgFileList,newItem);
		for(TopNewItem topNewItem : topNewItemList) {
			topNewItemRepository.save(topNewItem);
		}
	}
	
	//아우터 정보 db에 저장
	public void saveOuterItem(NewItem newItem, ArrayList<OuterNewItem> OuterNewItemList,List<MultipartFile> itemImgFileList) throws Exception {
		newItemRepository.save(newItem);
		saveImg(itemImgFileList,newItem);
		for(OuterNewItem outer : OuterNewItemList) {
			outerNewItemRepository.save(outer);
		}

	} 
	
	//페이지에 아이템 정보를 뿌려줌
	@Transactional(readOnly = true)
	public Page<ItemDto> getMainItemPage(ItemSearchDto itemserchDto,Pageable pageable) {
		return newItemRepository.getMainItemPage(itemserchDto,pageable);
	}
	
	//상품상세 정보가져오기
	@Transactional(readOnly = true)
	public NewItemFormDto getItemDtl(Long itemId) {
		//1. 아이템의 이미지를 테이블에서 가져온다.
		List<NewItemImg> newitemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
		List<NewItemImgDto>  newItemImgDtoList = new ArrayList<>();
		
		//엔티티 객체 -> dto객체로 변환
		for(NewItemImg newitemImg : newitemImgList) {
			NewItemImgDto newItemImgDto = NewItemImgDto.of(newitemImg);
			newItemImgDtoList.add(newItemImgDto);
		}
		
		//2. 신상정보테이블에 있는 데이터를 가져온다.
		NewItem newItem = newItemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
		
		// 신상정보테이블객체 entity -> dto객체로 변환
		NewItemFormDto newItemFormDto = NewItemFormDto.of(newItem);
		
		//상품의 사이즈별 정보를 가져온다.
		
		if(newItemFormDto.getCategory() == Category.OUTER) {//아우터
			//entity 리스트
			List<OuterNewItem> outerNewItemList = outerNewItemRepository.findByItemIdOrderByIdAsc(itemId);
			//dto를 담을 리스트
			List<OuterNewItemDto> outerNewItemDtoList = new ArrayList<>();
			//entity - dto 매핑
			for(OuterNewItem outerNewItem : outerNewItemList) {
				OuterNewItemDto outerNewItemDto = OuterNewItemDto.of(outerNewItem);
				outerNewItemDtoList.add(outerNewItemDto);
			}
			newItemFormDto.setOuterNewItemDtoList(outerNewItemDtoList);
		}else if(newItemFormDto.getCategory() == Category.TOP) {//탑
			//entity 리스트 가져오기
			List<TopNewItem> TopNewItemList = topNewItemRepository.findByItemIdOrderByIdAsc(itemId);
			//dto를 담을 리스트 만들기
			List<TopNewItemDto> topNewItemDtoList =new ArrayList<>();
			for(TopNewItem topNewItem : TopNewItemList) {
				
			}
		}else {//바텀
			
		}
		
	}
}
