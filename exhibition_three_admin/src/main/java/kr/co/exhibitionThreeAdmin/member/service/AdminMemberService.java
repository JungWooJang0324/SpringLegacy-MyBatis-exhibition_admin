package kr.co.exhibitionThreeAdmin.member.service;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.member.dao.AdminMemberDAO;
import kr.co.exhibitionThreeAdmin.member.domain.MemberDomain;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminMemberService {
	@Autowired(required = false)
	private AdminMemberDAO aDAO; //의존성 주입
	
	/**
	 * 전체 레코드 수
	 * @return
	 */
	public int searchTotalCount() {
		int cnt = 0;
		try {
			
		cnt = aDAO.selectTotalCount();
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return cnt;
	}//searchTotalCoung
	
	
	/**
	 * 모든 게시물을 보여주기 위한 페이지 수(총 페이지 수 )
	 * @param totalCnt
	 * @param pageScale
	 * @return
	 */
	public int pageCnt(int totalCnt, int pageScale) {
		int pageCnt = totalCnt/pageScale;
		pageCnt = (int)Math.ceil((double)totalCnt/pageScale);
//		if(totalCnt%pageScale!=0) {
//			pageCnt++;
//		}//end if
		return pageCnt;
	}//pageCnt
	
	/**
	 * 시작번호 구하기
	 * @param currentPage - 현재 페이지 번호
	 * @param pageScale - 한 화면에 보여줄 페이지 수
	 * @return
	 */
	public int startNum(SearchVO sVO) {
		int startNum=1;
		int currentPage = sVO.getCurrentPage();
		int pageScale = sVO.getPageScale();
		
		startNum = currentPage*pageScale-pageScale+1;
		return startNum;
	}//startNum
	
	/**
	 * 끝번호 구하기
	 * @param startNum
	 * @param pageScale
	 * @return
	 */
	public int endNum(int startNum,int pageScale) {
		int endNum=0;
		endNum = startNum+pageScale-1;
		return endNum;
	}//endNum
	
	/**
	 * 시작 페이지 번호
	 * @param currentPage
	 * @param pageBlock
	 * @return
	 */
	public int startPage(int currentPage,int pageBlock) {
		int startPage = 0;
		startPage =	((currentPage-1)/pageBlock)*pageBlock+1;
		return startPage;
	}//startPage
	
	public int endPage(int startPage, int pageBlock) {
		int endPage=0;
		endPage=startPage + pageBlock - 1;//마지막 페이지 번호
		return endPage;
	}//endPage
	
	public int pageBlock() {
		int pageBlock=5;//한번에 보여줄 페이지 번호 갯수
		return pageBlock;
	}//pageBlock
	
	public List<MemberDomain> searchMember(SearchVO sVO){
		List<MemberDomain> list = null;
		try {
			list = aDAO.selectMember(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchMember
	
	
}//class
