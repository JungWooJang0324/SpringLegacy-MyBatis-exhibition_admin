package kr.co.exhibitionThreeAdmin.exHall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.exhibitionThreeAdmin.exHall.domain.AdminExHallDomain;
import kr.co.exhibitionThreeAdmin.exHall.service.AdminExHallServie;
import kr.co.exhibitionThreeAdmin.exHall.vo.AdminExHallVO;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Controller
public class AdminExHallController {
	
	@Autowired(required =  false)
	private AdminExHallServie as;
	
	//전시장 조회
	@RequestMapping(value = "/admin/hall.do", method = GET)
	public String exHall(Model model, BHSearchVO sVO) throws Exception{
		//전체 레코드의 수
		int totalCnt = as.countData(sVO);
		//한 화면에 보여줄 게시물의 수
		int pageScale = as.pageScale();
		//현재 페이지
		int currentPage=sVO.getCurrentPage();
		//페이지의 수
		int pageCnt = as.pageCnt(sVO);
		//시작 번호
		int startNum = as.startNum(currentPage, pageScale);
		//끝 번호 
		int endNum = as.endNum(startNum, pageScale);
		//페이지블럭 시작 번호
		int startPage = as.startPage(currentPage, pageScale);
		//페이지 블럭 끝 번호
		int endPage = as.endPage(startPage, pageScale, sVO);
		//이전 페이지 존재 유무
		boolean prev = as.prev(currentPage, pageScale);
		//다음 페이지 존재 유무
		boolean next = as.next(sVO, startPage, pageScale);
		//이전 페이지 시작 번호
		int prevNum = as.prevNum(currentPage, pageScale);
		//다음페이지 시작번호
		int nextNum = as.nextNum(currentPage, pageScale);
		
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageScale", pageScale);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("prevNum", prevNum);
		model.addAttribute("nextNum", nextNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRows", as.countData(sVO));
		model.addAttribute("exHallList", as.exHallList(sVO));
		
		return "exHall/hall";
	}
	
	//전시장 추가 
	@RequestMapping(value = "/admin/hallAdd.do", method = GET, produces = "applicaion/text; charset=UTF-8")
	@ResponseBody
	public String addExHall(AdminExHallVO aehVO, HttpServletRequest request)throws Exception {
		String flag="";
		//전시장 추가
		String ex_hall_name = request.getParameter("exName");
		String exLoc = request.getParameter("exLoc");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		String lat = request.getParameter("lat");
		String longi = request.getParameter("longi");
		String mgrName = request.getParameter("mgrName");
		String mgrTel = request.getParameter("mgrTel");
		String exTel = request.getParameter("exTel");

		//vo 넣기
		try{
			aehVO.setEx_hall_name(ex_hall_name);
			aehVO.setEx_loc(exLoc);
			aehVO.setAddress1(addr1);
			aehVO.setAddress2(addr2);
			aehVO.setZipcode(zipcode);
			aehVO.setLongitude(Double.parseDouble(longi));
			aehVO.setLatitude(Double.parseDouble(lat));
			aehVO.setMgr_name(mgrName);
			aehVO.setMgr_tel(mgrTel);
			aehVO.setEx_tel(exTel);
			
			flag =String.valueOf(as.addExHall(aehVO));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	//전시장 상세
	@RequestMapping(value = "/admin/exHallDetail.do", method = GET, produces = "applicaion/json; charset=UTF-8")
	@ResponseBody
	public String exHallDetail(int exHallNum, HttpServletRequest request)throws Exception {
		
		//클릭된 전시장 번호
		exHallNum = Integer.parseInt(request.getParameter("exHallNum")) ;
		//전시 상세 조회
		AdminExHallDomain aehDomain = new AdminExHallDomain();
		aehDomain = as.exHallDetail(exHallNum);
		//전시 상세 jsonObj 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("exName", aehDomain.getEx_hall_name());
		jsonObj.put("exNum", aehDomain.getEx_hall_num());
		jsonObj.put("zipcode", aehDomain.getZipcode());
		jsonObj.put("latitude", aehDomain.getLatitude());
		jsonObj.put("longitude", aehDomain.getLongitude());
		jsonObj.put("mgrName", aehDomain.getMgr_name());
		jsonObj.put("mgrTel", aehDomain.getMgr_tel());
		jsonObj.put("exTel", aehDomain.getEx_tel());
		jsonObj.put("addr1", aehDomain.getAddress1());
		jsonObj.put("addr2", aehDomain.getAddress2());
		return jsonObj.toJSONString();
	}
	
	@RequestMapping(value = "/admin/hallRemove.do", method = GET, produces = "applicaion/text; charset=UTF-8")
	@ResponseBody
	public String removeExHall(int exHallNum, HttpServletRequest request) throws Exception{
		
		String flag="";
		//전시장 삭제
		exHallNum = Integer.parseInt(request.getParameter("exHallNum")) ;
		flag = String.valueOf(as.removeExHall(exHallNum));
		return flag;
	}
	
	@RequestMapping(value = "/admin/hallModify.do", method = GET, produces = "applicaion/text; charset=UTF-8")
	@ResponseBody
	public String modifyExHall(AdminExHallVO aehVO, HttpServletRequest request)throws Exception {
		
		String flag="";
		//전시장 수정
		String hallNum = request.getParameter("hallNum");
		String exName = request.getParameter("exName");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		String lat = request.getParameter("lat");
		String longi = request.getParameter("lat");
		String mgrName = request.getParameter("mgrName");
		String mgrTel = request.getParameter("mgrTel");
		String exTel = request.getParameter("exTel");

		//vo 넣기
		try{
			aehVO.setEx_hall_num(Integer.parseInt(hallNum));
			aehVO.setEx_hall_name(exName);
			aehVO.setAddress1(addr1);
			aehVO.setAddress2(addr2);
			aehVO.setZipcode(zipcode);
			aehVO.setLongitude(Double.parseDouble(longi));
			aehVO.setLatitude(Double.parseDouble(lat));
			aehVO.setMgr_name(mgrName);
			aehVO.setMgr_tel(mgrTel);
			aehVO.setEx_tel(exTel);
			
			flag = String.valueOf(as.modifyExHall(aehVO)); 
			
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		} 
		return flag;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionMethod(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:hall.do"); 
		mav.addObject("error", "정상적으로 처리되지 않았습니다."); 

		return mav; 
	}

}
