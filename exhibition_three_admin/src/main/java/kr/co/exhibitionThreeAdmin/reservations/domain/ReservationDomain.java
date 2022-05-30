package kr.co.exhibitionThreeAdmin.reservations.domain;

import java.sql.Date;

public class ReservationDomain {
	private int rez_num,rez_count,ex_num, price;
	private Date visit_date,rez_date;
	private String name,ex_name;
	private String  userid; 
	private char rez_status;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public Date getRez_date() {
		return rez_date;
	}
	public void setRez_date(Date rez_date) {
		this.rez_date = rez_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEx_name() {
		return ex_name;
	}
	public void setEx_name(String ex_name) {
		this.ex_name = ex_name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public char getRez_status() {
		return rez_status;
	}
	public void setRez_status(char rez_status) {
		this.rez_status = rez_status;
	}
	@Override
	public String toString() {
		return "ReservationDomain [rez_num=" + rez_num + ", rez_count=" + rez_count + ", ex_num=" + ex_num + ", price="
				+ price + ", visit_date=" + visit_date + ", rez_date=" + rez_date + ", name=" + name + ", ex_name="
				+ ex_name + ", userid=" + userid + ", rez_status=" + rez_status + "]";
	}
	
}
