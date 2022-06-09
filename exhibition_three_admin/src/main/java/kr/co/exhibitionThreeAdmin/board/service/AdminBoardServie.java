package kr.co.exhibitionThreeAdmin.board.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.board.dao.AdminBaordDAO;
import kr.co.exhibitionThreeAdmin.board.domain.AdminBoardDomain;
import kr.co.exhibitionThreeAdmin.board.vo.AdminBoardVO;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Component
public class AdminBoardServie {
	
	@Autowired(required =  false)
	private AdminBaordDAO abDAO;
	
	/**
	 * 전체 글 수
	 * @param sVO
	 * @return
	 */
	public int countData(BHSearchVO sVO) {
		int cnt = 0;
		
		try {
			setKeyword(sVO);
			cnt = abDAO.getTotalRows(sVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * 한 화면에 보여줄 페이지의 수
	 * @return
	 */
	public int pageScale() {
		int pageScale=5;
		return pageScale;
	}
	
	/**
	 * 모든 게시물을 보여주기 위한 페이지 수
	 * @return
	 */
	public int pageCnt(BHSearchVO sVO) {
		int pageCnt=0;
		pageCnt = (int)Math.ceil((double)countData(sVO)/pageScale());
		return pageCnt;
	}
	
	/**
	 * 해당 페이지 글의 시작번호 구하기
	 * @param currentPage 현재 페이지 번호
	 * @param pageScale 한 화면에 보여줄 페이지 수
	 * @return
	 */
	public int startNum(int currentPage, int pageScale) {
		int startNum =1;
		
		if(currentPage!=0) {
			startNum = currentPage*pageScale-pageScale+1;
		}
		return startNum;
	}
	
	/**
	 * 해당 페이지 글의 끝 번호 구하기
	 * @param startNum
	 * @param pageScale
	 * @return
	 */
	public int endNum(int startNum, int pageScale) {
		int endNum=0;
		endNum=startNum+pageScale-1;
		return endNum;
	}
	
	/**
	 * 페이지블럭 시작 번호
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	public int startPage(int currentPage, int pageScale) {
		int startPage=0;
		startPage = ((currentPage-1)/pageScale)*pageScale+1;
		return startPage;
	}
	
	/**
	 * 페이지 블럭 끝 번호
	 * @param startPage
	 * @param pageScale
	 * @return
	 */
	public int endPage(int startPage, int pageScale,BHSearchVO sV) {
		int endPage=startPage+pageScale-1;
		if(pageCnt(sV)<endPage) {
			endPage=pageCnt(sV);
		}
		return endPage;
	}
	
	/**
	 * 이전 페이지 존재 유무
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	public boolean prev(int currentPage, int pageScale) {
		boolean prevFlag = startPage(currentPage,pageScale) == 1?false : true;
		return prevFlag;
	}
	
	/**
	 * 다음 페이지 존재 유무
	 * @param sVO
	 * @param startPage
	 * @param pageScale
	 * @return
	 */
	public boolean next(BHSearchVO sVO, int startPage, int pageScale) {
		boolean nextFlag = endPage(startPage, pageScale,sVO) >= pageCnt(sVO)?false : true;
		return nextFlag;
	}
	
	
	//이전 페이지 시작 번호
	public int prevNum(int currentPage, int pageScale) {
		int prevNum = startPage(currentPage, pageScale) - pageScale();
		return prevNum;
	}
	
	//다음페이지 시작번호
	public int nextNum(int currentPage, int pageScale) {
		int nextNum = startPage(currentPage, pageScale) + pageScale();
		return nextNum;
	}
	
			
	/**
	 * 검색 키워드와 옵션 비교
	 * @param sVO
	 */
	public void setKeyword(BHSearchVO sVO) {
		if(!"".equals(sVO.getKeyword())) {
			String option="";
			int num = sVO.getOptNum();
			switch (num) {
				case 1: option="userid";break;
				case 2: option="input_date";break;
				case 3: option="cat_name";break;
				default: option="title";
			}
			sVO.setOption(option);
		}
	}
	
	/**
	 * 게시판 조회
	 * @param sVO
	 * @return
	 */
	public List<AdminBoardDomain> boardList(BHSearchVO sVO){
		List<AdminBoardDomain> list = null;
		
		try {
			setKeyword(sVO);
			list = abDAO.selectBoard(sVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 게시판 수정
	 * @param abVO
	 * @return
	 */
	public boolean modifyBoard(AdminBoardVO abVO) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = abDAO.updateBoard(abVO);
			if(cnt>0) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 게시판 삭제
	 * @param bdId
	 * @return
	 */
	public boolean removeBoard(int bdId) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = abDAO.deleteBoard(bdId);
			if(cnt>0) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 게시판 추가
	 * @param abVO
	 * @return
	 */
	public boolean addBoard(AdminBoardVO abVO) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = abDAO.insertBoard(abVO);
			if(cnt>0) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 게시판 상세
	 * @param bdId
	 * @return
	 */
	public AdminBoardDomain boardDetail(int bdId) {
		AdminBoardDomain abDomain = null;
		try {
			abDomain = abDAO.selectBoardDetail(bdId);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return abDomain;
	}
	
	/**
	 * 카테고리 
	 * @return
	 */
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
