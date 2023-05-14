package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.services.ModeOfWorkService;

@RestController
@RequestMapping("/api/deskbook")
public class ModeOfWorkController {

	@Autowired
	private ModeOfWorkService modeOfWorkService;
	 
	@GetMapping("/mode-of-works")
	public List<ModeOfWorkDto> findAllModeOfWork() {
		return modeOfWorkService.findAllModeOfWork();
	}
}
