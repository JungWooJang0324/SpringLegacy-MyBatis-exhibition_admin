package kr.co.exhibitionThreeAdmin.reservations.service;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.reservations.dao.ReservationDAO;
import kr.co.exhibitionThreeAdmin.reservations.domain.ReservationDomain;
import kr.co.exhibitionThreeAdmin.reservations.vo.ReservationVO;

@Service
public class reservationService {
	
	@Autowired(required = false)
    private ReservationDAO rDao;
	
	public List<ReservationDomain> rezMainList(){
		List<ReservationDomain> list= null;
		
		try {
			list = rDao.rezMainList();
			System.out.println("service: "+list);
		}catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
