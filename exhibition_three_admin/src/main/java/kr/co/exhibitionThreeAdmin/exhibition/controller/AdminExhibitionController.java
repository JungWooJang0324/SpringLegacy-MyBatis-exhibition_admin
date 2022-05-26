package kr.co.exhibitionThreeAdmin.exhibition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminExhibitionController {
	@RequestMapping(value="/exhibition.do",method=RequestMethod.GET)
	public String moveExhibition() {
		return "admin_exhibition";
	}//moveExhibition
}
