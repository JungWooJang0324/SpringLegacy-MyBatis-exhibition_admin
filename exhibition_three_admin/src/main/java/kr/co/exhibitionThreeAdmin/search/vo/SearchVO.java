package kr.co.exhibitionThreeAdmin.search.vo;

public class SearchVO {
	private String option,keyword;
	private int startNum, endNum, pageScale;
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
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
	@Override
	public String toString() {
		return "SearchVO [option=" + option + ", keyword=" + keyword + ", startNum=" + startNum + ", endNum=" + endNum
				+ ", pageScale=" + pageScale + "]";
	}
	
}
