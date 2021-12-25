/**
 *
 */

function addrfunc(){//우편번호를 클릭했을때

		var zipcode = document.getElementById("zipcode");
    	var addr1 = document.getElementById("addr1");

		new daum.Postcode({
	        oncomplete: function(data) {

	        	if(data.userSelectedType ==="R"){//사용자가 선택한 주소가 도로명주소일때
	        		addr1.value = data.roadAddress;

	        	}else{//사용자가가 선택한 주소가 지번주소일때(J)
	        		addr1.value = data.jibunAddress;
	        	}
	        	zipcode.value = data.zonecode;
	        }

	    }).open();
		document.getElementById("addr2").focus();

	}//addrfunc 함수부분

	function updateCheck(){

		if(inputform.pwd.value == ""){
			alert("비밀번호가 입력되지 않았습니다");
			return inputform.pwd.focus();
		}
		if(inputform.pwdchk.value == ""){
			alert("비밀번호확인이 입력되지 않았습니다");
			return inputform.pwdchk.focus();
		}

		if( inputform.pwd.value != inputform.pwdchk.value){
			alert("비밀번호를 일치하게 입력하세요");
			return inputform.pwdchk.focus();
		}

		if(inputform.phone1.value == "" ){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone1.focus();
		}
		if(inputform.phone2.value == ""){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone2.focus();
		}
		if(inputform.phone3.value == ""){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone3.focus();
		}
		if(inputform.email.value == ""){
        			alert("이메일이 입력되지 않았습니다");
        			return inputform.email.focus();
        }
		if(inputform.zipcode.value == ""){
			alert("우편번호가 입력되지 않았습니다");
			return inputform.zipcode.focus();
		}
		if(inputform.addr1.value == ""){
			alert("주소가 입력되지 않았습니다");
			return inputform.addr1.focus();
		}
		if(inputform.addr2.value == ""){
			alert("주소가 입력되지 않았습니다");
			return inputform.addr2.focus();
		}
		inputform.submit();
	}//updateCheck 함수부분

/** 여기까지 MemberUpdate.jsp 파일 부분 */
	function useridchk(){//아이디 중복확인 눌렀을때
		var a = inputform.useridunchk.value;
		a = "idchk";
		var userid = inputform.userid.value;

		if(userid === ""){
			alert("아이디 입력하세요");
			return inputform.userid.focus();
		}
		$.ajax({
			url : '/joinidcheck.do',
			type : 'post',
			data : {'userid' : userid},
			error:function(request,status,error){
				alert('에러code : ' + request.status+"에러메시지error : " + error);
			},
			success : function(result){
				var b = result;
				if(b == 1 ){
					alert(userid + "아이디는 사용중인아이디입니다");
					userid = "";
				}else{
					alert(userid + "는 사용가능한 아이디입니다");
					inputform.useridunchk.value="idchk";

				}
			}
		});
	}//useridchk 함수부분

	function keyup(){//아이디 입력란에 입력을 했을때(아이디중복확인을 했는데 아이디입력란에 수정을 하면 다시 아이디중복을 하도록하는함수)
		inputform.useridunchk.value="";
	}//keyup 함수부분

	function joinCheck(){

		if(inputform.userid.value == ""){
			alert("아이디가 입력되지 않았습니다");
			return inputform.userid.focus();
		}

		if(inputform.useridunchk.value != "idchk"){//
			alert("아이디중복확인안했습니다");
			return false;
		}
		if(inputform.pwd.value == ""){
			alert("비밀번호가 입력되지 않았습니다");
			return inputform.pwd.focus();
		}
		if(inputform.pwdchk.value == ""){
			alert("비밀번호확인이 입력되지 않았습니다");
			return inputform.pwdchk.focus();
		}
		if( inputform.pwd.value != inputform.pwdchk.value){
			alert("비밀번호를 일치하게 입력하세요");
			return inputform.pwdchk.focus();
		}
		if(inputform.username.value == ""){
			alert("이름이 입력되지 않았습니다");
			return inputform.username.focus();
		}
		if(inputform.phone1.value == "" ){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone1.focus();
		}
		if(inputform.phone2.value == ""){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone2.focus();
		}
		if(inputform.phone3.value == ""){
			alert("전화번호가 입력되지 않았습니다");
			return inputform.phone3.focus();
		}
		if(inputform.email.value == ""){
                alert("이메일이 입력되지 않았습니다");
                return inputform.email.focus();
        }
		if(inputform.zipcode.value == ""){
			alert("우편번호가 입력되지 않았습니다");
			return inputform.zipcode.focus();
		}
		if(inputform.addr1.value == ""){
			alert("주소가 입력되지 않았습니다");
			return inputform.addr1.focus();
		}
		if(inputform.addr2.value == ""){
			alert("주소가 입력되지 않았습니다");
			return inputform.addr2.focus();
		}
		inputform.submit();
	}//joinCheck 함수부분
/** 여기까지 memberJoin.jsp 파일 부분 */

function agreecheck(){
		if(! document.agreeform.agreecheck.checked){
			alert("이용약관을 읽어보시고 동의하셔야 됩니다");
			return;
		}
		agreeform.submit();
	}
/** 여기까지 agreeJoin.jsp 파일 부분 */

function loginCheck(){
		if( loginform.userid.value==""){
			alert("아이디를 입력하세요");
			return loginform.userid.focus();
		}
		if(loginform.pwd.value==""){
			alert("비밀번호를 입력하세요");
			return loginform.pwd.focus();
		}
		loginform.submit();
	}
/** 여기까지 Login.jsp 부분 */

function memberIdFind(){
	if(idfindform.username.value==""){
		alert("이름을 입력하세요");
		return idfindform.username.focus();
	}
	if(idfindform.phonef.value==""){
		alert("휴대폰번호를 입력하세요");
		return idfindform.phonef.focus();
	}
	if(idfindform.phones.value==""){
		alert("휴대폰번호를 입력하세요");
		return idfindform.phones.focus();
	}
	if(idfindform.phonet.value==""){
		alert("휴대폰번호를 입력하세요");
		return idfindform.phonet.focus();
	}
	idfindform.submit();
}//memberIdFind 메소드 부분

function memberPwdFind(){
	if(pwdfindform.userid.value==""){
		alert("아이디를 입력하세요");
		return pwdfindform.userid.focus();
	}
	if(pwdfindform.phonef.value==""){
		alert("휴대폰번호를 입력하세요");
		return pwdfindform.phonef.focus();
	}
	if(pwdfindform.phones.value==""){
		alert("휴대폰번호를 입력하세요");
		return pwdfindform.phones.focus();
	}
	if(pwdfindform.phonet.value==""){
		alert("휴대폰번호를 입력하세요");
		return pwdfindform.phonet.focus();
	}
	pwdfindform.submit();

}//memberPwdFind 메소드 부분

/**여기까지 MemberFind.jsp 부분 */

function memberDelete(){

	if(confirm("정말로 회원 탈퇴 하시겠습니까?")){
		location.href='/member/memberDelete.do';
	}else{
		history.go(-1);
	}
}//memberDelete 메소드 부분


/**여기까지 memberDelete.jsp부분 */