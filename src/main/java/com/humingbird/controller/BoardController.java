package com.humingbird.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;




import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {


	@GetMapping("/view")
	// 게시물 작성 폼으로 이동하는 컨트롤러
	public String boardview() {
		return "board/boardview";
	}

	@GetMapping("/write")
	// 게시물 작성 폼으로 이동하는 컨트롤러
	public String boardWroteForm() {
		return "board/boardwrite";
	}


}
