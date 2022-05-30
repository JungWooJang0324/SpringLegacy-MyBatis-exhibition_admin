package kr.co.exhibitionThreeAdmin.board.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.board.domain.AdminBoardDomain;
import kr.co.exhibitionThreeAdmin.board.vo.AdminBoardVO;
import kr.co.exhibitionThreeAdmin.exHall.vo.AdminExHallVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminBaordDAO {
	
	//전체 글 수
	public int getTotalRows(AdminBoardVO adVO) {
		int cntRows =0;
		
		return cntRows;
	}
	
	//전시장 조회
	public List<AdminBoardDomain> selectBoard(SearchVO sVO) throws PersistenceException{
		List<AdminBoardDomain> list = null;
			
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhbition.board.selectBoard", sVO); 	
		System.out.println(list);
		if(ss!=null) {ss.close();}
		
		return list;
	}
	
	//전시장 수정
	public int updateBoard(AdminBoardVO abVO) {
		int cnt =0;
		
		return cnt;
	}
	
	//전시장 삭제
	public int deleteBoard(int bdId) {
		int cnt =0;
		
		return cnt;
	}
	
	//전시장 추가
	public int insertBoard(AdminBoardVO abVO) {
		int cnt =0;
		
		return cnt;
	}
	
	//전시장 상세
	public AdminExHallVO selectBoardDetail(int bdId) {
		AdminExHallVO eVO = null;
		
		return eVO;
	}
	
	//카테고리 전체
	public List<AdminBoardDomain> selectCategory(){
		List<AdminBoardDomain> list = null;
		
		return list;
	}
	
}
