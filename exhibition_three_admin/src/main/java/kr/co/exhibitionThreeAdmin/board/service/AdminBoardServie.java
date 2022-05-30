package kr.co.exhibitionThreeAdmin.board.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.board.dao.AdminBaordDAO;
import kr.co.exhibitionThreeAdmin.board.domain.AdminBoardDomain;
import kr.co.exhibitionThreeAdmin.board.vo.AdminBoardVO;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminBoardServie {
	
	@Autowired(required =  false)
	private AdminBaordDAO abDAO;
	
	//전체 글 수
	public int countData(AdminBoardVO abVO) {
		int cnt = 0;
		
		
		return cnt;
	}
	
	//게시판 조회
	public List<AdminBoardDomain> boardList(SearchVO sVO){
		List<AdminBoardDomain> list = null;
		
		try {
			list = abDAO.selectBoard(sVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return list;
	}
	
	//게시판 수정
	public int modifyBoard(AdminBoardVO abVO) {
		int cnt = 0;
		
		return cnt;
	}
	
	//게시판 삭제
	public int removeBoard(int BdId) {
		int cnt = 0;
		
		return cnt;
	}
	
	//게시판 추가
	public int addBoard(AdminBoardVO abVO) {
		int cnt = 0;
		
		return cnt;
	}
	
	//게시판 상세
	public AdminBoardVO boardDetail(int bdId) {
		AdminBoardVO abVO = null;
		
		return abVO;
	}
	
	//카테고리 
	public List<AdminBoardDomain> categoryList() {
		List<AdminBoardDomain> list = null;
		
		try {
			list = abDAO.selectCategory();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return list;
	}
}
