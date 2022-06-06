package kr.co.exhibitionThreeAdmin.exHall.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.dao.AdminExHallDAO;
import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exHall.vo.AdminExHallVO;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Component
public class AdminExHallServie {
	
	@Autowired(required =  false)
	private AdminExHallDAO aehDAO;
	
	/**
	 * 전체 글 수
	 * @param sVO
	 * @return
	 */
	public int countData(BHSearchVO sVO) {
		int cnt = 0;
		
		try {
			setKeyword(sVO);
			cnt = aehDAO.getTotalRows(sVO);
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
		int startPage=1;
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
		boolean prevFlag = startPage(currentPage, pageScale) == 1?false : true;
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
		//검색 key와 option 설정
		if(!"".equals(sVO.getKeyword())) {
			String option="ex_hall_name";
			if("1".equals(sVO.getOption())||"ex_loc".equals(sVO.getOption())) {
				option="ex_loc";
			}
			sVO.setOption(option);
		}
	}
	
	//전시장 조회
	public List<AdminExHallDomain> exHallList(BHSearchVO sVO){
		List<AdminExHallDomain> list = null;
		
		try {
			setKeyword(sVO);
			list = aehDAO.selectExHall(sVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * 전시장 수정
	 * @param aaehVO
	 * @return
	 */
	public boolean modifyExHall(AdminExHallVO aehVO) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = aehDAO.updateExHall(aehVO);
			if(cnt==1) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 전시장 삭제
	 * @param exHallNum
	 * @return
	 */
	public boolean removeExHall(int exHallNum) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = aehDAO.deleteExHall(exHallNum);
			if(cnt==1) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 전시장 추가
	 * @param aehVO
	 * @return
	 */
	public boolean addExHall(AdminExHallVO aehVO) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = aehDAO.insertExHall(aehVO);
			if(cnt==1) {
				flag = true;
			}
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 전시장 상세
	 * @param exHallNum
	 * @return
	 */
	public AdminExHallDomain exHallDetail(int exHallNum) {
		AdminExHallDomain aehDomain = null;
		try {
			aehDomain = aehDAO.selectExHallDetail(exHallNum);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return aehDomain;
	}
	
}
