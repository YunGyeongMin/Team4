<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접속 오류</title>
</head>
<body>
	<h1>You Authorize Fail! - 
		<sec:authorize access="isAuthenticated()">
			<sec:authentication var="user" property="principal" />
			${sessionScope.info.name}
		</sec:authorize>
	</h1>
	<a href="/">홈으로 돌아가기</a>
</body>
</html>