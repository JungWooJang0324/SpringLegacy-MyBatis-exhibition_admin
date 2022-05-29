package kr.co.exhibitionThreeAdmin.exhibition.domain;

import java.sql.Date;

public class ExhibitionDomain {
	private String  ex_name, ex_info, ex_intro,  total_count,  ex_status, exhibition_poster, add_img; 
	private int ex_num,adult,teen, child, ex_hall_num, cat_num;
	private Date exhibit_date,deadline, input_date;
	public String getEx_name() {
		return ex_name;
	}
	public void setEx_name(String ex_name) {
		this.ex_name = ex_name;
	}
	public String getEx_info() {
		return ex_info;
	}
	public void setEx_info(String ex_info) {
		this.ex_info = ex_info;
	}
	public String getEx_intro() {
		return ex_intro;
	}
	public void setEx_intro(String ex_intro) {
		this.ex_intro = ex_intro;
	}
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getEx_status() {
		return ex_status;
	}
	public void setEx_status(String ex_status) {
		this.ex_status = ex_status;
	}
	public String getExhibition_poster() {
		return exhibition_poster;
	}
	public void setExhibition_poster(String exhibition_poster) {
		this.exhibition_poster = exhibition_poster;
	}
	public String getAdd_img() {
		return add_img;
	}
	public void setAdd_img(String add_img) {
		this.add_img = add_img;
	}
	public int getEx_num() {
		return ex_num;
	}
	public void setEx_num(int ex_num) {
		this.ex_num = ex_num;
	}
	public int getAdult() {
		return adult;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public int getTeen() {
		return teen;
	}
	public void setTeen(int teen) {
		this.teen = teen;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	public int getEx_hall_num() {
		return ex_hall_num;
	}
	public void setEx_hall_num(int ex_hall_num) {
		this.ex_hall_num = ex_hall_num;
	}
	public int getCat_num() {
		return cat_num;
	}
	public void setCat_num(int cat_num) {
		this.cat_num = cat_num;
	}
	public Date getExhibit_date() {
		return exhibit_date;
	}
	public void setExhibit_date(Date exhibit_date) {
		this.exhibit_date = exhibit_date;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "ExhibitionDomain [ex_name=" + ex_name + ", ex_info=" + ex_info + ", ex_intro=" + ex_intro + ", total_count="
				+ total_count + ", ex_status=" + ex_status + ", exhibition_poster=" + exhibition_poster + ", add_img="
				+ add_img + ", ex_num=" + ex_num + ", adult=" + adult + ", teen=" + teen + ", child=" + child
				+ ", ex_hall_num=" + ex_hall_num + ", cat_num=" + cat_num + ", exhibit_date=" + exhibit_date
				+ ", deadline=" + deadline + ", input_date=" + input_date + "]";
	}
	
	
	
}//class
