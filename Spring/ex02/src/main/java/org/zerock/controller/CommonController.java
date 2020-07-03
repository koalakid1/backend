package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/test")
	public void test() {}
}
