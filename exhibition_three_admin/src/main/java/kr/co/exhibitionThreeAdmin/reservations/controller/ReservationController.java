package kr.co.exhibitionThreeAdmin.reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.exhibitionThreeAdmin.reservations.domain.ReservationDomain;
import kr.co.exhibitionThreeAdmin.reservations.service.reservationService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
@Controller
public class ReservationController {

	@Autowired(required = false)
	private reservationService rs;
	
	
	@RequestMapping("/admin/reservation.do")
	public String reservationMain(Model model) {
		List<ReservationDomain> list = rs.rezMainList();
		System.out.println(list);
		model.addAttribute("rezList",rs.rezMainList());
		
		return "reservations/reservations";
	}
	
}
