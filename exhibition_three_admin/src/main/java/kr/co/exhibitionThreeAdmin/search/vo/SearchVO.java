package kr.co.exhibitionThreeAdmin.search.vo;

public class SearchVO {
	private String option,keyword; //검색항목, 검색어
	private int startNum, endNum, pageScale,currentPage; //시작번호,끝번호,한 페이지 당 데이터 수 ,현재 페이지
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "SearchVO [option=" + option + ", keyword=" + keyword + ", startNum=" + startNum + ", endNum=" + endNum
				+ ", pageScale=" + pageScale + "]";
	}
	
}
