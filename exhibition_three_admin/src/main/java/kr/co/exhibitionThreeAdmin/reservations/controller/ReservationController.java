package kr.co.exhibitionThreeAdmin.reservations.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.reservations.domain.ReservationDomain;
import kr.co.exhibitionThreeAdmin.reservations.service.reservationService;
import kr.co.exhibitionThreeAdmin.reservations.vo.ReservationVO;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;
@Controller
public class ReservationController {

	@Autowired(required = false)
	private reservationService rs;
	
	
	@RequestMapping(value="/admin/reservation.do", method=GET)
	public String reservationMain(Model model, SearchVO sVO) {		
		List<ReservationDomain> list = rs.rezMainList(sVO);
		
		//전체 페이지 수
		int totalCnt=rs.searchTotalCnt(sVO);
		//2. 한화면에 보여줄 게시물의 수
		int pageScale =sVO.getPageScale();
		//페이지 수
		int pageCnt = rs.pageCnt(totalCnt, pageScale);
		int currentPage = sVO.getCurrentPage();
		int startNum = rs.startNum(currentPage,sVO.getPageScale());
		int endNum = rs.endNum(startNum, pageScale);
		int pageBlock = rs.pageBlock();
		int startPage = rs.startPage(currentPage, pageBlock);
		int endPage = rs.endPage(startPage, pageBlock);
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		
		model.addAttribute("startNum",startNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("startNum",startNum);
		model.addAttribute("startPage",startPage);
		model.addAttribute("pageCnt",pageCnt);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalCnt",totalCnt);
		model.addAttribute("pageBlock",pageBlock);
		model.addAttribute("currentPage",currentPage);
		
		model.addAttribute("rezList",rs.rezMainList(sVO));
		
		return "reservations/reservations";
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/rezDetail.do", method=GET ,produces = "applicataion/json;charset=UTF-8")
	public String searchRezDetail(Model model, String rezNum) {
		int resNum = Integer.parseInt(rezNum);
		return rs.searchRezDetail(resNum);
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/rezModify.do", method=GET ,produces = "applicataion/json;charset=UTF-8")
	public String modifyRez(Model model, ReservationVO rVO) {
		int cnt = rs.modifyRez(rVO);
		String msg="실패";
		
		JSONObject jObj=new JSONObject();
		if(cnt==1) {
			msg="성공";
		}
		
		jObj.put("msg", msg);
		
		return jObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/rezCancel.do", method=GET ,produces = "applicataion/json;charset=UTF-8")
	public String cancelRez(Model model, int rezNum) {
		int cnt = rs.cancelRez(rezNum);
		
		JSONObject jObj=new JSONObject();
		
		jObj.put("cnt", cnt);
		
		return jObj.toJSONString();
	}
	
	
	
}
