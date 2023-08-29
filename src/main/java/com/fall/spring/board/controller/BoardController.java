package com.fall.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fall.spring.board.domain.Board;
import com.fall.spring.board.domain.PageInfo;
import com.fall.spring.board.domain.Reply;
import com.fall.spring.board.service.BoardService;
import com.fall.spring.board.service.ReplyService;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@Autowired
	private ReplyService rService;
	
	@RequestMapping(value = "/board/write.kh", method = RequestMethod.GET) 
	public ModelAndView showWriteForm(ModelAndView mv) {
		// ModelAndView를 통해 페이지 이동과 데이터 보내기 가능 
		mv.setViewName("board/write");
		return mv;
	}

	@RequestMapping(value = "/board/write.kh", method = RequestMethod.POST)
		public ModelAndView boardRegister(
				ModelAndView mv // ModelAndView에 기능이 많아서 이걸로 사용! 
				, @ModelAttribute Board board
				, @RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
				, HttpServletRequest request
				, HttpSession session) {
			try {
				String boardWriter = (String)session.getAttribute("memberId");
				if(boardWriter != null && !boardWriter.equals("")) {
					board.setBoardWriter(boardWriter);
					if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
						// 파일 정보(이름, 리네임, 경로, 크기) 및 파일 저장 
						Map<String, Object> bMap = this.saveFile(request, uploadFile);
						board.setBoardFilename((String)bMap.get("fileName"));
						board.setBoardFileRename((String)bMap.get("fileRename"));
						board.setBoardFilepath((String)bMap.get("filePath"));
						board.setBoardFilelength((long)bMap.get("fileLength"));
					}
					int result = bService.insertBoard(board);
					if(result > 0) {
						mv.setViewName("redirect:/board/list.kh");
					} else {
						mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
						mv.addObject("error", "게시글 등록 실패");
						mv.addObject("url", "/board/write.kh");
						mv.setViewName("common/errorPage");
					}
				} else {
					mv.addObject("msg", "로그인 정보가 존재하지 않습니다.");
					mv.addObject("error", "로그인이 필요합니다.");
					mv.addObject("url", "/index.jsp");
					mv.setViewName("common/errorPage");
				}
			} catch (Exception e) {
	//			model.addAtrribute("msg", "등록이 완료되지 않았습니다.");
				mv.addObject("msg", "관리자에게 문의하세요.");
				mv.addObject("error", e.getMessage());
				mv.addObject("url", "/board/write.kh");
				mv.setViewName("common/errorPage");
			}
			return mv;
		}

	@RequestMapping(value = "/board/modify.kh", method = RequestMethod.GET)
	public ModelAndView showModifyForm(ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo) {
		try {
			Board board = bService.selectBoardByNo(boardNo);
			mv.addObject("board", board);
			mv.setViewName("board/modify");
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의하세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value="/board/modify.kh", method=RequestMethod.POST)
	public ModelAndView boardModify(ModelAndView mv
			, @ModelAttribute Board board
			, @RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
			, HttpServletRequest request
			, HttpSession session) {
		try {
			String boardWriter = (String)session.getAttribute("memberId");
			if(boardWriter != null && !boardWriter.equals("")) {
				board.setBoardWriter(boardWriter);
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					// 기존 업로드 파일 있으면 삭제 
					String fileRename = board.getBoardFileRename();
					if(fileRename != null) {
						this.deleteFile(fileRename, request);
					}
					// 새로운 파일 저장 - 파일 정보(이름, 리네임, 경로, 크기) 및 파일 저장 
					Map<String, Object> bMap = this.saveFile(request, uploadFile);
					board.setBoardFilename((String)bMap.get("fileName"));
					board.setBoardFileRename((String)bMap.get("fileRename"));
					board.setBoardFilepath((String)bMap.get("filePath"));
					board.setBoardFilelength((long)bMap.get("fileLength"));
				}
				int result = bService.updateBoard(board);
				if(result > 0) {
					mv.setViewName("redirect:/board/detail.kh?boardNo="+board.getBoardNo());
				} else {
					mv.addObject("msg", "게시글 수정이 완료되지 않았습니다.");
					mv.addObject("error", "게시글 수정 실패");
					mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
					mv.setViewName("common/errorPage");
				} 
			} else {
				mv.addObject("msg", "로그인 정보가 존재하지 않습니다.");
				mv.addObject("error", "로그인이 필요합니다.");
				mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
//			model.addAtrribute("msg", "등록이 완료되지 않았습니다.");
			mv.addObject("msg", "관리자에게 문의하세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
		
		
		

	@RequestMapping(value = "/board/delete.kh", method = RequestMethod.GET)
	public ModelAndView boardDelete(ModelAndView mv
			, @ModelAttribute Board board
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			String boardWriter = board.getBoardWriter();
			if(boardWriter != null && boardWriter.equals(memberId)) {
//				int result = bService.deleteBoard(board.getBoardNo());
				int result = bService.deleteBoard(board);
				if(result > 0) {
					mv.setViewName("redirect:/board/list.kh");
				} else {
					mv.addObject("msg", "게시글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "게시글 삭제 실패");
					mv.addObject("url", "/board/list.kh");
					mv.setViewName("common/errorPage");
				}
			} else {
				mv.addObject("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
				mv.addObject("error", "게시글 삭제 불가");
				mv.addObject("url", "/board/list.kh");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의하세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "/board/list.kh", method = RequestMethod.GET)
	public ModelAndView showBoardList(
			@RequestParam(value = "page", required=false, defaultValue = "1") Integer currentPage
			, ModelAndView mv) {
		try {
			Integer totalCount = bService.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Board> bList = bService.selectBoardList(pInfo);
			if(!bList.isEmpty()) {
				mv.addObject("pInfo", pInfo).addObject("bList", bList).setViewName("board/list");
			} else {
				mv.addObject("msg", "게시글 목록 조회가 완료되지 않았습니다.");
				mv.addObject("error", "게시글 목록 조회 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의하세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/index.jsp");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value = "/board/detail.kh", method = RequestMethod.GET)
	public ModelAndView showBoardDetail(
			@RequestParam("boardNo") Integer boardNo
			, ModelAndView mv) {
		try {
			Board boardOne = bService.selectBoardByNo(boardNo);
			if(boardOne != null) {
				List<Reply> replyList = rService.selectReplyList(boardNo);
				if(replyList.size() > 0) {
					mv.addObject("rList", replyList);
				}
				mv.addObject("boardOne", boardOne);
				mv.setViewName("board/detail");
			} else {
				mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
				mv.addObject("error", "게시글 상세 조회 실패");
				mv.addObject("url", "/board/list.kh");
				mv.setViewName("common/errorPage");
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의하세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 파일 저장 경로 구하기
		String savePath = root + "\\buploadFiles";
		// 파일 이름 
		String fileName = uploadFile.getOriginalFilename(); 
		// 파일 확장자 구하기
		String extension = fileName.substring(fileName.lastIndexOf(".")+1);
		// 시간으로 파일 리네임, 리네임을 위한 포맷 만들기 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = "B" + sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
		// 파일 저장 전폴더 만들기 
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		// 파일 저장
		File saveFile = new File(savePath+"\\"+fileRename); // 업로드 파일 
		uploadFile.transferTo(saveFile);
		// 파일 크기
		long fileLength = uploadFile.getSize();
		// 파일 정보 리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/buploadFiles/"+fileRename);
		fileMap.put("fileLength", fileLength);
		return fileMap;
	}

	private void deleteFile(String fileRename, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilepath = root + "\\buploadFiles\\" + fileRename;
		File file = new File(delFilepath);
		if(file.exists()) { // 파일이 존재하는지 확인 
			file.delete(); // 존재하면 삭제 
		}
	}

	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		int naviTotalCount = (int)Math.ceil((double)totalCount / recordCountPerPage);
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pInfo;
	}
	
	
	
	
	
	
}
