<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>마이페이지</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<fieldset>
			<legend>마이페이지</legend>
			<ul>
				<li>
					<label>아이디</label>
					${mOne.memberId }
				</li>
				<li>
					<label>이름</label>
					${mOne.memberName }
				</li>
				<li>
					<label>나이</label>
					${mOne.memberAge }
				</li>
				<li>
					<label>성별</label>
					<c:if test="${mOne.memberGender eq 'M' }">남자</c:if>
					<c:if test="${mOne.memberGender eq 'F' }">여자</c:if>
				</li>
				<li>
					<label>이메일</label>
					${mOne.memberEmail }
				</li>
				<li>
					<label>전화번호</label>
					${mOne.memberPhone }
				</li>
				<li>
					<label>주소</label>
					${mOne.memberAddress }
				</li>
				<li>
					<label>취미</label>
					${mOne.memberHobby }
				</li>
			</ul>
		</fieldset>
		<div>
			<a href="/index.jsp">메인으로 이동</a>
			<a href="/member/update.kh?memberId=${mOne.memberId }">정보수정</a>
			<a href="javascript:void(0)" onclick="checkDelete();">회원탈퇴</a>
		</div>
		<script>
		function checkDelete() {
			const memberId = '${sessionScope.memberId}';
			if(confirm("탈퇴하시겠습니까?")) {
				location.href = "/member/delete.kh?memberId=" + memberId;
			}
		}
		</script>
	</body>
</html>