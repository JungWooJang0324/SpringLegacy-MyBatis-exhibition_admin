package kr.co.exhibitionThreeAdmin.board.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.exhibitionThreeAdmin.board.service.AdminBoardServie;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Controller
public class AdminBoardController {
	
	@Autowired(required =  false)
	private AdminBoardServie abs;
	
	//게시판 조회
	@RequestMapping(value = "/admin/board.do", method = GET)
	public String board(Model model, SearchVO sVO) {
		
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
