package com.fall.spring.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fall.spring.board.domain.Reply;
import com.fall.spring.board.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService rService;

	@RequestMapping(value = "/add.kh", method = RequestMethod.POST)
	public ModelAndView insertReply(ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			reply.setReplyWriter(replyWriter);
			int result = rService.insertReply(reply);
			url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
			if(result > 0) {
				// 댓글 등록 성공 
				mv.setViewName("redirect:"+url);
			} else {
				// 댓글 등록 실패 
				mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
				mv.addObject("error", "댓글 등록 실패");
				mv.addObject("url", url);
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value = "/update.kh", method = RequestMethod.POST)
	public ModelAndView updateReply(ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			if(replyWriter != null && !replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
				int result = rService.updateReply(reply);
				mv.setViewName("redirect:"+url);
			} else {
				mv.addObject("msg", "로그인이 되지 않았습니다.");
				mv.addObject("error", "로그인 정보 확인 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/delete.kh", method = RequestMethod.GET)
	public ModelAndView deleteReply(ModelAndView mv
			, @ModelAttribute Reply reply
//			, @RequestParam("replyNo") Integer replyNo
//			, @RequestParam("replyWriter") String replyWriter
			, HttpSession session) {
		String url = "";
		try {
			String memberId = (String)session.getAttribute("memberId");
			String replyWriter = reply.getReplyWriter();
			url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
			if(replyWriter != null && replyWriter.equals(memberId)) {
//			if(replyWriter.equals(memberId)) {
//				Reply reply = new Reply();
//				reply.setReplyNo(replyNo);
//				reply.setReplyWriter(replyWriter);
				int result = rService.deleteReply(reply);
				if(result > 0) {
					// 성공 
					// 바뀔 때 주의 해야할 것 redirect 다음에 : 
					mv.setViewName("redirect:"+url);
				} else {
					// 실패
					mv.addObject("msg", "댓글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "댓글 삭제 실패");
					mv.addObject("url", url);
					mv.setViewName("common/errorPage");
				}
			} else {
				mv.addObject("msg", "자신의 댓글만 삭제할 수 있습니다.");
				mv.addObject("error", "댓글 삭제 불가");
				mv.addObject("url", url);
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	
	
	
}
