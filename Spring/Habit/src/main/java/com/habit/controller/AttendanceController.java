package com.habit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.habit.domain.AttendanceDTO;
import com.habit.domain.AttendanceVO;
import com.habit.service.AttendanceService;
import com.habit.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
@RequestMapping("/attend/*")
public class AttendanceController {
	
	private AttendanceService service;
	
	
	@GetMapping(value = "/{m_num}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<List<AttendanceVO>> getList(@PathVariable("m_num") Long m_num) {
		log.info("되긴 하냐?" + service.read(m_num));
		
		return new ResponseEntity<List<AttendanceVO>>(service.read(m_num),HttpStatus.OK);
	}
	
	@GetMapping(value = "/category/{m_num}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<List<AttendanceDTO>> getListWithCategory(@PathVariable("m_num") Long m_num) {
		log.info("되긴 하냐?" + service.readWithCategory(m_num));
		
		return new ResponseEntity<List<AttendanceDTO>>(service.readWithCategory(m_num),HttpStatus.OK);
	}
}
