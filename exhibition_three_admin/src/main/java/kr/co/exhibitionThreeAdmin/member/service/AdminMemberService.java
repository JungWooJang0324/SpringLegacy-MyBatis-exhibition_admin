package kr.co.exhibitionThreeAdmin.member.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.member.dao.AdminMemberDAO;
import kr.co.exhibitionThreeAdmin.member.domain.MemberDomain;
import kr.co.exhibitionThreeAdmin.search.service.SearchService;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminMemberService implements SearchService {
	@Autowired(required = false)
	private AdminMemberDAO aDAO; //의존성 주입
	
	@Override
	public int searchTotalCnt(SearchVO sVO) {
		int cnt = 0;
		try {
			setField(sVO);
		cnt = aDAO.selectTotalCnt(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return cnt;
	}

	@Override
	public int pageCnt(int totalCnt, int pageScale) {
		int pageCnt = 0;
		pageCnt = (int)Math.ceil((double)totalCnt/pageScale);
	//	System.out.println(totalCnt+"/ "+pageCnt);
//		if(totalCnt%pageScale!=0) {
//			pageCnt++;
//		}//end if
		return pageCnt;
	}



	@Override
	public int startNum(int currentPage, int pageScale) {
		int startNum=1;
		
		startNum = currentPage*pageScale-pageScale+1;
		
		return startNum;
	}



	@Override
	public int endNum(int startNum, int pageScale) {
		int endNum=0;
		endNum = startNum+pageScale-1;
		return endNum;
	}



	@Override
	public int startPage(int currentPage, int pageBlock) {
		int startPage = 0;
		startPage =	((currentPage-1)/pageBlock)*pageBlock+1;
		return startPage;
	}



	@Override
	public int endPage(int startPage, int pageBlock) {
		int endPage=0;
		endPage=startPage + pageBlock - 1;//마지막 페이지 번호
		return endPage;
	}



	@Override
	public int pageBlock() {
		int pageBlock=5;//한번에 보여줄 페이지 번호 갯수
		return pageBlock;
	}

	/**
	 * 검색항목 번호를 입력받아 컬럼명을 구한다.
	 * @param sVO
	 */
	private void setField(SearchVO sVO) {
		
		String field="address1";
		String fieldNum = sVO.getField();
		if(!"".equals(fieldNum)) {
			if("1".equals(fieldNum) || "name".equals(fieldNum)) {
				field="name";
			}else if("2".equals(fieldNum) || "userid".equals(fieldNum)) {
				field="userid";
			}//end else
		}//end if
		sVO.setField(field);
	}//setField


	public List<MemberDomain> searchMember(SearchVO sVO){
		List<MemberDomain> list = null;
		try {
			setField(sVO);
			list = aDAO.selectMember(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchMember
	
	
	
}//class
