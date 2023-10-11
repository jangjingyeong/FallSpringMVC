package com.fall.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fall.spring.member.domain.Member;
import com.fall.spring.member.service.MemberService;
import com.fall.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired 
	private SqlSession session;
	@Autowired
	private MemberStore mStore;
	
	@Override
	public int insertMember(Member member) {
		int result = mStore.insertMember(session, member);
//		mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = mStore.updateMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

	@Override
	public int checkMemberLogin(Member member) {
		int result = mStore.checkMemberLogin(session, member);
		return result;
	}

	@Override
	public Member memberLoginCheck(Member member) {
		Member mOne = mStore.selectCheckLogin(session, member);
		return mOne;
	}

	@Override
	public Member showOneById(String memberId) {
		Member mOne = mStore.selectOneById(session, memberId);
		return mOne;
	}

}
