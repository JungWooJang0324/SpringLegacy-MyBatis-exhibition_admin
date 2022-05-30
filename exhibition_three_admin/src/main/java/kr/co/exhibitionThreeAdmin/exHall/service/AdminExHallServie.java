package kr.co.exhibitionThreeAdmin.exHall.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.dao.AdminExHallDAO;
import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exHall.vo.AdminExHallVO;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class AdminExHallServie {
	
	@Autowired(required =  false)
	private AdminExHallDAO aehDAO;
	
	//전체 글 수
	public int countData(AdminExHallVO aehVO) {
		int cnt = 0;
		
		
		return cnt;
	}
	
	//전시장 조회
	public List<AdminExHallDomain> exHallList(SearchVO sVO){
		List<AdminExHallDomain> list = null;
		
		try {
			list = aehDAO.selectExHall(sVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return list;
	}
	
	//전시장 수정
	public int modifyExHall(AdminExHallVO aehVO) {
		int cnt = 0;
		
		return cnt;
	}
	
	//전시장 삭제
	public int removeExHall(int exHallNum) {
		int cnt = 0;
		
		return cnt;
	}
	
	//전시장 추가
	public int addExHall(AdminExHallVO aehVO) {
		int cnt = 0;
		
		return cnt;
	}
	
	//전시장 상세
	public AdminExHallVO exHallDetail(int exHallNum) {
		AdminExHallVO aehVO = null;
		
		return aehVO;
	}
}
