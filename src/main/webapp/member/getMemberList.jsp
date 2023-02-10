<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.springlab.member.MemberDTO" %>

<%
	List<MemberDTO> memberList_jsp = (List) session.getAttribute("memberList_s");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1>회원 목록 출력 페이지</h1>
	<hr>
	
	<h3>환영합니다. <a href = "logout.do">Log-Out</a></h3>
	
	<!-- 검색 기능 추가 -->
	<form action = "getMemberList.jsp" method="post">
		<table border="1" cellspacing="0" cellpadding="0" width="700">
			<tr>
				<td> <select name="searchCondition">
					<option value = "ID">아이디</option>
					<option value = "NAME">이름</option>				
					</select>
					<input type="text" name="searchKeword" />
					<input type="submit" value="검색">
				</td>
			</tr>
		</table>
		
		<!-- 서버에서 담은 List에 저장된 DTO를 끄집어내서 출력 -->
		<table border="1" cellspacing="0" cellpadding="0" width="700">
			<tr>
				<th bgcolor="skyblue" width = "100">번호</th>
				<th bgcolor="skyblue" width = "200">아이디</th>
				<th bgcolor="skyblue" width = "150">이름</th>
				<th bgcolor="skyblue" width = "150">등록일</th>
				<th bgcolor="skyblue" width = "100">조회수</th>
			</tr>
		
			<!-- tr을 for문으로 루프를 돌리면서 List의 DTO값을 끄집어내서 출력 -->
			
			<%
				for (MemberDTO dto : memberList_jsp) {
			%>
			
			<tr>
				<td align = "center"><%= dto.getIdx() %></td>
				<td>
					<a href ="getMember.do?idx=<%= dto.getIdx()%>">
					<%= dto.getId() %>
					</a>
				</td>
				<td align = "center"><%= dto.getName() %></td>
				<td align = "center"><%= dto.getRegDate() %></td>
				<td align = "center"><%= dto.getCnt() %></td>
			</tr>
		
			<%
				}			
			%>
		</table>
		
		<p />
		<a href = "insertMember.jsp"> 새 회원 등록 </a>
		
	</form>
	




</center>	
	
</body>
</html>