<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form class="form-horizontal">
	<h2>Information</h2>
	<div class="form-group">
    	<label class="control-label col-sm-2" for="name">Name:</label>
    	<div class="col-sm-10">
      		<input type="text" class="form-control" id="name" value="${userInfo.name}" required="required">
    	</div>
  	</div>
	<div class="form-group">
    	<label class="control-label col-sm-2" for="email">Email:</label>
    	<div class="col-sm-10">
      		<input type="email" class="form-control" id="email" value="${userInfo.email}" required="required">
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="control-label col-sm-2" for="phone">Phone Cell:</label>
    	<div class="col-sm-10">
      		<input type="tel" class="form-control" id="phone" value="${userInfo.phone}">
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="control-label col-sm-2" for="pwd">Password:</label>
    	<div class="col-sm-10">
      		<input type="password" class="form-control" id="pwd" placeholder="신규 비밀번호를 입력하세요.">
    	</div>
  	</div>
  	<div class="form-group txt-body">
	  	<h2>Interests</h2>
	  	<div class="row">
	  		<c:forEach var="row" items="${userInterests.result}">
		  		<div class="col-xs-6 col-sm-4 col-md-3 cursor-pointer intersetsItem" data-interest="${row.num}" data-state="${row.state}">
					<c:choose>
						<c:when test="${row.state eq '1'}">
							<div class="txt-box txt-active">
						        <h4>${row.interest_name}</h4>
					        </div>
						</c:when>
						<c:otherwise>
							<div class="txt-box">
						        <h4>${row.interest_name}</h4>
					        </div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
	</div>
  	<div class="form-group button-body">
      	<button type="submit" class="btn btn-success btn-block font-default">수정</button>
	</div>
</form>

</body>
</html>