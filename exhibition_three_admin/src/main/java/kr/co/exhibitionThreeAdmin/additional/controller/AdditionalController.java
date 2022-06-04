package kr.co.exhibitionThreeAdmin.additional.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.exhibitionThreeAdmin.additional.service.AdditionalService;
import kr.co.exhibitionThreeAdmin.additional.service.LoginService;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;

/**
 * 추가적인 페이지
 */
@Controller
public class AdditionalController {
	@Autowired(required = false)
	private LoginService ls;
	@Autowired(required = false)
	private AdditionalService as;
	
	@RequestMapping(value = "/admin/index.do", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("cntAllMembers", as.countAllMember());
		model.addAttribute("cntTodayMember", as.cntTodayMember());
		model.addAttribute("cntShownRez", as.cntShownRez());
		model.addAttribute("cntAllRez", as.cntAllRez());
		model.addAttribute("cntTodayRez", as.cntTodayRez());
		
		return "index";
	}
	
	@RequestMapping(value = "/admin/settings.do", method = RequestMethod.GET)
	public String settings(Model model) {
		return "commons/settings";
	}
	
	@RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
	public String login(Model model,LoginVO lVO, String loginChk) {
		String createdDate = ls.loginChk(lVO);
		String reLog="index";
		
		if(createdDate==null) {
			reLog = "redirect:/admin/index.do";
			model.addAttribute("login", 1);
		}
		return reLog;
	}
	
	
}
