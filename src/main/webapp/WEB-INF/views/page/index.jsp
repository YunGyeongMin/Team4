<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:forEach var="row" items="${data.result}">
			<div class="col-sm-3 col-xs-6 cursor-pointer user" data-targetUser="${row.num}">
				<div class="well">
					<p>${row.name}</p>
					<img src="${row.img}" class="img-circle" height="55" width="55" alt="${row.email}">
				</div>
			</div>
		</c:forEach>
	</body>
</html>