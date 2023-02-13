package com.humingbird.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.humingbird.dto.NewItemFormDto;
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
}
