package com.habit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.habit.domain.BoardVO;
import com.habit.service.AttendanceService;
import com.habit.service.BoardService;
import com.habit.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Setter(onMethod_= @Autowired)
	private BoardService boardService;
	
	@Setter(onMethod_= @Autowired)
	private CategoryService categoryService;
	
	@Setter(onMethod_= @Autowired)
	private AttendanceService attendanceService;
	
	@GetMapping("/list")
	public void list( Model model) {
		log.info("List를 가져옵니다.-------------");
		
		model.addAttribute("list", boardService.getList());
		log.info(boardService.getList());
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("b_num") Long b_num, Model model) {
		log.info(boardService.get(b_num));
		model.addAttribute("board",boardService.get(b_num));
	}
	
	@GetMapping("/register")
	public void register(@RequestParam("m_num") Long m_num,Model model) {
		model.addAttribute("categoryList", categoryService.getCategory());
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("=====================================");
		log.info("register: " + board);
		
		log.info("=====================================");
		
		boardService.register(board);
		attendanceService.;
		rttr.addFlashAttribute("result", board.getB_num());
		return "redirect:/board/list"; //주소를 바꿀때는 redirect:/를 붙여야함
	}
}
