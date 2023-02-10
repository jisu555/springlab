<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import = "com.springlab.member.MemberDTO" %>

<%
	MemberDTO member = (MemberDTO) session.getAttribute("member");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>
	<h1>회원가입 상세 페이지</h1>
	<a href = "logout.do">Log-out</a>
	<!-- 폼에서 value에 출력 -->
	
	<form action = "updateMember.do" method = "post">
		<!-- updateBoard.do 페이지로 넘길때 seq -->
		<!-- 출력구문에는 나타나지 않고 변수값 넘길 때 사용 -->
		<input type="hidden" name="idx" value = "<%= member.getIdx() %>">
	
		<table border="1" cellspacing="0" cellpadding="0">
			<tr>
				<td bgcolor="skyblue" width="100">아이디</td>
				<td><%=member.getId() %></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100">비밀번호</td>
				<td><input type="password" name="pass" value= "<%=member.getPass()%>"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100">이름</td>
				<td><input type="text" name="name" value= "<%= member.getName()%>"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100">이메일</td>
				<td><%=member.getEmail() %></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100">나이</td>
				<td><%=member.getAge() %></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100">몸무게</td>
				<td><%=member.getWeight() %></td>
			</tr>
			<tr>
			<tr>
				<td bgcolor="skyblue">등록일</td>
				<td><%=member.getRegDate() %></td>
			</tr>
			<tr>
				<td bgcolor="skyblue">조회수</td>
				<td><%=member.getCnt() %></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정"></td>
			</tr>
		</table>
	</form>
	<a href = "insertMember.jsp"> 회원 등록</a> &nbsp;&nbsp;&nbsp;
	<a href = "deleteMember.do?idx=<%= member.getIdx() %>"> 회원 삭제</a>&nbsp;&nbsp;&nbsp;
	<a href = "getMemberList.do">회원 목록</a>



</center>



</body>
</html>