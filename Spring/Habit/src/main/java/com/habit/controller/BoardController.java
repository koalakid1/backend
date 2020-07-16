package com.habit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.habit.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("List를 가져옵니다.-------------");
		
		model.addAttribute("list", service.getList());
		log.info(service.getList());
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("b_num") Long b_num, Model model) {
		log.info(service.get(b_num));
		model.addAttribute("board",service.get(b_num));
	}
}
