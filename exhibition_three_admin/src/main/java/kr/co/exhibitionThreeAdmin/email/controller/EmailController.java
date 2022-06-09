package kr.co.exhibitionThreeAdmin.email.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.email.service.MailService;
import kr.co.exhibitionThreeAdmin.email.vo.EmailVO;
import kr.co.exhibitionThreeAdmin.member.domain.MemberDomain;
import kr.co.exhibitionThreeAdmin.member.vo.EsVO;

@Controller
public class EmailController {
	
	@Autowired
	private MailService ms;
	
	@RequestMapping(value="/admin/mail.do")
	public String email(Model model) {
		EsVO eVO = new EsVO("", "", "");
		model.addAttribute("idList",ms.selectId(eVO));
		return "email/mail_form";
	}//email
	
	@ResponseBody
	@RequestMapping(value="/admin/mail_send.do",method=RequestMethod.POST,produces="aplicataion/text;charset=UTF-8")
	public String sendMail(EmailVO eVO) {
		return ms.sendMail(eVO);
	}//sendMail
	
	@ResponseBody
	@RequestMapping(value="/admin/search.do",method=RequestMethod.POST,produces="aplicataion/text;charset=UTF-8")
	public String searchId(EsVO eVO) {
		List<MemberDomain> list =  ms.selectId(eVO);
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = null;
		for(int i = 0; i < list.size(); i++) {
			jsonObj = new JSONObject();
			jsonObj.put("userid",list.get(i).getUserid());
			jsonObj.put("name",list.get(i).getName());
			jsonArr.add(jsonObj);
		}//end for
		return jsonArr.toJSONString();
	}//searchId
}//class
