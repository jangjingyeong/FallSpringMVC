package com.fall.spring.notice.domain;

// 페이징 처리 관련 VO
public class PageInfo {
	private int currentPage; // 현재 목록의 페이지 번호
	private int recordCountPerPage; // 한 페이지당 보여질 게시물의 개수
	private int naviCountPerPage; // 한 페이지 범위에 보여질 페이지의 개수
	private int startNavi; // 각 페이지 범위 시작 번호
	private int endNavi; // 각 페이지 범위 끝 번호
	private int totalCount; // 전체 게시물의 개수
	private int naviTotalCount; // 범위의 총 개수
	
	public PageInfo() {}

	public PageInfo(int currentPage, int recordCountPerPage, int naviCountPerPage, int startNavi, int endNavi,
			int totalCount, int naviTotalCount) {
		super();
		this.currentPage = currentPage;
		this.recordCountPerPage = recordCountPerPage;
		this.naviCountPerPage = naviCountPerPage;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
		this.totalCount = totalCount;
		this.naviTotalCount = naviTotalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", recordCountPerPage=" + recordCountPerPage
				+ ", naviCountPerPage=" + naviCountPerPage + ", startNavi=" + startNavi + ", endNavi=" + endNavi
				+ ", totalCount=" + totalCount + ", naviTotalCount=" + naviTotalCount + "]";
	}
	
	
	
	
	
}
