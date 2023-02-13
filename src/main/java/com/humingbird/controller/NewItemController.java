package com.humingbird.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.humingbird.constant.Category;
import com.humingbird.dto.BottomNewItemDto;
import com.humingbird.dto.NewItemFormDto;
import com.humingbird.dto.OuterNewItemDto;
import com.humingbird.dto.TopNewItemDto;
import com.humingbird.entity.BottomNewItem;
import com.humingbird.entity.NewItem;
import com.humingbird.entity.OuterNewItem;
import com.humingbird.entity.TopNewItem;
import com.humingbird.service.MemberService;
import com.humingbird.service.NewItemService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/newItem")
@Controller
@RequiredArgsConstructor
public class NewItemController {

	private final NewItemService newItemService;

	@GetMapping(value = "/newItemView")
	public String main() {
		return "newItem/nItemMain";
	}

	// 신상정보등록 페이지
	@GetMapping("/nItemForm")
	public String nItemForm(Model model) {
		model.addAttribute("newItemFormDto", new NewItemFormDto());

		return "newItem/nItemForm";
	}

	// 일단 아이템 저장 테스트
	@PostMapping("/test")
	@ResponseBody String test(@RequestBody @Validated NewItemFormDto newItemFormDto, BindingResult bindingResult
			, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {
			System.out.println("처음부터인가?");

			if(bindingResult.hasErrors()) {
				return "newItem/nItemForm";
			}
			
			//첫번째 이미지가 있는지 검사
			if(itemImgFileList.get(0).isEmpty() && newItemFormDto.getId() == null) {
				model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
				return "newItem/nItemForm";
			}
			
			//맵퍼로 dto와 entity 매핑
			NewItem newItem = newItemFormDto.createNewItem();
			

			try {
				if (newItemFormDto.getCategory() == Category.OUTER) {//아우터 테이블입력

					ArrayList<OuterNewItem> outerNewItemList = new ArrayList<>();

					for (OuterNewItemDto dto : newItemFormDto.getOuterNewItemDtoList()) {
						OuterNewItem outerNewItem = OuterNewItem.createouterNewItem(dto, newItem);
						outerNewItemList.add(outerNewItem);
					}

					newItemService.saveOuterItem(newItem, outerNewItemList,itemImgFileList);
					
				} else if (newItemFormDto.getCategory() == Category.TOP) {// 상의 테이블 입력

					ArrayList<TopNewItem> topNewItemList = new ArrayList<>();

					for (TopNewItemDto dto : newItemFormDto.getTopNewItemDtoList()) {
						TopNewItem topNewItem = TopNewItem.createTopNewItem(dto, newItem);
						topNewItemList.add(topNewItem);
					}
					newItemService.saveTopItem(newItem, topNewItemList,itemImgFileList);

				} else { // 하의 테이블 입력

					ArrayList<BottomNewItem> bottomNewItemList = new ArrayList<>();

					for (BottomNewItemDto dto : newItemFormDto.getBottomNewItemDtoList()) {
						BottomNewItem bottomNewItem = BottomNewItem.createBottomNewItem(dto, newItem);
						bottomNewItemList.add(bottomNewItem);
					}
					newItemService.saveBottomItem(newItem, bottomNewItemList,itemImgFileList);
				}

			} catch (Exception e) {
				model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
				return "item/itemForm";
			}
		
		return "main";
	}
}
