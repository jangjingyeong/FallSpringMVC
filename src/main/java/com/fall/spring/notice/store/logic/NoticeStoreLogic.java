package com.fall.spring.notice.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fall.spring.notice.domain.Notice;
import com.fall.spring.notice.domain.PageInfo;
import com.fall.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage(); // 가져오는 행의 갯수 
		int offset = (pInfo.getCurrentPage()-1)*limit; // 변하는 디폴트값 
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.selectListCount");
		return result;
	}

	@Override
	public List<Notice> selectNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage(); // 가져오는 행의 갯수 
		int offset = (pInfo.getCurrentPage()-1)*limit; // 변하는 디폴트값 
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public List<Notice> selectNoticesByAll(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByAll", searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> selectNoticesByWriter(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByWriter", searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> selectNoticesByTitle(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByTitle", searchKeyword);
		return searchList;
	}

	@Override
	public List<Notice> selectNoticesByContent(SqlSession session, String searchKeyword) {
		List<Notice> searchList = session.selectList("NoticeMapper.selectNoticesByContent", searchKeyword);
		return searchList;
	}

	@Override
	public int getListCount(SqlSession session, Map<String, String> paramMap) {
		int result = session.selectOne("NoticeMapper.selectListByKeywordCount", paramMap);
		return result;
	}

	@Override
	public Notice selectNoticeByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectNoticeByNo", noticeNo);
		return notice;
	}
	
	// 이걸 controller에서 쓴다 
//	public void generatePageNavi() {
//		
//	}
	

}
