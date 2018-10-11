<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Website Layout</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	
	
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="css/checkReviewStyle.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="css/rc.css">
<link rel="stylesheet" href="css/nav.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<style>
* {
	box-sizing: border-box;
}

body {
	background-image: linear-gradient(to top, #d299c2 0%, #fef9d7 100%);
}

.title {
	text-align: right;
}
/* Style the header */
.header {
	padding: 20px;
	text-align: center;
}

/* Style the top navigation bar */
.topnav {
	overflow: hidden;
}

.topnav2 {
	overflow: hidden;
	text-align: center;
}

/* Style the topnav links */
.topnav a {
	float: left;
	display: block;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

/* Change color on hover */
.topnav a:hover {
	background-color: #ddd;
	color: black;
}

/* Create three unequal columns that floats next to each other */
.column {
	float: left;
	padding: 10px;
}

/* Left and right column */
.column.side {
	width: 15%;
}

/* Middle column */
.column.middle {
	width: 50%;
}

/* Clear floats after the columns */
.row:before {
	content: "";
	display: table;
	clear: both;
	padding-top: 30px;
}

.row:after {
	content: "";
	display: table;
	clear: both;
	padding-top: 30px;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
	.column.side, .column.middle {
		width: 100%;
	}
}

.main {
	width: 100%;
	float: left;
	box-sizing: border-box;
}

.maincontent {
	width: 70%;
	float: left;
	border-left: 1px solid #d9d9d9;
	border-right: 1px solid #d9d9d9;
	margin-left: 15%;
	margin-right: 15%;
	box-sizing: border-box;
}

.img {
	width: 350px;
	height: 300px;
}
/* likes,scrap button */

/* Style the footer */
.footer {
	padding: 10px;
	text-align: center;
}
</style>

<script type="text/javascript" src="js/nav.js"></script>
</head>
<body>
	<nav class="navbar navbar-defalt navbar-fixed-top" style="background-color: #fff">
      	<div id="header">
      		<div class="container">
         		<div class="navbar-header" style="margin-top: 15px">
            		<button type="button" class="navbar-toggle" id="menuSpan" data-toggle="collapse" data-target="#myNavbar">
	                    <span class="icon-bar"></span>
	               		<span class="icon-bar" style="margin-top: 2px"></span>
	               		<span class="icon-bar"></span>
            		</button>
            		<a href="index.jsp"><img src="img/main_logo2.png" width="150"></a>
         		</div> <!-- navbar-header -->
         		
	         	<div class="collapse navbar-collapse navbar-right" id="myNavbar" style="margin-top: 15px">
	            	<form class="navbar-form navbar-left" action="getdata.do">
	               		<div class="input-group">
	                  		<input type="text" class="form-control" placeholder="Search" name="search" id="myInput">
	                  		<div class="input-group-btn">
	                     		<button class="btn btn-default" type="submit">
	                        		<i class="glyphicon glyphicon-search"></i>
	                     		</button>
	                  		</div>
	               		</div>
	            	</form>
	            	
	            	<ul class="nav navbar-nav navbar-right">
	               		<li class="dropdown">
		                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="userMenu">
		                     	<span class="glyphicon glyphicon-user white">
		                     		<span class="caret" style="margin-left: 10px"></span>
		                     	</span>
		                  	</a>
		                  	<c:choose>
		                  	 	<c:when test="${vo != null}">
			                  	 	<ul class="dropdown-menu">
			                     	<li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
			                     	<li><a href="myreviews.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
			                     	<li><a href="scrap.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
			                     	<li><a href="write.jsp"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글쓰기</a></li>
			                     	<li><a href="registerupdate.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
			                  		</ul>
		                  		</c:when>
		      
		                  		<c:otherwise>
		                  			<ul class="dropdown-menu">
			                     	<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
			                     	<li><a href="register.jsp"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;회원가입</a></li>
			                  		</ul>
		                  		</c:otherwise>
		                  	</c:choose>
	               		</li>
					</ul>
				</div> <!-- myNavbar -->
      		</div> <!-- container -->
		</div> <!-- header -->
    	<div id="line"></div>
	</nav>
	
	<div style="height: 150px"></div>
	
	<div class="maincontent">
		<div class="topnav2">
			<h1>${rvo.title}</h1>
			<h3 class="title">글번호 :: ${rvo.reviewNum}</h3>
		</div>
		<hr>
		작성자::<a href="memberreview.do?id=${rvo.id}">${rvo.id}</a> &nbsp;&nbsp; 작성일시::${rvo.date} &nbsp;&nbsp; 좋아요::${rvo.like}
		<hr>
		<div class="row">
			<div class="main">
				${rvo.content} <br>
				<!--   곱창전골은 전골류의 한국 요리로, 소나 돼지의 내장과 여러가지 채소를 육수와 함께 끓여낸 음식이다. <br>
    곱창이란 소나 돼지의 작은 창자를 의미한다. 곱창전골은 곱창이 주재료이지만, 다른 부위의 내장도 많이 사용되어 내장<br>
     특유의 쫄깃한 식감으로 곱창전골의 맛을 더욱 풍부하게 한다. -->
				<div class="carousel">
					<figure>
						<c:forEach items="${rvo.images}" var="vo">
							<img src="${vo}" class="img">
						</c:forEach>
					</figure>
					<nav>
						<button class="nav prev">Prev</button>
						<button class="nav next">Next</button>
					</nav>
				</div>
				<ul class="choice-list">
					<li class="checkbox check"></li>
					<li class="checkbox heart is-checked"></li>
					<li class="checkbox star"></li>
				</ul>
			</div>
		</div>

		<hr>
		<div class="row">
			<h3>댓글</h3>
			<hr>
			<c:forEach items="${rvo.comments}" var="cvo">
작성자:: ${cvo.id}    |  ${cvo.comment}<hr>
				<br>
			</c:forEach>

		</div>


		<div class="footer">
			<p>관련글(카테고리)</p>

		</div>
	</div>



	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="js/rc.js"></script>
</body>
</html>