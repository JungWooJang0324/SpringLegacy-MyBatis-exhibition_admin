package kr.co.exhibitionThreeAdmin.search.service;

public interface SearchService {
	public int searchTotalCnt();
	public int pageCnt(int totalCnt, int pageScale);
	public int startNum(int currentPage, int pageScale);
	public int endNum(int startNum,int pageScale);
}
