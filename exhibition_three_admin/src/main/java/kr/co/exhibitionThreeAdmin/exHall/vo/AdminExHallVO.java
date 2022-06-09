package kr.co.exhibitionThreeAdmin.exHall.vo;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestParam;

public class AdminExHallVO {
	
	private String 	ex_loc, ex_hall_name,	mgr_name,	mgr_tel,	zipcode,	address1,	address2, ex_tel;	
	private int ex_hall_num;
	private double latitude,	longitude;
	private char hall_deleted;
	private Date input_date;
	public String getEx_loc() {
		return ex_loc;
	}
	public void setEx_loc(String ex_loc) {
		this.ex_loc = ex_loc;
	}
	public String getEx_hall_name() {
		return ex_hall_name;
	}
	public void setEx_hall_name(String ex_hall_name) {
		this.ex_hall_name = ex_hall_name;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public String getMgr_tel() {
		return mgr_tel;
	}
	public void setMgr_tel(String mgr_tel) {
		this.mgr_tel = mgr_tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getEx_tel() {
		return ex_tel;
	}
	public void setEx_tel(String ex_tel) {
		this.ex_tel = ex_tel;
	}
	public int getEx_hall_num() {
		return ex_hall_num;
	}
	public void setEx_hall_num(@RequestParam(defaultValue = "0")int ex_hall_num) {
		this.ex_hall_num = ex_hall_num;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(@RequestParam(defaultValue = "0.0")double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(@RequestParam(defaultValue = "0.0")double longitude) {
		this.longitude = longitude;
	}
	public char getHall_deleted() {
		return hall_deleted;
	}
	public void setHall_deleted(char hall_deleted) {
		this.hall_deleted = hall_deleted;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "AdminExHallVO [ex_loc=" + ex_loc + ", ex_hall_name=" + ex_hall_name + ", mgr_name=" + mgr_name
				+ ", mgr_tel=" + mgr_tel + ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2
				+ ", ex_tel=" + ex_tel + ", ex_hall_num=" + ex_hall_num + ", latitude=" + latitude + ", longitude="
				+ longitude + ", hall_deleted=" + hall_deleted + ", input_date=" + input_date + "]";
	}

	
	
	
	
	
	
	
	
}
