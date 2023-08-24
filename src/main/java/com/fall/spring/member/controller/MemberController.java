package com.fall.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fall.spring.member.domain.Member;
import com.fall.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "/member/register.kh", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	
	@RequestMapping(value = "/member/register.kh", method = RequestMethod.POST)
	public String registerMember(
			Model model
//			, @RequestParam("memberId") String memberId
//			, @RequestParam("memberPw") String memberPw
//			, @RequestParam("memberName") String memberName
//			, @RequestParam("memberAge") int memberAge
//			, @RequestParam("memberGender") String memberGender
//			, @RequestParam("memberEmail") String memberEmail
//			, @RequestParam("memberPhone") String memberPhone
//			, @RequestParam("memberAddress") String memberAddress
//			, @RequestParam("memberHobby") String memberHobby
			, @ModelAttribute Member member
			) {
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				// 성공하면 로그인 페이지
				// home.jsp가 로그인할 수 있는 페이지가 되면 됨!!
				return "redirect:/index.jsp";
			} else {
				// 실패하면 에러 페이지
				model.addAttribute("msg", "회원 가입이 완료되지 않았습니다.");
				model.addAttribute("error", "회원 가입 실패");
				model.addAttribute("url", "/member/register.kh");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/member/update.kh", method = RequestMethod.GET)
	public String showUpdateMember(
			Model model
			, @RequestParam("memberId") String memberId) {
		try {
			Member mOne = service.showOneById(memberId);
			if(mOne != null) {
				model.addAttribute("mOne", mOne);
				return "member/modify";
			} else {
				model.addAttribute("msg", "회원 정보를 불러오지 못했습니다.");
				model.addAttribute("error", "회원 정보 조회 실패");
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
	
	@RequestMapping(value = "/member/update.kh", method = RequestMethod.POST)
	public String modifyMember(
			Model model
			, RedirectAttributes redirect
			, @ModelAttribute Member member) {
		try {
			int result = service.modifyMember(member);
			if(result > 0) {
				redirect.addAttribute("memberId", member.getMemberId());
				return "redirect:/member/mypage.kh";
			} else {
				model.addAttribute("msg", "회원 정보 수정이 완료되지 않았습니다.");
				model.addAttribute("error", "회원 정보 수정 실패");
				model.addAttribute("url", "/member/mypage.kh?memberId"+member.getMemberId());
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	
	@RequestMapping(value = "/member/delete.kh", method = RequestMethod.GET)
	public String removeMember(
			Model model
			, @RequestParam("memberId") String memberId) {
		try {
			int result = service.removeMember(memberId);
			if(result > 0) {
				return "redirect:/member/logout.kh";
			} else {
				model.addAttribute("msg", "회원 탈퇴가 완료되지 않았습니다.");
				model.addAttribute("error", "회원 탈퇴 실패");
				model.addAttribute("url", "/member/mypage.kh");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/member/login.kh", method = RequestMethod.POST)
	public String memberLogin(
			Model model
			, HttpSession session
			, @ModelAttribute Member member) {
		try {
			// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
			Member mOne = service.memberLoginCheck(member);
			// SELECT COUNT(*) FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
//			int result = service.checkMemberLogin(member);
			
			if(mOne != null) {
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
//			if(result > 0) {
//				session.setAttribute("memberId", member.getMemberId());
				return "redirect:/index.jsp";
			} else { 
				model.addAttribute("msg", "로그인이 되지 않았습니다.");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping(value = "/member/logout.kh", method = RequestMethod.GET)
	public String memberLogout(
			Model model
			, HttpSession session) {
		if(session != null) {
			session.invalidate();
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "로그아웃을 완료하지 못하였습니다.");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value = "/member/mypage.kh", method = {RequestMethod.GET, RequestMethod.POST})
	public String showDetailMember(
			Model model
//			, @RequestParam("memberId") String memberId
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			Member mOne = null;
			if(memberId != "" && memberId != null) {
				mOne = service.showOneById(memberId);
			}
			if(mOne != null) {
				model.addAttribute("mOne", mOne);
				return "member/mypage";
			} else {
				model.addAttribute("msg", "회원 정보를 불러오지 못했습니다.");
				model.addAttribute("error", "회원 정보 조회 실패");
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
	
	
	
	
}
