package kr.co.exhibitionThreeAdmin.exHall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.exHall.service.AdminExHallServie;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Controller
public class AdminExHallController {
	
	@Autowired(required =  false)
	private AdminExHallServie as;
	
	//전시장 조회
	@RequestMapping(value = "/admin/hall.do", method = GET)
	public String exHall(Model model, SearchVO sVO) {
		
		model.addAttribute("exHallList", as.exHallList(sVO));
		
		return "exHall/hall";
	}
	
	//전시장 추가 폼
	@ResponseBody
	@RequestMapping(value = "", method = {GET, POST})
	public String formAddExHall() {
		
		return "";
	}
}
