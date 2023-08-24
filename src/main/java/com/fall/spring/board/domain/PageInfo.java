package com.fall.spring.board.domain;

public class PageInfo {
	private int currentPage; // 현재 페이지 번호 
	private int totalCount; // 게시물의 총 갯수
	private int naviTotalCount; // 범위의 총 갯수 
	private int recordCountPerPage; // 한 페이지당 보여줄 글 갯수, 고정값 
	private int naviCountPerPage; // 한 페이지 범위에 보여질 페이지의 개수, 고정값 
	private int startNavi; // 각 페이지 범위 시작 번호
	private int endNavi; // 각 페이지 범위 끝 번호 
	
	public PageInfo() {}

	
	public PageInfo(int currentPage, int totalCount, int naviTotalCount, int recordCountPerPage, int naviCountPerPage,
			int startNavi, int endNavi) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.naviTotalCount = naviTotalCount;
		this.recordCountPerPage = recordCountPerPage;
		this.naviCountPerPage = naviCountPerPage;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
	}






	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNaviTotalCount() {
		return naviTotalCount;
	}
	public void setNaviTotalCount(int naviTotalCount) {
		this.naviTotalCount = naviTotalCount;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}
	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}
	public int getStartNavi() {
		return startNavi;
	}
	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}
	public int getEndNavi() {
		return endNavi;
	}
	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}


	@Override
	public String toString() {
		return "PageInfo [현재 페이지=" + currentPage + ", 총 게시물 갯수=" + totalCount + ", 네비 총 갯수="
				+ naviTotalCount + ", 한 페이지당 게시물 갯수=" + recordCountPerPage + ", 한 페이지당 네비 갯수="
				+ naviCountPerPage + ", 시작 네비=" + startNavi + ", 종료 네비=" + endNavi + "]";
	}
	
	
	
	
	
	
	
}
