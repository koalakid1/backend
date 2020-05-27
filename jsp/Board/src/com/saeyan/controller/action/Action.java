package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//추상메서드 선언
	public void execute(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException;
}
