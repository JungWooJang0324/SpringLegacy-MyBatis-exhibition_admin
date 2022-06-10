package kr.co.exhibitionThreeAdmin.additional.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.bootstrap.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.exhibitionThreeAdmin.additional.service.AdditionalService;
import kr.co.exhibitionThreeAdmin.additional.service.LoginService;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.exhibition.service.AdminExhibitionService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 추가적인 페이지
 */
@SessionAttributes("id")
@Controller
public class AdditionalController{

	@Autowired(required = false)
	private AdditionalService as;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping(value = "/admin/index.do", method = GET)
	public String index(Model model) {
		model.addAttribute("cntAllMembers", as.countAllMember());
		model.addAttribute("cntTodayMember", as.cntTodayMember());
		model.addAttribute("cntShownRez", as.cntShownRez());
		model.addAttribute("cntAllRez", as.cntAllRez());
		model.addAttribute("cntTodayRez", as.cntTodayRez());
		model.addAttribute("cntTodayBoard", as.cntTodayBoard());
		model.addAttribute("cntAllEx", as.cntAllExhibition());
		model.addAttribute("endedEx", as.endedExhibition());
		model.addAttribute("endsTomorrow", as.endsTomorrow());
		return "index";
	}
	
	@RequestMapping(value = "/admin/settings.do", method = GET)
	public String settings(Model model) {
		return "commons/settings";
	}
	
	@RequestMapping(value="/admin/password.do", method= {POST, GET})
	public String password() {
		return "commons/password";
	}
	@RequestMapping(value="/admin/passwordChk.do", method=POST)
	public String passwordChk(String password,HttpSession session, Model model) {
		LoginVO lvo = new LoginVO();
		lvo.setAdmin_id((String) session.getAttribute("id"));
		lvo.setPassword(password);
		String page = "commons/passwordReset";
		
		if(session.getAttribute("id")==null) {
			page = "redirect: login.do";
		}
		
		int cnt = as.checkPw(lvo);
		
		if(cnt != 1) {
			model.addAttribute("msg", false);
			page = "commons/password";
		}
		return page;
	}
	
	@RequestMapping(value = "/admin/resetPass.do", method= POST)
	public String resetPass(String newPassword,HttpSession session) {
		String pass=encoder.encode(newPassword);

		LoginVO lvo = new LoginVO();
		lvo.setAdmin_id((String) session.getAttribute("id"));
		lvo.setPassword(pass);
		
		as.updatePw(lvo);
		
		return "redirect:logout.do";
		
	}
	
	
	
}
