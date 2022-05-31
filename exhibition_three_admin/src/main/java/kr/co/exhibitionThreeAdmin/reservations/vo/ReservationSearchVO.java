package kr.co.exhibitionThreeAdmin.reservations.vo;

public class ReservationSearchVO {
	private String vDate,nameSelection;
	private int startNum, endNum, pageScale,currentPage; //시작번호,끝번호,한 페이지 당 데이터 수 ,현재 페이지
	
	public ReservationSearchVO() {
		currentPage=1;
		pageScale=5;
	}

	public String getvDate() {
		return vDate;
	}

	public void setvDate(String vDate) {
		this.vDate = vDate;
	}

	public String getNameSelection() {
		return nameSelection;
	}

	public void setNameSelection(String nameSelection) {
		this.nameSelection = nameSelection;
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
	
	
}
