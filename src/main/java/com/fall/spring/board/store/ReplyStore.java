package com.fall.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fall.spring.board.domain.Reply;

public interface ReplyStore {

	/**
	 * 댓글 등록 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);

	/**
	 * 댓글 수정 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int updateReply(SqlSession session, Reply reply);

	/**
	 * 댓글 전체 조회 Store
	 * @param session
	 * @return
	 */
	List<Reply> selectReplyList(SqlSession session, int refBoardNo);

}