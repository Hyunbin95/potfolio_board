<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/header.jsp"%>
<section class="content">
	<div class="box">
		<c:if test="${user == null }">
			<div class="main-header" align="center">
				<button type="button" class="btn btn-primary btn-lg" id="login">Login</button>
			</div>
			<div class="box-header with-border">
				<a href="/user/register"><h3 class="box-title">회원가입</h3></a>
			</div>
		</c:if>
		<c:if test="${user != null }">

			<h3 align="center">${user.nickname}님환영합니다.</h3>
			<br />
			<div class="box-header with-border" id="addressdisp"></div>
			<br />
			<div class="main-header" align="center">
				<button type="button" class="btn btn-primary btn-lg" id="logout">Logout</button>
			</div>

		</c:if>
	</div>
</section>

<script>
	document.getElementById("login").addEventListener("click",
			function(e){
		location.href="/user/login"
	})


	document.getElementById("logout").addEventListener("click",
			function(e){
		location.href="logout"
	})

				//8초를 주기로 동작을 수행하는 타이머 생성
			setInterval(function(){
				//현재 위치 정보를 가져오기 위한 HTML5 API
				navigator.geolocation.getCurrentPosition(
						function(position){
					//위치 정보 가져오기
					var coords = position.coords;
					//위도와 경도를 하나의 문자열로 생성
					var param = coords.latitude + ":" + coords.longitude;
					//jquery에 ajax 요청은
					$.ajax({
						url:"/address",
						data:{"param":param},
						dataType:"json",
						success:function(data){
							document.getElementById("addressdisp").innerHTML =
							"현재 위치:" + data.address
						}
					})
				})
			}, 8000);

</script>

<%@include file="include/footer.jsp"%>

