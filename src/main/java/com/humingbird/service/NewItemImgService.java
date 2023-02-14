package com.humingbird.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.humingbird.entity.NewItemImg;
import com.humingbird.repository.ItemImgRepository;
import com.humingbird.repository.NewItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NewItemImgService {
	@Value("${itemImgLocation}") //프로퍼티의 밸류를 읽어온다 = //C:/shop/item
	private String itemImgLocation;
	
	private final ItemImgRepository newItemImgRepository;
	
	private final FileService fileService;
	
	//이미지 저장 메소드
	public void saveItemImg(NewItemImg newItemImg,MultipartFile itemImgFile) throws Exception {
		String oriImgName = itemImgFile.getOriginalFilename(); //파일 이름
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		//상품 이미지 정보 저장
		newItemImg.updateItemImg(oriImgName, imgName, imgUrl);
		newItemImgRepository.save(newItemImg);
	}
}
