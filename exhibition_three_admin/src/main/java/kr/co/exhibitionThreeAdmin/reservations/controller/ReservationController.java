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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;
@Controller
public class ReservationController {

	@Autowired(required = false)
	private reservationService rs;
	
	
	@RequestMapping(value="/admin/reservation.do")
	public String reservationMain(Model model) {
		List<ReservationDomain> list = rs.rezMainList();
		model.addAttribute("rezList",rs.rezMainList());
		
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
	
	
	
}
