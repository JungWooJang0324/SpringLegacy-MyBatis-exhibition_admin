package kr.co.exhibitionThreeAdmin.exhibition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.exhibitionThreeAdmin.exhibition.domain.ExhibitionDomain;
import kr.co.exhibitionThreeAdmin.exhibition.service.AdminExhibitionService;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;
@Component
@Controller
public class AdminExhibitionController {
	@Autowired(required = false)
	AdminExhibitionService as;
	
	@RequestMapping(value="/admin/exhibitions.do",method=RequestMethod.GET)
	public String exhibitionMain(Model model,SearchVO sVO) {
		List<ExhibitionDomain> list = null;
		list= as.searchExhibition(sVO);
		
		model.addAttribute("exhibitionList",list);
		return "exhibitions/exhibitionSchedule";
	}//moveExhibition
}
