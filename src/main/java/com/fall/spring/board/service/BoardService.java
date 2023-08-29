package com.fall.spring.board.service;



import java.util.List;

import com.fall.spring.board.domain.Board;
import com.fall.spring.board.domain.PageInfo;

public interface BoardService {

	
	/**
	 * 게시글 등록 Service
	 * @param board
	 * @return int 
	 */
	int insertBoard(Board board);

	/**
	 * 게시글 수정 Service
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);

	/**
	 * 게시글 삭제 Service
	 * @param boardNo
	 * @return
	 */
	int deleteBoard(int boardNo);

	/**
	 * 게시글 삭제 Service (update)
	 * @param board
	 * @return
	 */
	int deleteBoard(Board board);

	/**
	 * 게시글 전체 갯수 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 게시글 전체 조회 Service
	 * @param pInfo
	 * @return List
	 */
	List<Board> selectBoardList(PageInfo pInfo);

	/**
	 * 게시글 상세 조회 Service
	 * @param boardNo
	 * @return Board
	 */
	Board selectBoardByNo(Integer boardNo);


}
