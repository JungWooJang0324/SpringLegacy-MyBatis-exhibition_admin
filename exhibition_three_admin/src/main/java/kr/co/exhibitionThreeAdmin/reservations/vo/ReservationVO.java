package kr.co.exhibitionThreeAdmin.reservations.vo;

import java.sql.Date;

public class ReservationVO {
		
	private int rez_num, rez_count,ex_num;
	private char rez_status;
	private  Date rez_date,visit_date;
	
	public int getRez_num() {
		return rez_num;
	}
	public void setRez_num(int rez_num) {
		this.rez_num = rez_num;
	}
	public int getRez_count() {
		return rez_count;
	}
	public void setRez_count(int rez_count) {
		this.rez_count = rez_count;
	}
	public int getEx_num() {
		return ex_num;
	}
	public void setEx_num(int ex_num) {
		this.ex_num = ex_num;
	}
	public char getRez_status() {
		return rez_status;
	}
	public void setRez_status(char rez_status) {
		this.rez_status = rez_status;
	}
	public Date getRez_date() {
		return rez_date;
	}
	public void setRez_date(Date rez_date) {
		this.rez_date = rez_date;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	@Override
	public String toString() {
		return "ReservationVO [rez_num=" + rez_num + ", rez_count=" + rez_count + ", ex_num=" + ex_num + ", rez_status="
				+ rez_status + ", rez_date=" + rez_date + ", visit_date=" + visit_date + "]";
	}
	
	
	
}
