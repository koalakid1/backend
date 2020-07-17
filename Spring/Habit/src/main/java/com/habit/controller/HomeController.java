package com.habit.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habit.service.AttendanceService;
import com.habit.service.BoardService;
import com.habit.service.MemberService;
import com.habit.service.HomeService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Setter(onMethod_=@Autowired )
	private AttendanceService attendanceService;
	
	@Setter(onMethod_=@Autowired )
	private MemberService memberService;
	
	@Setter(onMethod_=@Autowired )
	private BoardService boardService;
	
	@Setter(onMethod_=@Autowired )
	private HomeService homeService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("list", homeService.getListBestBoard());
		log.info(homeService.getListBestBoard());
		
		return "home";
	}
	
	@GetMapping("/myPage")
	public void myPage(@RequestParam("m_num") Long m_num, Model model) {
		log.info(m_num);
		model.addAttribute("member", memberService.readMember(m_num));
		model.addAttribute("myBoardList", boardService.getListMyBoard(m_num));
		model.addAttribute("likeBoardList", boardService.getListLikeBoard(m_num));
		
	}
}
