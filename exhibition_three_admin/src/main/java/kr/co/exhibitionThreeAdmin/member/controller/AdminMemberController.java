package kr.co.exhibitionThreeAdmin.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.member.service.AdminMemberService;
import kr.co.exhibitionThreeAdmin.member.vo.MemberVO;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Controller
public class AdminMemberController {
	@Autowired(required = false)
	private AdminMemberService ams;
	
	@RequestMapping(value="/admin/member.do",method=GET)
	public String memberMain(Model model,SearchVO sVO) {
	//		if(sVO.getPageScale()==0 ) {
	//		sVO.setPageScale(10);
	//	}//end if
	//	if(sVO.getCurrentPage()==0) {
	//		sVO.setCurrentPage(1);
	//	}//end if
		int pageScale = sVO.getPageScale();
		int totalCnt = ams.searchTotalCnt(sVO);
		int pageCnt = ams.pageCnt(totalCnt, pageScale);
		int currentPage = sVO.getCurrentPage();
		int startNum = ams.startNum(currentPage,sVO.getPageScale());
		int endNum = ams.endNum(startNum, pageScale);
		int pageBlock = ams.pageBlock();
		int startPage = ams.startPage(currentPage, pageBlock);
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
		model.addAttribute("totalCnt",totalCnt);
		model.addAttribute("pageBlock",pageBlock);
		model.addAttribute("currentPage",currentPage);
		
		return "member/admin_member";
	}//moveMember
	
	@ResponseBody
	@RequestMapping(value="/admin/detailMember.do",method=GET,produces = "application/json;charset=UTF-8")
	public String detailMember(String userid) {
		
		return ams.searchDetail(userid);
	}//detailMember
	
	@ResponseBody
	@RequestMapping(value="/admin/updateMember.do",method=POST, produces="application/json;charset=UTF-8")
	public String modifyMember(MemberVO mVO) {
		return ams.modifyMember(mVO);
	}//modifyMember
}//class
