package kr.co.exhibitionThreeAdmin.exHall.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exHall.vo.AdminExHallVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Component
public class AdminExHallDAO {
	
	//전체 전시장 수
	public int getTotalRows(BHSearchVO sVO) {
		int cntRows =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cntRows = ss.selectOne("kr.co.exhbitionThreeAdmin.exHall.totalRows", sVO); 	
		if(ss!=null) {ss.close();}
		return cntRows;
	}
	
	//전시장 조회
	public List<AdminExHallDomain> selectExHall(BHSearchVO sVO) throws PersistenceException{
		List<AdminExHallDomain> list = null;
			
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhbitionThreeAdmin.exHall.selectExHall", sVO); 	
		if(ss!=null) {ss.close();}
		return list;
	}
	
	/**
	 * 전시장 수정
	 * @param aehVO
	 * @return
	 * @throws PersistenceException
	 */
	public int updateExHall(AdminExHallVO aehVO) throws PersistenceException {
		int cnt =0;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("kr.co.exhbitionThreeAdmin.exHall.updateExhall", aehVO);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	/**
	 * 전시장 삭제
	 * @param exHallNum
	 * @return
	 */
	public int deleteExHall(int exHallNum) throws PersistenceException {
		int cnt =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.insert("kr.co.exhbitionThreeAdmin.exHall.deleteExhall", exHallNum);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	/**
	 * 전시장 추가
	 * @param eVO
	 * @return
	 */
	public int insertExHall(AdminExHallVO aehVO) throws PersistenceException {
		int cnt =0;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.delete("kr.co.exhbitionThreeAdmin.exHall.insertExhall", aehVO);
		if(cnt>0) {
			ss.commit();
		}
		if(ss!=null) {ss.close();}
		return cnt;
	}
	
	/**
	 * 전시장 상세
	 * @param exHallNum
	 * @return
	 */
	public AdminExHallDomain selectExHallDetail(int exHallNum) throws PersistenceException {
		AdminExHallDomain aehDomain= null;
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		aehDomain = ss.selectOne("kr.co.exhbitionThreeAdmin.exHall.exhallDetail", exHallNum); 	
		if(ss!=null) {ss.close();}
		return aehDomain;
	}
	
}
