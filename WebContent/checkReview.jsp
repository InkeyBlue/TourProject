<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CSS Website Layout</title>
 <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
 <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
 <link rel="stylesheet" href="css/checkReviewStyle.css">
 <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
 <link rel="stylesheet" href="css/rc.css">
 
  
   <!-- Demo CSS -->
	<link rel="stylesheet" href="css/checkreview.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />

	<!-- Modernizr -->
  <script src="js/modernizr.js"></script>


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
<script src="js/rc.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
    box-sizing: border-box;
}

body {
background-image: linear-gradient(to top, #d299c2 0%, #fef9d7 100%);
}
.title{
text-align : center;

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
.topnav2{
    overflow: hidden;
    
    text-align:center;

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
.maincontent{

    width: 70%;
    float: left;
    border-left: 1px solid #d9d9d9;
    border-right: 1px solid #d9d9d9;
    margin-left:15%;
    margin-right:15%;
    box-sizing: border-box;


}
.reviewinfo{
text-align:right;
}
.img{
width:350px;

height:300px;


}
/* likes,scrap button */


/* Style the footer */
.footer {
    
    padding: 10px;
    text-align: center;
}

</style>


</head>
<body>
<c:choose>
<c:when test="${sessionScope.vo!=null}">
<script type="text/javascript">
$(function() {
	 	 $('.heart').click(function() {
			
			if ($(this).hasClass('is-checked')) {
				$.ajax({
					type : "get",
					url : "like.do",
					data : {
						"reviewNum" : "${rvo.reviewNum}",
						"id" : "${rvo.id}",
						"flag" : "down"
					},

					success : function(data) {
						alert("좋아요 취소");
						$('.heart').toggleClass('is-checked');
						$('#like').html("좋아요::${rvo.like}");
					}
				});
			} else {
				$.ajax({
					type : "get",
					url : "like.do",
					data : {
						"reviewNum" : "${rvo.reviewNum}",
						"id" : "${rvo.id}",
						"flag" : "up"
					},

					success : function(data) {
						alert("좋아요 완료");
						$('.heart').toggleClass('is-checked');
						$('#like').html("좋아요::${rvo.like+1}");
					}
				});
			}
		});  
		
		$('.star').click(function() {
			
			if ($(this).hasClass('is-checked')) {
				$.ajax({
					type : "get",
					url : "scrapCheck.do",
					data : {
						"reviewNum" : "${rvo.reviewNum}",
						"id" : "${rvo.id}",
						"flag" : "down"
					},

					success : function(data) {
						alert("스크랩 취소");
						$('.star').toggleClass('is-checked');
					}
				});
			} else {
				$.ajax({
					type : "get",
					url : "scrapCheck.do",
					data : {
						"reviewNum" : "${rvo.reviewNum}",
						"id" : "${rvo.id}",
						"flag" : "up"
					},

					success : function(data) {
						alert("스크랩 완료");
						$('.star').toggleClass('is-checked');
					}
				});
			}
		});

$(window).load(function() {
  $('.flexslider').flexslider({
    animation: "slide",
    controlNav: "thumbnails"
  });
});
})
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
	$(function() {
		$('.heart').click(function() {
	
		
		//중앙위치 구해오기
		 LeftPosition=(screen.width)/2;
		 TopPosition=(screen.height)/2;
		 
		 O="width=350"+",height=200"+",scrollbars=no"+",top="+TopPosition+",left="+LeftPosition; 
		 Win=window.open("","",O); 
		 Win.document.write("<html><head><title>Login</title></head>");
		 Win.document.write("<body topmargin=0 leftmargin=0>");
		 Win.document.write("<h1>===경고===</h1></body></html>");
		 Win.document.write("<h2>로그인을 해주시기 바랍니다</h2></body></html>");
		 Win.document.close();
			});
		$('.star').click(function() {
		
			
			//중앙위치 구해오기
			 LeftPosition=(screen.width)/2;
			 TopPosition=(screen.height)/2;
			 O="width=350"+",height=200"+",scrollbars=no"+",top="+TopPosition+",left="+LeftPosition;
			 Win=window.open("","",O); 
			 Win.document.write("<html><head><title>Login</title></head>");
			 Win.document.write("<body topmargin=0 leftmargin=0>");
			 Win.document.write("<h1>===경고===</h1></body></html>");
			 Win.document.write("<h2>로그인을 해주시기 바랍니다</h2></body></html>");
			 Win.document.close();
				});
		
		})
		
</script>
</c:otherwise>		
</c:choose>
<div class="header">
  <h1>Header</h1>
</div>
<div class="maincontent">
<div class="topnav2">

<h3 class="title">${rvo.title} <br>글번호 :: ${rvo.reviewNum}</h3>
</div>
<hr>
<div class="reviewinfo">
 작성자::${rvo.id}  작성일시::${rvo.date} <span id="like">좋아요::${rvo.like}</span>
<hr>
</div>
  <div class="main">
 ${rvo.content}
 <br>
  <!--   곱창전골은 전골류의 한국 요리로, 소나 돼지의 내장과 여러가지 채소를 육수와 함께 끓여낸 음식이다. <br>
    곱창이란 소나 돼지의 작은 창자를 의미한다. 곱창전골은 곱창이 주재료이지만, 다른 부위의 내장도 많이 사용되어 내장<br>
     특유의 쫄깃한 식감으로 곱창전골의 맛을 더욱 풍부하게 한다. -->
   
     <section class="slider">
       <div id="slider" class="flexslider">
         <ul class="slides">	  	
          	<c:forEach items="${rvo.images}" var="vo">
	  		<li data-thumb="${vo}">
	  		<img src="${vo}" width="295px" height="295px" title="클릭하시면 원본크기로 보실 수 있습니다."
     		style="cursor: pointer;" onclick="expImgPop('${vo}')"/>
     		</li>
			</c:forEach>
		</ul>
	  </div>
	 <div id="carousel" class="flexslider">
	  	<ul class="slides">
	  		<c:forEach items="${rvo.images}" var="vo">
	  		<li data-thumb="${vo}">
	  		<img src="${vo}" width="200px" height="200px"/>
     		</li>
			</c:forEach>
		</ul>
	  </div>
     </section>

 

<ul class="choice-list">
  <li class="checkbox check"></li>
  <c:choose>
  	<c:when test="${likeFlag==true}">
  		  <li class="checkbox heart is-checked"></li>
  	</c:when>
  	<c:otherwise>
  		<li class="checkbox heart"></li>
  	</c:otherwise>
  </c:choose>
  
  <c:choose>
  	<c:when test="${scrapFlag==true}">
  		  <li class="checkbox star is-checked"></li>
  	</c:when>
  	<c:otherwise>
  		<li class="checkbox star"></li>
  	</c:otherwise>
  </c:choose>
 </ul>  
  
  </div>
  

 <hr>
 <div class="row">
 <h3>댓글</h3><hr>
 <c:forEach items="${rvo.comments}" var="cvo">
작성자:: ${cvo.id}    |   ${cvo.comment}<hr><br>
</c:forEach>
 </div>
<div class="footer">
  <p>관련글(카테고리)</p>
 </div>
</div>

 <!-- jQuery -->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\x3C/script>')</script>


 <!-- FlexSlider -->
  <script defer src="js/jquery.flexslider.js"></script>

  <script type="text/javascript">
    $(function(){
      SyntaxHighlighter.all();
    });
    $(window).load(function(){
    	$('#carousel').flexslider({
            animation: "slide",
            controlNav: false,
            animationLoop: false,
            slideshow: false,
            itemWidth: 250,
            itemMargin: 5,
            asNavFor: '#slider'
          });

      
      $('#slider').flexslider({
          animation: "slide",
          controlNav: false,
          animationLoop: false,
          slideshow: false,
          sync: "#carousel",
          start: function(slider){
            $('body').removeClass('loading');
          }
        });
    });
  </script>

 
  <!-- Optional FlexSlider Additions -->
  <script defer src="js/demo.js"></script>
  <script defer src="js/jquery.flexslider.js"></script>


</body>
</html>