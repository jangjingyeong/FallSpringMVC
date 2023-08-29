package com.fall.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fall.spring.board.domain.Board;
import com.fall.spring.board.domain.PageInfo;
import com.fall.spring.board.service.BoardService;
import com.fall.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private BoardStore bStore;
	
	@Override
	public int insertBoard(Board board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}

	@Override
	public int updateBoard(Board board) {
		int result = bStore.updateBoard(session, board);
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result = bStore.deleteBoard(session, boardNo);
		return result;
	}

	@Override
	public int deleteBoard(Board board) {
		int result = bStore.deleteBoard(session, board);
		return result;
	}

	@Override
	public int getListCount() {
		int result = bStore.selectListCount(session);
		return result;
	}

	@Override
	public List<Board> selectBoardList(PageInfo pInfo) {
		List<Board> bList = bStore.selectBoardList(session, pInfo);
		return bList;
	}

	@Override
	public Board selectBoardByNo(Integer boardNo) {
		Board board = bStore.selectBoardByNo(session, boardNo);
		return board;
	}

}
