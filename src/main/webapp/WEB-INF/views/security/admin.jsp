<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 화면</title>
</head>
<body>
	<h1>관리자 화면!!</h1>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication var="user" property="principal" />
		<div>${sessionScope.info.name} <a href="/logout">로그아웃 하기</a></div>
	</sec:authorize>
	<a href="/">홈 이동</a>
</body>
</html>