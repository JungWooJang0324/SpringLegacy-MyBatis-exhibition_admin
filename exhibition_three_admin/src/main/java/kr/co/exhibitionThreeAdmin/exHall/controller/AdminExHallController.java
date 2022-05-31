package kr.co.exhibitionThreeAdmin.exHall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.exHall.service.AdminExHallServie;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Controller
public class AdminExHallController {
	
	@Autowired(required =  false)
	private AdminExHallServie as;
	
	//전시장 조회
	@RequestMapping(value = "/admin/hall.do", method = GET)
	public String exHall(Model model, BHSearchVO sVO) {
		//전체 레코드의 수
		int totalCnt = as.countData(sVO);
		//한 화면에 보여줄 게시물의 수
		int pageScale = as.pageScale();
		//현재 페이지
		int currentPage=sVO.getCurrentPage();
		//페이지의 수
		int pageCnt = as.pageCnt(sVO);
		//시작 번호
		int startNum = as.startNum(currentPage, pageScale);
		//끝 번호 
		int endNum = as.endNum(startNum, pageScale);
		//페이지블럭 시작 번호
		int startPage = as.startPage(currentPage, pageScale);
		//페이지 블럭 끝 번호
		int endPage = as.endPage(startPage, pageScale, sVO);
		//이전 페이지 존재 유무
		boolean prev = as.prev(currentPage, pageScale);
		//다음 페이지 존재 유무
		boolean next = as.next(sVO, startPage, pageScale);
		//이전 페이지 시작 번호
		int prevNum = as.prevNum(currentPage, pageScale);
		//다음페이지 시작번호
		int nextNum = as.nextNum(currentPage, pageScale);
		
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageScale", pageScale);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("prevNum", prevNum);
		model.addAttribute("nextNum", nextNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRows", as.countData(sVO));
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
