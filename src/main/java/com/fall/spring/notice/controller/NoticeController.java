package com.fall.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fall.spring.notice.domain.Notice;
import com.fall.spring.notice.domain.PageInfo;
import com.fall.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.GET)
	public String showInsertForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.POST)
	public String insertNotice(
			@ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			// required=false 필수가 아니도록 false 해줌  
			, HttpServletRequest request
			, Model model) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				// =================== 파일 이름 ===================
				String fileName = uploadFile.getOriginalFilename();
				// (내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것) 
				String root = request.getSession().getServletContext().getRealPath("resources");
				// 폴더가 없을 경우 자동 생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
				File folder = new File(saveFolder); // java.io
				if(!folder.exists()) {
					folder.mkdir();
				}
				// =================== 파일 경로 ===================
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				// ******************** 파일저장 ********************
				uploadFile.transferTo(file);
				
				// =================== 파일 크기 ===================
				long fileLength = uploadFile.getSize();
				
				// DB에 저장하기 위해 notice에 데이터를 Set하는 부분임.
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}
			int result = service.insertNotice(notice);
			if(result > 0) {
				
				return "redirect:/notice/list.kh";
			} else {
				model.addAttribute("msg", "공지사항 등록이 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 등록 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/notice/list.kh", method = RequestMethod.GET)
	public String showNoticeList(
			// page는 필수가 아니다 -> required=false, 값이 없으면 defaultValue = "1", null 체크를 위해 Integer 씀
			@RequestParam(value="page", required=false, defaultValue = "1") Integer currentPage
			, Model model) {
		try {
			// int currentPage = page != 0 ? page : 1; // 삼항연산자 대신 RequestParam으로 설정 가능
			// SELECT COUNT(*) FROM NOTICE_TBL
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			// List 데이터의 유효성 체크 방법 2가지 
			// 1. isEmpty() 
			// 2. size()
			if(nList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("nList", nList);
				return "notice/list";
			} else {
				model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 목록 조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	public PageInfo getPageInfo(int currentPage, int totalCount) {
		PageInfo pi = null;
		int recordCountPerPage = 10; // 한 페이지당 보여질 게시물의 개수
		int naviCountPerPage = 10; // 한 페이지 범위에 보여질 페이지의 개수
		int naviTotalCount; // 범위의 총 개수
		int startNavi; // 각 페이지 범위 시작 번호
		int endNavi; // 각 페이지 범위 끝 번호
		
		naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		// Math.ceil((double)totalCount/recordCountPerPage) 이렇게도 쓸 수 있음 
		// currentPage값이 1~5일 때 startNavi가 1로 고정되도록 구해주는 식 
		startNavi = (((int)((double)currentPage/naviCountPerPage+0.9))-1)*naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage -1;
		// endNavi는 startNavi에 무조건 naviCountPerPage값을 더하므로 
		// 실제 최대값보다 커질 수 있음 
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		// return recordCountPerPage, naviCountPerPage, naviTotalCount, startNavi, endNavi;
		// 이 값들을 다 넣어줄 객체를 만들어야 하기 때문에 domain에 PageInfo 클래스 생성
		pi= new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
	}
	
	@RequestMapping(value = "/notice/search.kh", method = RequestMethod.GET)
	public String searchNoticeList(
			@RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchKeyword") String searchKeyword
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
		// 2개의 값을 하나의 변수로 다루는 방법
		// 1. VO 클래스 만드는 방법(이미 해봄)
		// 2. HashMap 사용하는 방법(안 해봄) 
		Map<String, String> paramMap = new HashMap<String, String>();
		// put() 메소드를 사용해서 key-value 설정을 하는데
		// key값(파란색)이 mapper.xml에서 사용됨!!
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Notice> searchList = service.searchNoticesByKeyword(pInfo, paramMap);
//		List<Notice> searchList = new ArrayList<Notice>();
//		switch(searchCondition) {
//		case"all" : 
//			// SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%공지%' OR NOTICE_CONTENT LIKE '%공지%' OR NOTICE_WRITER LIKE '%공지%';
//			searchList = service.searchNoticesByAll(searchKeyword);
//			break;
//		case"writer" : 
//			// SELECT * FROM NOTICE_TBL WHERE NOTICE_WRITER LIKE '%'||#{searchKeyword}||'%';
//			searchList = service.searchNoticesByWriter(searchKeyword);
//			break;
//		case"title" : 
//			// SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%'||#{searchKeyword}||'%';
//			searchList = service.searchNoticesByTitle(searchKeyword);
//			break;
//		case"content" : 
//			// SELECT * FROM NOTICE_TBL WHERE NOTICE_CONTENT LIKE '%'||#{searchKeyword}||'%';
//			searchList = service.searchNoticesByContent(searchKeyword);
//			break;
//		}
		if(!searchList.isEmpty()) {
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "notice/search";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "공지사항 제목으로 조회 실패");
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
	}
	
	
	
	
	
	
	
	
}
