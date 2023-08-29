<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 상세</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>게시글 상세</h1>
		
			<ul>
				<li>
					<label>제목</label>
					<span>${boardOne.boardTitle }</span>
				</li>
				<li>
					<label>작성자</label>
					<span>${boardOne.boardWriter }</span>
				</li>
				<li>
					<label>작성일</label>
					<span>${boardOne.bCreateDate }</span>
				</li>
				<li>
					<label>수정일</label>
					<span>${boardOne.bUpdateDate }</span>
				</li>
				<li>
					<label>내용</label>
					<p>${boardOne.boardContent }</p>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- 첨부 파일은 String으로 받을 수 없어서 변환 작업이 필요 -->
<%-- 					<img alt="첨부파일" src="../resources/nuploadFiles/${noticeOne.noticeFilename }"> --%>
					<a href="../resources/buploadFiles/${boardOne.boardFileRename }" download>${boardOne.boardFilename }</a> 
					<c:if test="${not empty boardOne.boardFilename }">
						<a href="#">삭제하기</a>
					</c:if>
				</li>
			</ul>
			<br><br>
			<div>
			<c:url var="boardDelUrl" value="/board/delete.kh">
				<c:param name="boardNo" value="${boardOne.boardNo}"></c:param>
				<c:param name="boardWriter" value="${boardOne.boardWriter}"></c:param>
			</c:url>
			<c:url var="modifyUrl" value="/board/modify.kh">
				<c:param name="boardNo" value="${boardOne.boardNo }"></c:param>
			</c:url>
				<c:if test="${boardOne.boardWriter eq memberId }">
					<button type="button" onclick="showModifyPage('${modifyUrl}');">수정하기</button>
					<button type="button" onclick="deleteBoard('${boardDelUrl}');">삭제하기</button>
				</c:if>
				<button type="button" onclick="showBoardList();">목록으로</button>
				<button type="button" onclick="javascripy:history.go(-1);">뒤로가기</button>
			</div>
			<!-- 댓글 등록 -->
			<br><hr><br>
			<form action="/reply/add.kh" method="post">
				<input type="hidden" name="refBoardNo" value="${boardOne.boardNo}">
				<table width="500" border="1">
					<tr>
						<td>
							<textarea rows="3" cols="55" name="replyContent"></textarea>
						</td>
						<td>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="600" border="1">
				<c:forEach var="reply" items="${rList }">
				<!-- forEach에서 var는 List에 있는 것들을 꺼내쓸 변수명 items는 값을 꺼낼 List -->
				<tr>
					<td>${reply.replyWriter }</td>
					<td>${reply.replyContent }</td>
					<td>${reply.rCreateDate }</td>
					<td>
<%-- 						<a href="javascript:void(0)" onclick="showModifyForm(this, '${reply.replyContent }');">수정하기</a> / <a href="javascript:void(0)" onclick="deleteReply();" >삭제하기</a> --%>
<%-- 						<c:if test="${ reply.replyWriter eq memberId}"> --%>
							<a href="javascript:void(0)" onclick="showReplyModifyForm(this);">수정하기</a> / 
							<c:url var="delUrl" value="/reply/delete.kh">
								<c:param name="replyNo" value="${reply.replyNo }"></c:param>
								<!-- 내가 쓴 댓글만 지우도록 하기 위해서 replyWriter 추가! -->
								<c:param name="replyWriter" value="${reply.replyWriter }"></c:param>
								<!-- 성공하면 디테일로 가기 위해 필요한 boardNo 셋팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo }"></c:param>
							</c:url>
							<a href="javascript:void(0)" onclick="deleteReply('${delUrl}');" >삭제하기</a>
<%-- 						</c:if> --%>
					</td>
				</tr>
				<tr id="replyModifyForm" style="display:none;">
<!-- 					<form action="/reply/update.kh" method="post"> -->
<%-- 						<input type="hidden" name="replyNo" value="${reply.replyNo }" > --%>
<%-- 						<input type="hidden" name="refBoardNo" value="${reply.refBoardNo }" > --%>
<%-- 						<td colspan="3"><input type="text" size="20" name="replyContent" value="${reply.replyContent }"></td> --%>
<!-- 						<td><input type="submit" value="완료"></td> -->
<!-- 					</form> -->
						<td colspan="3"><input type="text" id="replyContent" size="20" name="replyContent" value="${reply.replyContent }"></td>
						<td><input type="button" onclick="replyModifyReply(this, '${reply.replyNo}','${reply.refBoardNo }');" value="완료"></td>
				</tr>
				</c:forEach>
			</table>
			<script> 
				// ############################### 게시글 ###############################
				function showModifyPage(modifyUrl) {
					location.href= modifyUrl;
				}
				const deleteBoard = (boardUrl) => {
					location.href= boardUrl;
				}
				function showBoardList() {
					location.href="/board/list.kh";
				}
				// ############################### 게시글 ###############################
				function deleteReply(url) {
// 					alert(url);
					// DELETE FROM REPLY_TBL WHERE REPLY_NO = 샵{replyNo} AND R_STATUS = 'Y'
					// UPDATE REPLY_TBL SET R_STATUS = 'N' WHERE REPLY_NO = 샵{replyNo}
					location.href = url;
				}
				function replyModifyReply(obj, replyNo, refBoardNo) {
					// DOM 프로그래밍을 이용하는 방법
					
					const form = document.createElement("form");
					form.action="/reply/update.kh";
					form.method="post";
					
					const input1 = document.createElement("input");
					input1.type="hidden";
					input1.value=replyNo;
					input1.name="replyNo";
					
					const input2 = document.createElement("input");
					input2.type="hidden";
					input2.value=refBoardNo;
					input2.name="refBoardNo";
					
					const input3 = document.createElement("input");
					input3.type="text";
// 					input3.value=document.querySelector("#replyContent").value;
					// 위처럼 하면 첫번째 댓글 내용으로 다 바뀌어버리기 때문에 this를 이용해야 함 
					// id가 다 같아서 document.querySelector("#replyContent")로는 첫번째 댓글 내용이 선택됨 
// 					input3.value=obj.parentElement.previousElementSibling.childNodes[0].value;
					input3.value=obj.parentElement.previousElementSibling.children[0].value;
					input3.name="replyContent";
					
					// input 태그들을 form에 넣어줌 
					form.appendChild(input1);
					form.appendChild(input2);
					form.appendChild(input3);
					
					document.body.appendChild(form);
					form.submit();
				}
				function showReplyModifyForm(obj, replyContent) {
// 				function showModifyForm(obj, replyContent) {
// 					alert("test");

					// #1. HTML 태그, display:none 사용하는 방법 
// 					document.querySelector("#replyModifyForm").style.display=""; // 이건 맨 위 댓글만 나옴 
					obj.parentElement.parentElement.nextElementSibling.style.display="";


					// #2. DOM프로그래밍을 이용하는 방법 
// 					<tr id="replyModifyForm" style="display:none;">
// 						<td colspan="3"><input type="text" size="20" value="${reply.replyContent }"></td>
// 						<td><input type="button" value="완료"></td>
// 					</tr>
// 					const trTag = document.createElement("tr");
// 					const tdTag1 = document.createElement("td");
// 					tdTag1.colSpan = 3;
// 					const inputTag1 = document.createElement("input");
// 					inputTag1.type="text";
// 					inputTag1.size=50;
// 					inputTag1.value=replyContent;
// 					tdTag1.appendChild(inputTag1);
// 					const tdTag2 = document.createElement("td");
// 					const inputTag2 = document.createElement("input");
// 					inputTag2.type="button";
// 					inputTag2.value="완료";
// 					tdTag2.appendChild(inputTag2);
// 					trTag.appendChild(tdTag1);
// 					trTag.appendChild(tdTag2);
// 					console.log(trTag);
// 					// 클릭한 a를 포함하고 있는 tr 다음에 수정폼이 있는 tr 추가하기 
// 					const prevTrTag = obj.parentElement.parentElement;
// 					prevTrTag.parentNode.insertBefore(trTag, prevTrTag.nextSibling);
				}
				
				
				
				
			</script>
	</body>
</html>