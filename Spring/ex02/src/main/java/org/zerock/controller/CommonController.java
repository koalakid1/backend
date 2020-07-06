package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		model.addAttribute("msg", "접근권한이 없습니다.");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "로그인 에러가 발생");
		}
		if(logout != null) {
			model.addAttribute("logout", "로그아웃 되었습니다.");
		}
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		//csrf 토큰 생성 후 세션변수에 저장하고 csrf토큰을 jsp 페이지로 전달
	}
	@PostMapping("/customLogout")
	public void logoutPost() {
		//jsp에서 넘어온 csrf토큰값과 세션변수의 토큰값이 일치하는지 검증
	}
}
