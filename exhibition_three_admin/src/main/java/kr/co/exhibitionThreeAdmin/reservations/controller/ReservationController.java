package kr.co.exhibitionThreeAdmin.reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Controller
public class ReservationController {
	
	@RequestMapping("/admin/reservation.do")
	public String reservationMain() {
		
		return "reservations/reservations";
	}
	
}
