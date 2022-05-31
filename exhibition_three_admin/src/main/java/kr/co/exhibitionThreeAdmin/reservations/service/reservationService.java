package kr.co.exhibitionThreeAdmin.reservations.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.simple.JSONObject;
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
		}catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String searchRezDetail(int rezNum){
		ReservationDomain  rd = null;
		JSONObject jsonObj=null;
		
		try {
			rd= rDao.selectRezDetail(rezNum);
			jsonObj = new JSONObject();
			jsonObj.put("exName", rd.getEx_name());
			jsonObj.put("exNum", rd.getEx_num());
			jsonObj.put("name", rd.getName());
			jsonObj.put("rezCount", rd.getRez_count());
			jsonObj.put("rezDate",new SimpleDateFormat("yyyy-MM-dd").format( rd.getRez_date()));
			jsonObj.put("userId", rd.getUserid());
			jsonObj.put("visitDate", new SimpleDateFormat("yyyy-MM-dd").format(rd.getVisit_date()) );
			jsonObj.put("price", rd.getPrice());
			
		}catch (PersistenceException e) {
			e.printStackTrace();
		}
		return jsonObj.toJSONString();
	}
	
	
	public int modifyRez(ReservationVO rVO){
		int cnt = 0;
		try {
			cnt = rDao.updateRez(rVO);
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
