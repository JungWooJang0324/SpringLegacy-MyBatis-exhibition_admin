package kr.co.exhibitionThreeAdmin.board.vo;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestParam;

public class AdminBoardVO {
	
	private String 	title,	description, userid, adminid, img_file;
	public String getImg_file() {
		return img_file;
	}
	public void setImg_file(String img_file) {
		this.img_file = img_file;
	}
	private int bd_id, cat_num;
	private char isdeleted;
	private Date input_date;
	public int getBd_id() {
		return bd_id;
	}
	public void setBd_id(@RequestParam(defaultValue = "0") int bd_id) {
		this.bd_id = bd_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public int getCat_num() {
		return cat_num;
	}
	public void setCat_num(@RequestParam(defaultValue = "0")int cat_num) {
		this.cat_num = cat_num;
	}
	public char getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(char isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "AdminBoardVO [title=" + title + ", description=" + description + ", userid=" + userid + ", adminid="
				+ adminid + ", img_file=" + img_file + ", bd_id=" + bd_id + ", cat_num=" + cat_num + ", isdeleted="
				+ isdeleted + ", input_date=" + input_date + "]";
	}
	
	
	
}
