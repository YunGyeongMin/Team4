<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach var="row" items="${data.result}">
	<div class="row">
	  <div class="col-sm-3">
	    <div class="well">
	     <p>${row.name}</p>
	     <img src="${row.img}" class="img-circle" height="55" width="55" alt="${row.email}">
	    </div>
	  </div>
	  <div class="col-sm-9">
	    <div class="well" style="min-height: 129px;">
	      <p>${row.message}</p>
	    </div>
	  </div>
	</div>
</c:forEach>
<c:if test="${data.result.size() == 0}">
	<div class="row">
		<div class="col-xs-12">
			<div class="txt-box">
		        <h4>글이 없습니다.</h4>
	        </div>
		</div>
	</div>
</c:if>
</body>
</html>