package kr.co.exhibitionThreeAdmin.exhibition.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exhibition.dao.AdminExhibitionDAO;
import kr.co.exhibitionThreeAdmin.exhibition.domain.ExhibitionDomain;
import kr.co.exhibitionThreeAdmin.exhibition.vo.ExhibitionVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;
import kr.co.exhibitionThreeAdmin.search.service.SearchService;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;
@Component
public class AdminExhibitionService implements SearchService{
	@Autowired(required = false)
	AdminExhibitionDAO aDAO;
	
	@Override
	public int searchTotalCnt(SearchVO sVO) {
		int totalCnt = 0;
		setField(sVO);
		totalCnt = aDAO.totalCount(sVO);
		return totalCnt;
	}//searchTotalCnt

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


	/**
	 * 검색항목 번호를 입력받아 컬럼명을 구한다.
	 * @param sVO
	 */
	private void setField(SearchVO sVO) {
		
		String field="input_date";
		String fieldNum = sVO.getField();
		if(!"".equals(fieldNum)) {
			if("1".equals(fieldNum) || "ex_name".equals(fieldNum)) {
				field="ex_name";
			}else if("2".equals(fieldNum) || "ex_num".equals(fieldNum)) {
				field="ex_num";
			}//end else
		}//end if
		sVO.setField(field);
	}//setField
	
	public List<ExhibitionDomain> searchExhibition(SearchVO sVO){
		List<ExhibitionDomain> list = null;
		try {
			setField(sVO);
			list = aDAO.selectExhibition(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchExhibition
	
	public String searchExDetail(String ex_num) {
		SearchVO sVO = new SearchVO();
		sVO.setField("ex_num");
		sVO.setKeyword(ex_num);
		ExhibitionDomain ed=null;
		try {
		ed = aDAO.selectExDetail(sVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("exName", ed.getEx_name());
		jsonObj.put("teen", ed.getTeen());
		jsonObj.put("adult",ed.getAdult() );
		jsonObj.put("child",ed.getChild() );
		jsonObj.put("totalCount",ed.getTotal_count() );
		jsonObj.put("watchCount",ed.getWatch_count() );
		jsonObj.put("exInfo",ed.getEx_info());
		jsonObj.put("exIntro",ed.getEx_intro() );
		jsonObj.put("exHallNum",ed.getEx_hall_num() );
		jsonObj.put("addImgUrl",ed.getAdd_img_url() );
		jsonObj.put("exPosterUrl",ed.getExhibition_poster_url() );
		jsonObj.put("exStatus",ed.getEx_status());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonObj.put("exhibitDate", sdf.format(ed.getExhibit_date()));
		jsonObj.put("deadline", sdf.format(ed.getDeadline()));
		
		return jsonObj.toJSONString();
	}//searchExDetail
	
	public List<AdminExHallDomain> searchExHall(){
		List<AdminExHallDomain> list = null;
		try {
			list = aDAO.selectExHall();
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchExHall
	
	public String modifyExhibition(ExhibitionVO eVO) {
		int cnt=0;
		JSONObject jsonObj = new JSONObject();
		try {
			cnt = aDAO.updateExhibition(eVO);
			jsonObj.put("cnt", cnt);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return jsonObj.toJSONString();
	}//modifyExhibition
	
	public int addExhibition(ExhibitionVO eVO) {
		int cnt = 0;
		try {
			cnt = aDAO.insertExhibition(eVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return cnt;
	}//addExhibition
}//class
