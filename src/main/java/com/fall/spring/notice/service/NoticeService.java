package com.fall.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.fall.spring.notice.domain.Notice;
import com.fall.spring.notice.domain.PageInfo;

public interface NoticeService {
	
	/** 
	 * 공지사항 등록 Service
	 * @param notice
	 * @return int
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지사항 목록 조회 Service
	 * @param currentPage
	 * @return List
	 */
	List<Notice> selectNoticeList(PageInfo pInfo);

	/**
	 * 공지사항 갯수 조회 Service
	 * @return int
	 */
	int getListCount();

	/**
	 * 공지사항 조건에 따라 키워드로 검색 Service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 전체로 검색 Service
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> searchNoticesByAll(String searchKeyword);

	/**
	 * 공지사항 작성자로 검색 Service
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> searchNoticesByWriter(String searchKeyword);

	/**
	 * 공지사항 제목으로 검색 Service
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> searchNoticesByTitle(String searchKeyword);
	
	/**
	 * 공지사항 내용으로 검색 Service
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> searchNoticesByContent(String searchKeyword);
	
	/**
	 * 공지사항 검색 게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

}
