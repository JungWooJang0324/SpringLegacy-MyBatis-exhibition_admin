package kr.co.exhibitionThreeAdmin.additional.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.exhibitionThreeAdmin.additional.service.LoginService;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;

@Controller
public class LoginController {
	@Autowired(required = false)
	private LoginService ls;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping(value="/admin/login.do", method= {GET, POST})
	public String login() {
		return "commons/login";
	}
	
	@RequestMapping(value="/admin/loginChk.do", method= POST)
	public String loginChk(HttpSession session, String id,
											String password,
											Model model) {
		String page="";
		LoginVO lvo = new LoginVO();
		lvo.setAdmin_id(id);
		lvo.setPassword(password);

		if(ls.loginChk(lvo)==1) {
			session.setAttribute("id", id);
			page = "redirect: index.do";
		}else {
			model.addAttribute("loginFail", 1);
			page="forward: login.do";
		}
		
		return page;
	}
	
	@RequestMapping(value="admin/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "forward: login.do";
	}
}
