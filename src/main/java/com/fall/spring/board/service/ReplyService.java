package com.fall.spring.board.service;

import java.util.List;

import com.fall.spring.board.domain.Reply;

public interface ReplyService {

	/**
	 * 댓글 등록 Service
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);

	/**
	 * 댓글 수정 Service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);

	/**
	 * 댓글 삭제 Service
	 * @param reply
	 * @return
	 */
	int deleteReply(Reply reply);

	/**
	 * 댓글 전체 조회 Service
	 * @return
	 */
	List<Reply> selectReplyList(int refBoardNo);

}
