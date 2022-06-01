package kr.co.exhibitionThreeAdmin.reservations.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.reservations.domain.ReservationDomain;
import kr.co.exhibitionThreeAdmin.reservations.vo.ReservationVO;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Component
public class ReservationDAO {

	public List<ReservationDomain> rezMainList(SearchVO sVO) throws PersistenceException{
		List<ReservationDomain> list = null;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhibitionThreeAdmin.reservation.rezMainList", sVO);
		if(ss != null) {ss.close();}//end if

		return list;
	}//rezMainList

	public ReservationDomain selectRezDetail(int rezNum) throws PersistenceException{
		ReservationDomain rd= null;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		rd = ss.selectOne("kr.co.exhibitionThreeAdmin.reservation.rezDetail", rezNum);
		if(ss != null) {ss.close();}//end if
		
		return rd;
	}//rezMainList
	
	public int updateRez(ReservationVO rVO) throws PersistenceException{
		int cnt = 0;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("kr.co.exhibitionThreeAdmin.reservation.rezModify", rVO);
		
		if(cnt==1) ss.commit();
		
		if(ss != null) {ss.close();}//end if
		
		return cnt;
	}//UpdateRez
	
	public int cancelRez(int rezNum) throws PersistenceException{
		int cnt = 0;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.update("kr.co.exhibitionThreeAdmin.reservation.rezCancel", rezNum);
		
		if(cnt==1) ss.commit();
		
		if(ss != null) {ss.close();}//end if
		
		return cnt;
	}//rezMainList
		
	public int totalCnt(SearchVO sVO) throws PersistenceException{
		int cnt = 0;

		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		cnt = ss.selectOne("kr.co.exhibitionThreeAdmin.reservation.totalCnt", sVO);
		if(ss != null) {ss.close();}//end if
		
		return cnt;
	}//totalCnt
	
}
