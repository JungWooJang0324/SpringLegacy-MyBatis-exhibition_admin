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
import kr.co.exhibitionThreeAdmin.search.service.SearchService;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

@Service
public class reservationService implements SearchService {
	
	@Autowired(required = false)
    private ReservationDAO rDao;
	
	public List<ReservationDomain> rezMainList(SearchVO sVO){
		List<ReservationDomain> list= null;
		
		try {
			list = rDao.rezMainList(sVO);
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

	public int cancelRez(int rezNum){
		int cnt = 0;
		try {
			cnt = rDao.cancelRez(rezNum);
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int searchTotalCnt(SearchVO sVO) {
		int cnt=0;
		try {
			cnt = rDao.totalCnt(sVO);
		}catch(PersistenceException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int pageCnt(int totalCnt, int pageScale) {
		int pageCnt = 0;
		pageCnt = (int)Math.ceil((double)totalCnt/pageScale); // 나눈 실수를 올림하여 페이지 수를 계산
		return pageCnt;
	}//pageCnt


	@Override
	public int startNum(int currentPage, int pageScale) {
		int startNum=1;
		startNum = currentPage*pageScale-pageScale+1; // 현재페이지*페이지 당 레코드 수-페이지 당 레코드수+1
		return startNum;
	}//startNum


	@Override
	public int endNum(int startNum, int pageScale) {
		int endNum=0;
		endNum = startNum+pageScale-1;//시작번호+페이지 당 레코드 수-1 
		
		return endNum;
	}//endNum


	@Override
	public int startPage(int currentPage, int pageBlock) {
		int startPage = 0;
		startPage = ((currentPage-1)/pageBlock)*pageBlock+1; //((현재페이지-1)/한번에 보여줄 페이지 갯수)* 한번에 보여줄 페이지 갯수+1
		return startPage;
	}//startPage


	@Override
	public int endPage(int startPage, int pageBlock) {
		int endPage = 0;
		endPage = startPage+pageBlock;//페이지 시작번호+한번에 보여줄 페이지 갯수
		return endPage;
	}//endPage

	@Override
	public int pageBlock() {
		int pageBlock=5; // 한번에 보여줄 페이지 갯수 5로 설정
		return pageBlock;
	}//pageBlock



	
}
