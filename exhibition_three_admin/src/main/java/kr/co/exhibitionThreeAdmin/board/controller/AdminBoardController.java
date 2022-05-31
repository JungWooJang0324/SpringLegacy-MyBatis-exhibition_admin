package kr.co.exhibitionThreeAdmin.board.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.exhibitionThreeAdmin.board.service.AdminBoardServie;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Controller
public class AdminBoardController {
	
	@Autowired(required =  false)
	private AdminBoardServie abs;
	
	//게시판 조회
	@RequestMapping(value = "/admin/board.do", method = GET)
	public String board(Model model, BHSearchVO sVO) {
		//전체 레코드의 수
		int totalCnt = abs.countData(sVO);
		//한 화면에 보여줄 게시물의 수
		int pageScale = abs.pageScale();
		//현재 페이지
		int currentPage=sVO.getCurrentPage();
		//페이지의 수
		int pageCnt = abs.pageCnt(sVO);
		//시작 번호
		int startNum = abs.startNum(currentPage, pageScale);
		//끝 번호 
		int endNum = abs.endNum(startNum, pageScale);
		//페이지블럭 시작 번호
		int startPage = abs.startPage(currentPage, pageScale); 
		//페이지 블럭 끝 번호
		int endPage = abs.endPage(startPage, pageScale,sVO);
		//이전 페이지 존재 유무
		boolean prev = abs.prev(currentPage, pageScale);
		//다음 페이지 존재 유무
		boolean next = abs.next(sVO, startPage, pageScale);
		//이전 페이지 시작 번호
		int prevNum = abs.prevNum(currentPage, pageScale);
		//다음페이지 시작번호
		int nextNum = abs.nextNum(currentPage, pageScale);

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
		model.addAttribute("totalRows", abs.countData(sVO));
		model.addAttribute("boardList", abs.boardList(sVO));
		
		return "board/board";
	}
	
	//게시판 추가 폼
	@RequestMapping(value = "/admin/addBoard.do", method = {GET})
	public String form(HttpSession session, Model model) {
		
		return "board/addBoard";
	}
	
	//게시판
}
