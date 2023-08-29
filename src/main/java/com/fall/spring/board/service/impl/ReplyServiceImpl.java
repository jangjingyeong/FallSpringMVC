package com.fall.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fall.spring.board.domain.Reply;
import com.fall.spring.board.service.ReplyService;
import com.fall.spring.board.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private ReplyStore rStore;
	
	@Override
	public int insertReply(Reply reply) {
		int result = rStore.insertReply(session, reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = rStore.updateReply(session, reply);
		return result;
	}

	@Override
	public int deleteReply(Reply reply) {
		int result = rStore.deleteReply(session, reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(int refBoardNo) {
		List<Reply> rList = rStore.selectReplyList(session, refBoardNo);
		return rList;
	}

}
