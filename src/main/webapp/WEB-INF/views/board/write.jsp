<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 등록</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>글 등록</h1>
		<form action="/board/write.kh" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="boardTitle">
				</li>
				<li>
					<label>작성자</label>
					<span>${sessionScope.memberId }</span>
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="boardContent"></textarea>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- 첨부 파일은 String으로 받을 수 없어서 변환 작업이 필요 -->
					<input type="file" name="uploadFile">
				</li>
			</ul>
			<div>
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
				<input type="button" onclick="showBoardList();" value="목록으로">
			</div>
		</form>	
		<script>
			function showBoardList() {
				location.href="/board/list.kh";
			}
		</script>
	</body>
</html>