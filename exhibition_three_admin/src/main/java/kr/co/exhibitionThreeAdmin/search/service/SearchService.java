package kr.co.exhibitionThreeAdmin.search.service;

import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;

public interface SearchService {
	public int searchTotalCnt(SearchVO sVO); // 총 데이터 수(검색포함)
	public int pageCnt(int totalCnt, int pageScale); // 페이지 수
	public int startNum(int currentPage, int pageScale); // 시작 번호
	public int endNum(int startNum,int pageScale); //끝 번호
	public int startPage(int currentPage, int pageBlock);//시작 페이지 번호
	public int endPage(int startPage, int pageBlock);//끝 페이지 번호
	public int pageBlock();//보여줄 페이지 번호 갯수
}//class
