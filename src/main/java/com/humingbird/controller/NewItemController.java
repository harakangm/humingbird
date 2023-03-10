package com.humingbird.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.humingbird.constant.Category;
import com.humingbird.dto.BottomNewItemDto;
import com.humingbird.dto.ItemDto;
import com.humingbird.dto.ItemSearchDto;
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
	public String main(ItemSearchDto itemserchDto, Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
		Page<ItemDto> items = newItemService.getMainItemPage(itemserchDto,pageable);
		
		model.addAttribute("itemserchDto", itemserchDto);
		model.addAttribute("items", items);
		model.addAttribute("maxPage", 5);
		
		return "newItem/nItemMain";
	}

	// ?????????????????? ?????????
	@GetMapping("/nItemForm")
	public String nItemForm(Model model) {
		model.addAttribute("newItemFormDto", new NewItemFormDto());

		return "newItem/nItemForm";
	}

	// ?????? ????????? ?????? ?????????
	@PostMapping(value =  "/test" )
	@ResponseBody public ResponseEntity newItemSave(@RequestPart("dto") @Valid NewItemFormDto newItemFormDto, BindingResult bindingResult
			, @RequestPart(value = "ImgFile",required = false) List<MultipartFile> itemImgFileList, Model model,Principal principal) {
				
		
	       if(bindingResult.hasErrors()){
	            StringBuilder sb = new StringBuilder();
	            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

	            for (FieldError fieldError : fieldErrors) {
	                sb.append(fieldError.getDefaultMessage());
	            }

	            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
	        }
			
			//????????? ???????????? ????????? ??????
			if(itemImgFileList.get(0).isEmpty() && newItemFormDto.getId() == null) {
				model.addAttribute("errorMessage", "????????? ?????? ???????????? ?????? ?????? ??? ?????????.");
	
			}
			
			//????????? dto??? entity ??????
			NewItem newItem = newItemFormDto.createNewItem();

			try {
				if (newItemFormDto.getCategory() == Category.OUTER) {//????????? ???????????????

					ArrayList<OuterNewItem> outerNewItemList = new ArrayList<>();

					for (OuterNewItemDto dto : newItemFormDto.getOuterNewItemDtoList()) {
						OuterNewItem outerNewItem = OuterNewItem.createouterNewItem(dto, newItem);
						outerNewItemList.add(outerNewItem);
					}

					newItemService.saveOuterItem(newItem, outerNewItemList,itemImgFileList);
					
				} else if (newItemFormDto.getCategory() == Category.TOP) {// ?????? ????????? ??????

					ArrayList<TopNewItem> topNewItemList = new ArrayList<>();

					for (TopNewItemDto dto : newItemFormDto.getTopNewItemDtoList()) {
						TopNewItem topNewItem = TopNewItem.createTopNewItem(dto, newItem);
						topNewItemList.add(topNewItem);
					}
					newItemService.saveTopItem(newItem, topNewItemList,itemImgFileList);

				} else { // ?????? ????????? ??????
					
					ArrayList<BottomNewItem> bottomNewItemList = new ArrayList<>();

					for (BottomNewItemDto dto : newItemFormDto.getBottomNewItemDtoList()) {
						BottomNewItem bottomNewItem = BottomNewItem.createBottomNewItem(dto, newItem);
						bottomNewItemList.add(bottomNewItem);
					}
					
					newItemService.saveBottomItem(newItem, bottomNewItemList,itemImgFileList);
				}

			} catch (Exception e) {
				model.addAttribute("errorMessage", "????????? ????????? ?????? ??????.");
	            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		
			   return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/item/{itemId}")
	public String itemDtl(Model model, @PathVariable("itemId") Long itemId) {
		NewItemFormDto newItemFormDto;
	}
}
