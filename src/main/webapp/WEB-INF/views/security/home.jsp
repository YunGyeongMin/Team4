<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<sec:authorize access="isAnonymous()">
		<a href="/login">로그인 하기</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication var="user" property="principal" />
		<div>${sessionScope.info.name} <a href="/logout">로그아웃 하기</a></div>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/admin">관리자 화면</a>
	</sec:authorize>
</body>
</html>
