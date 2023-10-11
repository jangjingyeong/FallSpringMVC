package com.fall.spring.member.service;

import com.fall.spring.member.domain.Member;

public interface MemberService {
	/**
	 * 멤버 등록 Service
	 * @param member
	 * @return int
	 */
	public int insertMember(Member member);
	
	/**
	 * 멤버 수정 Service
	 * @param member
	 * @return int
	 */
	public int updateMember(Member member);
	
	/**
	 * 멤버 삭제 Service
	 * @param memberId
	 * @return int
	 */
	public int deleteMember(String memberId);

	/**
	 * 멤버 로그인 Service
	 * @param Member
	 * @return int
	 */
	public int checkMemberLogin(Member member);

	/**
	 * 멤버 로그인 Service
	 * @param 아이디, 비번
	 * @return member객체
	 */
	public Member memberLoginCheck(Member member);

	/**
	 * 멤버 조회 Service
	 * @param memberId
	 * @return member객체 
	 */
	public Member showOneById(String memberId);
}
