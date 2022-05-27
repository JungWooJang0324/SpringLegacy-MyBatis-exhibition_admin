package kr.co.exhibitionThreeAdmin.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.exhibitionThreeAdmin.member.service.AdminMemberService;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Controller
public class AdminMemberController {
	@Autowired(required = false)
	private AdminMemberService ams;
	
	@RequestMapping(value="/admin/member.do",method=RequestMethod.GET)
	public String moveMember(Model model,SearchVO sVO) {
	//		if(sVO.getPageScale()==0 ) {
	//		sVO.setPageScale(10);
	//	}//end if
	//	if(sVO.getCurrentPage()==0) {
	//		sVO.setCurrentPage(1);
	//	}//end if
		int pageScale = sVO.getPageScale();
		int totalCnt = ams.searchTotalCnt(sVO);
		int pageCnt = ams.pageCnt(totalCnt, pageScale);
		int startNum = ams.startNum(sVO.getCurrentPage(),sVO.getPageScale());
		int endNum = ams.endNum(startNum, pageScale);
		int pageBlock = ams.pageBlock();
		int startPage = ams.startPage(sVO.getCurrentPage(), pageBlock);
		int endPage = ams.endPage(startPage, pageBlock);
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
	//	System.out.println(sVO);
		
		model.addAttribute("memberList",ams.searchMember(sVO));
		model.addAttribute("startNum",startNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("startNum",startNum);
		model.addAttribute("startPage",startPage);
		model.addAttribute("pageCnt",pageCnt);
		model.addAttribute("endPage",endPage);
		
		return "member/admin_member";
	}//moveMember
	
	
}//class
