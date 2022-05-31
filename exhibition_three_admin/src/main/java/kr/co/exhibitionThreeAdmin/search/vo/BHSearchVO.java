package kr.co.exhibitionThreeAdmin.search.vo;

import org.springframework.web.bind.annotation.RequestParam;

public class BHSearchVO {
	
	private String option, keyword; //검색항목, 검색어
	private int startNum, endNum, pageScale,currentPage, optNum; //시작번호,끝번호,한 페이지 당 데이터 수 ,현재 페이지
	
	public int getOptNum() {
		return optNum;
	}
	public void setOptNum(int optNum) {
		this.optNum = optNum;
	}
	public String getOption() {
		return option;
	}
	public void setOption(@RequestParam(defaultValue = "0") String option) {
		this.option = option;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public int getPageScale() {
		return pageScale;
	}
	public void setPageScale(int pageScale) {
		this.pageScale = pageScale;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "BHSearchVO [option=" + option + ", keyword=" + keyword + ", startNum=" + startNum + ", endNum=" + endNum
				+ ", pageScale=" + pageScale + ", currentPage=" + currentPage + ", optNum=" + optNum + "]";
	}
	
	
}
