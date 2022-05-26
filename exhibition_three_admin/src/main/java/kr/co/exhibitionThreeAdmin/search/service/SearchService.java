package kr.co.exhibitionThreeAdmin.search.service;

public interface SearchService {
	public int searchTotalCnt(); // 총 데이터 수 
	public int pageCnt(int totalCnt, int pageScale); // 페이지 수
	public int startNum(int currentPage, int pageScale); // 시작 번호
	public int endNum(int startNum,int pageScale); //끝 번호
}//class
