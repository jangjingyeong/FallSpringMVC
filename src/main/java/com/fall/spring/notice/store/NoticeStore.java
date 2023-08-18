package com.fall.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.fall.spring.notice.domain.Notice;
import com.fall.spring.notice.domain.PageInfo;

public interface NoticeStore {
	
	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return int
	 */
	int insertNotice(SqlSession session, Notice notice);

	/**
	 * 공지사항 목록 조회 Store
	 * @param session
	 * @param currentPage
	 * @return List
	 */
	List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo);
	
	/**
	 * 공지사항 갯수 조회 Store
	 * @param session
	 * @return int
	 */
	int selectListCount(SqlSession session);

	/**
	 * 공지사항 조건에 따라 키워드로 조회 Store
	 * @param session
	 * @param searchCondition
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 전체로 검색 Store
	 * @param session
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> selectNoticesByAll(SqlSession session, String searchKeyword);

	/**
	 * 공지사항 작성자로 검색 Store
	 * @param session
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> selectNoticesByWriter(SqlSession session, String searchKeyword);

	/**
	 * 공지사항 제목으로 검색 Store
	 * @param session
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> selectNoticesByTitle(SqlSession session, String searchKeyword);
	
	/**
	 * 공지사항 내용으로 검색 Store
	 * @param session
	 * @param searchKeyword
	 * @return List
	 */
	List<Notice> selectNoticesByContent(SqlSession session, String searchKeyword);

	/**
	 * 공지사항 검색 게시물 전체 갯수 Store
	 * @param session
	 * @param paramMap
	 * @return int
	 */
	int getListCount(SqlSession session, Map<String, String> paramMap);

}
