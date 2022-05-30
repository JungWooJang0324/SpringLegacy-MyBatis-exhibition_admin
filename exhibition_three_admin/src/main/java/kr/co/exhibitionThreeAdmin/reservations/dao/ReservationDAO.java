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

	public List<ReservationDomain> rezMainList() throws PersistenceException{
		List<ReservationDomain> list = null;
		
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();
		list = ss.selectList("kr.co.exhibitionThreeAdmin.reservation.rezMainList");
		if(ss != null) {ss.close();}//end if

		return list;
	}//rezMainList
		

	
}
