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
		<form action="/member/update.kh" method="post">
			<fieldset>
				<legend>회원 정보 수정</legend>
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="memberId" class="input-text" value="${mOne.memberId }" readonly>
					</li>
					<li>
						<label>비밀번호</label>
						<input type="password" name="memberPw" class="input-text" >
					</li>
					<li>
						<label>이름</label>
						<input type="text" name="memberName" class="input-text" value="${mOne.memberName }" readonly>
					</li>
					<li>
						<label>나이</label>
						<input type="text" name="memberAge" class="input-text" value="${mOne.memberAge }"readonly>
					</li>
					<li>
						<label>성별</label>
						<input type="hidden" name="${mOne.memberGender }">
						<c:if test="${mOne.memberGender eq 'M' }">남자</c:if>
						<c:if test="${mOne.memberGender eq 'F' }">여자</c:if>
					</li>
					<li>
						<label>이메일</label>
						<input type="text" name="memberEmail" class="input-text" value="${mOne.memberEmail }">
					</li>
					<li>
						<label>전화번호</label>
						<input type="text" name="memberPhone" class="input-text" value="${mOne.memberPhone }">
					</li>
					<li>
						<label>주소</label>
						<input type="text" name="memberAddress" id="memberAddr" class="input-text" value="${mOne.memberAddress }"> 
						<input type="button" onclick="sample4_execDaumPostcode();" value="우편번호 찾기">
					</li>
					<li>
						<label>취미</label>
						<input type="text" name="memberHobby" class="input-text" value="${mOne.memberHobby }">
					</li>
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="수정" class="input-btn">
				<input type="reset" value="초기화" class="input-btn">
			</div>
		</form>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			 function sample4_execDaumPostcode() {
			        new daum.Postcode({
			        	oncomplete : function(data) {
			        		document.querySelector("#memberAddr")
			        		.value = "(" + data.zonecode + ") " + data.roadAddress + ", " + data.buildingName;
			        	}
			        }).open();
			 }
		</script>
	</body>
</html>