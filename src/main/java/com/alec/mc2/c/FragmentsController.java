package com.alec.mc2.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {

	@GetMapping("/")
	public String getHome() {
		return "index.html";
	}
	
	@GetMapping("/index.html")
	public String getHome1() {
		return "index.html";
	}	

}
