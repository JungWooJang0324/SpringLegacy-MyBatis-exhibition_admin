package kr.co.exhibitionThreeAdmin.additional.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value="/error404.do")
	public String Err404() {
		return "err/admin_error404";
	}//Err404
	
	@RequestMapping(value="/error500.do")
	public String Err500() {
		return "err/admin_error500";
	}
}//class
