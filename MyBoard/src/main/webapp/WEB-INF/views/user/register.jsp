<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<section class="content">
	<!-- 회원가입 폼 : 유효성 검사를 위해 id를 부여
enctype은 file을 전송할 때 사용
최근에 form의 전송방식은 거의 post
조회할 때 입력받는 부분만 get-->
	<form id="registerform" enctype="multipart/form-data" method="post">
		<p align="center">
		<table border="1" width="50%" height="80%" align='center'>
			<tr>
				<td colspan="3" align="center"><h2>회원 가입</h2></td>
			</tr>
			<tr>
				<!-- rowspan은 행을 합치는 것 -->
				<td rowspan="5" align="center">
					<p></p> <img id="img" width="100" height="100" border="1" /> <br />
					<br /> <!-- accept 속성은 파일 선택을 제한 : 확장자를 가지고 제한 --> <input
					type='file' id="image" name="image" accept=".jpg,.jpeg,.gif,.png" /><br />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp; 이메일</font></td>
				<td align="center">
					<!-- HTML5에서 input의 type을 추가된 형태를 설정하면
형식 검사도 수행해 줍니다.
name은 서버에게 전달할 이름이 id는 스크립트가 사용할 이름
2개를 동일하게 설정하는 것이 코딩을 할 때 수월합니다. --> <input type="email" name="email"
					id="email" size="30" maxlength=50 required="required" />
					<div id="emaildiv"></div>
				</td>

			</tr>
			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp; 비밀번호</font></td>
				<td align="center"><input type="password" name="pw" id="pw"
					size="20" required="required" />
				<div id="pwdiv"></div></td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp; 비밀번호
						확인</font></td>
				<td align="center"><input type="password" id="pwconfirm"
					size="20" required="required" />
				<div id="pwconfirmdiv"></div></td>
			</tr>
			<tr>
				<td width="17%" bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;
						닉네임</font></td>
				<td align="center">
					<!-- pattern은 정규식 패턴을 설정해서 유효성 검사 수행
title은 유효성 검사에 실패했을 때 보여지는 텍스트인데 브라우저에 잘 적용  --> <input type="text"
					name="nickname" id="nickname" size="20"
					pattern="([a-z, A-Z, 가-힣]){2,}" required="required"
					title="닉네임은 문자 2자 이상입니다." />
					<div id="nicknamediv"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<p></p> <!-- 부트스트랩에서는 버 class를 적용하면 색상이 변경됩니다. --> <input
					type="submit" value="회원가입" class="btn btn-warning" /> <!-- 인라인 이벤트 처리 방식으로 클릭 이벤트 처리
간단한 구문에는 유용하지만 태그 안에 스크립트 코드가 있어서 가독성이 떨어지기 때문에
최근에는 비추천 --> <input type="button" value="메인으로" class="btn btn-success"
					onclick="javascripｔ:window.location='/'">
					<p></p>
				</td>
			</tr>
		</table>
	</form>
	<br /> <br />
</section>
<script>
	//change 이벤트가 발생하면 readURL 호출
	//change - 내용이 변경되면 호출되는 이벤트
	document.getElementById("image").addEventListener('change', function(e) {
		//파일 선택 여부 확인
		//프로그래밍 언어 들 중에는 데이터가 없으면 false를 리턴하기도 합니다.
		if (this.files && this.files[0]) {
			//파일의 내용을 읽는 객체를 생성
			var reader = new FileReader();
			//파일을 읽는 것은 비동기적으로 동작
			reader.readAsDataURL(this.files[0]);
			//파일을 읽는 동작이종료되면 처리
			reader.addEventListener("load", function(e) {
				//읽은 내용 img 태그에 출력
				document.getElementById("img").src = e.target.result;
			});
		}
	});
	
	//이메일 중복검사 여부를 통과를했는지를 저장할 변수를 생성
	var emailcheck = false;
	//email 입력란에서 포커스가 떠나면 수행할 메소드
	document.getElementById("email").addEventListener(
			"focusout", function(e){
				var email = document.getElementById("email").value
				if(email.trim().length > 0){
					$.ajax({
						url:'emailcheck',
						data:{
							'email':email
						},
						dataType:'json',
						success:function(data){
							var emaildiv = document.getElementById('emaildiv')
							if(data.result === true){
								emaildiv.innerHTML = "사용 가능한 이메일입니다."
								emaildiv.style.color = 'green'
								emailcheck=true
							}else if(data.result === false){
								emaildiv.innerHTML = "이미 사용중인 이메일입니다."
								emaildiv.style.color = 'red'
								emailcheck = false;
							}
						}
					})
				}
			})
	
			
	//nickname 중복검사 여부를 통과했는지를 확인할 변수를 생성
	var nicknamecheck = false;
	//nickname 입력란에서 포커스가 떠나면 실행할 메소드
	document.getElementById('nickname').addEventListener(
			'focusout', function(e){
				var nickname = document.getElementById('nickname').value
				if(nickname.trim().length > 0){
					$.ajax({
						url:'nicknamecheck',
						data:{
							nickname:'nickname'
						},
						dataType:'json',
						success:function(data){
							var nicknamediv = document.getElementById('nicknamediv')
							if(data.nicknameResult === true){
								nicknamediv.innerHTML = "사용가능한 닉네임 입니다."
								nicknamediv.style.color = "green"
								nicknamecheck = true;
							}else if(data.nicknameResult === false){
								nicknamediv.innerHTML = "이미 사용중인 닉네임입니다."
								nicknamediv.style.color = "red"
								nicknamecheck = false;
							}
						}
					})
				}
			})
			
	var passwordcheck = false
	//password 입력란에서 포커스가 떠나면 실행할 메소드
	document.getElementById('pw').addEventListener(
			'focusout',function(e){
				var pwdiv = document.getElementById('pwdiv')
				var p1 = /[0-9]/
				var p2 = /[a-z]/
				var p3 = /[A-Z]/
				var p4 = /[~!@#$%^&*()_+=-]/
				//pw 위 4개의 패턴이 등장하지 않거나 8자보다 글자수가 적으면 전송 x
				if(!p1.test(pw.value) || !p2.test(pw.value) ||
				   !p3.test(pw.value) || !p4.test(pw.value) ||
				   pw.value.length < 8){
					pwdiv.innerHTML = "비밀번호는 8자이상이고 숫자, 영어 대소문자 그리고 특수문자 1개 이상이 반드시 포함되어야 합니다."
					pwdiv.style.color="red"
					passwordcheck = false;
				}else{
					pwdiv.innerHTML = "사용 가능한 비밀번호입니다."
					pwdiv.style.color="green"
					
						document.getElementById('pwconfirm').addEventListener(
								'focusout',function(e){
									var pw = document.getElementById('pw').value
									var pwconfirm = document.getElementById('pwconfirm').value
									var pwconfirmdiv = document.getElementById('pwconfirmdiv')
									if(pw === pwconfirm){
										pwconfirmdiv.innerHTML = "두 비밀번호가 일치합니다."
										pwconfirmdiv.style.color = "green"
										passwordcheck = true;
									}else if(pw != pwconfirm){
										pwconfirmdiv.innerHTML = "두 비밀번호가 일치하지 않습니다."
										pwconfirmdiv.style.color = 'red'
										passwordcheck = false;
									}
								})
				}
			})

	//registerform 에 submit 이벤트가 발생했을때 처리할 메소드
	document.getElementById('registerform').addEventListener(
			'submit',function(e){
				if(emailcheck == false){
					document.getElementById(emaildiv).innerHTML = 
						'이메일 중복검사를 통과하지 못했습니다.'
					document.getElementById(emaildiv).style.color = 'red'
					document.getElementById("email").focues()
					e.preventDefault()
					return
				}else if(nicknamecheck == false){
					document.getElementById(nicknamediv).innerHTML = 
						'닉네임 중복검사를 통과하지 못했습니다.'
					document.getElementById(nicknamediv).style.color = 'red'
					document.getElementById("nickname").focues()
					e.preventDefault()
					return
				}else if(passwordcheck == false){
					document.getElementById(pwdiv).innerHTML = 
						'비밀번호 검사를 통과하지 못했습니다.'
					document.getElementById(pwdiv).style.color = 'red'
					document.getElementById("pw").focues()
					e.preventDefault()
					return
				}
			})
			
</script>
<%@include file="../include/footer.jsp"%>