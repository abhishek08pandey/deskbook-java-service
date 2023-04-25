package com.onerivet.deskbook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deskbook")
public class DeskBookController {
	
	@GetMapping
	public Object test() {
		
		return """
				{
				  "message":"this a deskbook api"
				}
				""";
	}

}
