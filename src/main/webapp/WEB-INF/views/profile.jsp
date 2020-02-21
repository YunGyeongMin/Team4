<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html oncontextmenu="return false">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Blog</title>
	<link rel="shortcut icon" type="image/x-icon" href="/res/img/icon_goodee.png">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/lib/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="/lib/jquery/3.4.1/dist/jquery.min.js"></script>
	<script src="/lib/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/res/css/commons.css">
	<script src="/res/js/commons.js"></script>
	<script src="/res/js/profile.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">GDJ21</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav nav-list">
	      	<li><a href="/">Home</a></li>
	      	<li><a href="/myList/${targetUser.num}">${targetUser.name}</a></li>
	      	<li class="active"><a href="/profile/${targetUser.num}">Profile</a></li>
	      </ul>
	      <form class="navbar-form navbar-right" role="search">
	        <div class="form-group input-group">
	          <input type="text" class="form-control" placeholder="검색..">
	          <span class="input-group-btn">
	            <button class="btn btn-default" type="button">
	              <span class="glyphicon glyphicon-search"></span>
	            </button>
	          </span>        
	        </div>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
		<sec:authorize access="isAnonymous()">
			<li><a href="/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      		<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>	
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li><a href="/myEdit"><span class="glyphicon glyphicon-user"></span> ${sessionScope.info.name}</a></li>
			<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</sec:authorize>
	      </ul>
	    </div>
	  </div>
	</nav>
	  
	<div class="container text-center">    
	  <div class="row">
	  
		<div class="col-sm-3 well">
		  <div class="well">
	        <img id="myImage" src="${targetUser.img}" class="img-circle cursor-pointer" height="65" width="65" alt="Avatar">
	      </div>
	    </div>
	    
	    <div class="col-sm-9" id="form_body"></div>
	    
	  </div>
	</div>
	<br>
	<footer class="container-fluid text-center">
	  <p>Copyright ⓒ GooDee Academy. All rights reserved.</p>
	</footer>
</body>
</html>