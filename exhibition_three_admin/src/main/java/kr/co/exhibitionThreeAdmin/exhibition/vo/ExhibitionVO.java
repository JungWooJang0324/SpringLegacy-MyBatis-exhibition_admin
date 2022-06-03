package kr.co.exhibitionThreeAdmin.exhibition.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ExhibitionVO {
	private String  ex_name, ex_info, ex_intro, ex_status; 
	private int ex_num,adult,teen, child, ex_hall_num, cat_num,total_count,watch_count;
	private Date exhibit_date,deadline, input_date;
	private MultipartFile mulAdd, mulPoster; // s3에 저장할 파일 변수
	private String add_img_url,exhibition_poster_url;
	private String add_img,exhibition_poster;
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
	public String getEx_status() {
		return ex_status;
	}
	public void setEx_status(String ex_status) {
		this.ex_status = ex_status;
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
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getWatch_count() {
		return watch_count;
	}
	public void setWatch_count(int watch_count) {
		this.watch_count = watch_count;
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
	public MultipartFile getMulAdd() {
		return mulAdd;
	}
	public void setMulAdd(MultipartFile mulAdd) {
		this.mulAdd = mulAdd;
	}
	public MultipartFile getMulPoster() {
		return mulPoster;
	}
	public void setMulPoster(MultipartFile mulPoster) {
		this.mulPoster = mulPoster;
	}
	public String getAdd_img_url() {
		return add_img_url;
	}
	public void setAdd_img_url(String add_img_url) {
		this.add_img_url = add_img_url;
	}
	public String getExhibition_poster_url() {
		return exhibition_poster_url;
	}
	public void setExhibition_poster_url(String exhibition_poster_url) {
		this.exhibition_poster_url = exhibition_poster_url;
	}
	public String getAdd_img() {
		return add_img;
	}
	public void setAdd_img(String add_img) {
		this.add_img = add_img;
	}
	public String getExhibition_poster() {
		return exhibition_poster;
	}
	public void setExhibition_poster(String exhibition_poster) {
		this.exhibition_poster = exhibition_poster;
	}
	@Override
	public String toString() {
		return "ExhibitionVO [ex_name=" + ex_name + ", ex_info=" + ex_info + ", ex_intro=" + ex_intro + ", ex_status="
				+ ex_status + ", ex_num=" + ex_num + ", adult=" + adult + ", teen=" + teen + ", child=" + child
				+ ", ex_hall_num=" + ex_hall_num + ", cat_num=" + cat_num + ", total_count=" + total_count
				+ ", watch_count=" + watch_count + ", exhibit_date=" + exhibit_date + ", deadline=" + deadline
				+ ", input_date=" + input_date + ", mulAdd=" + mulAdd + ", mulPoster=" + mulPoster + ", add_img_url="
				+ add_img_url + ", exhibition_poster_url=" + exhibition_poster_url + ", add_img=" + add_img
				+ ", exhibition_poster=" + exhibition_poster + "]";
	}
	
	
	
}//class
