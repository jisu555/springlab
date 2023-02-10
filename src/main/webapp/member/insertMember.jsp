<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Board</title>
</head>
<body>

<center>
	<h1> 회원가입 </h1>
	<a href = "logout.do">Log-Out</a>
	<form action = "insertMember.do" method="post">
		<table border="1" cellspacing="0" cellpadding="0">
			<tr>
				<td bgcolor="skyblue" width="100" align="center">아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100" align="center">비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100" align="center">이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100" align="center">이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100" align="center">나이</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td bgcolor="skyblue" width="100" align="center">몸무게</td>
				<td><input type="text" name="weight"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="가입하기"></td>
			</tr>
		</table>	
	</form>
	<p /><hr>
		<a href = "getMemberList.do"> 회원 목록 바로가기 </a>

</center>

</body>
</html>