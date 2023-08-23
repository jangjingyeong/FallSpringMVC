package com.fall.spring.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fall.spring.notice.domain.Notice;
import com.fall.spring.notice.domain.PageInfo;
import com.fall.spring.notice.service.NoticeService;
import com.fall.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private SqlSession session;
	
	@Autowired
	private NoticeStore nStore;
	
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	@Override
	public int getListCount() {
		int result = nStore.selectListCount(session);
		return result;
	}

	@Override
	public List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.selectNoticesByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public List<Notice> searchNoticesByAll(String searchKeyword) {
		List<Notice> searchList = nStore.selectNoticesByAll(session, searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> searchNoticesByWriter(String searchKeyword) {
		List<Notice> searchList = nStore.selectNoticesByWriter(session, searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> searchNoticesByTitle(String searchKeyword) {
		List<Notice> searchList = nStore.selectNoticesByTitle(session, searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> searchNoticesByContent(String searchKeyword) {
		List<Notice> searchList = nStore.selectNoticesByContent(session, searchKeyword);
		return searchList;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = nStore.getListCount(session, paramMap);
		return result;
	}

	@Override
	public Notice selectNoticeByNo(int noticeNo) {
		Notice notice = nStore.selectNoticeByNo(session, noticeNo);
		return notice;
	}

}
