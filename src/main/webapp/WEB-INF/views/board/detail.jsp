<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				</li>
			</ul>
			<br><br>
			<div>
				<button type="button" onclick="showModifyPage();">수정하기</button>
				<button>삭제하기</button>
				<button type="button" onclick="showBoardList();">목록으로</button>
			</div>
			<!-- 댓글 등록 -->
			<br><hr><br>
			<form action="/board/addReply.kh" method="post">
				<table width="500" border="1">
					<tr>
						<td>
							<textarea rows="3" cols="55"></textarea>
						</td>
						<td>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="500" border="1">
				<tr>
					<td>일용자</td>
					<td>아 처음이시군요 환영합니다.</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>			
				<tr>
					<td>삼용자</td>
					<td>환영합니다.</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>			
				<tr>
					<td>사용자</td>
					<td>반갑습니다</td>
					<td>2023-08-24</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>			
			</table>
			<script>
				function showModifyPage() {
					const boardNo = "${boardOne.boardNo}";
					location.href="/board/modify.kh?boardNo=" + boardNo;
				}
				function showBoardList() {
					location.href="/board/list.kh";
				}
			</script>
	</body>
</html>