<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="member.register" /></title>
</head>
<body>
	<h2>
		<spring:message code="member.info" />
	</h2>
	<form:form action="step3" modelAttribute="registerCommand">
		<p>
			<label><spring:message code="id" />:<br> 
			<form:input class="id_input" path = "id" />
			<button type = "button" class = "id_check">중복확인</button><br>
			<form:errors path="id" />
			</label>										
			<span class = "msg1" style = "display : none; color : green;">사용 가능합니다.</span>
			<span class = "msg2" style = "display : none; color : red">중복된 아이디 입니다. 다시 입력해 주세요.</span>
		</p>
		<p>
			<label><spring:message code="password" />:<br> <form:password
					path="pw" /> <form:errors path="pw" /> </label>
		</p>
		<p>
			<label><spring:message code="password.confirm" />:<br>
				<form:password path="confirmPw" /> <form:errors path="confirmPw" />
			</label>
		</p>
		<p>
			<label><spring:message code="name" /> :<br> <form:input
					path="name" /> <form:errors path="name" /> </label>
		</p>
		<p>
			<label><spring:message code="tel" /> : <br> </label>
			<form:input path="tel" />
			<form:errors path="tel" />
		</p>
		<p>
			<label><spring:message code = "birth" /> <br>
				<form:select path = "year" name="year" id="year" title="년도" />
				<form:select path = "month" name="month" id="month" title="월"/>
				<form:select path = "day" name="day" id="day" title="일" />
			</label>
		</p>
		<p>
			<input type="text" id="sample4_postcode" placeholder="우편번호">
			<input type="button" onclick="sample4_execDaumPostcode()"
				value="우편번호 찾기"><br>
			<form:input path="addr_road" id="sample4_roadAddress"
				placeholder="도로명주소" />
			<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
			<span id="guide" style="color: #999; display: none"></span>
			<form:input path="addr_detail" id="sample4_detailAddress"
				placeholder="상세주소" />
			<input type="text" id="sample4_extraAddress" placeholder="참고항목">
		</p>
		<p>
			<label><spring:message code="email" /> : <br> </label>
			<form:input path="email" />
			<form:errors path="email" />
		</p>
		<input type="submit" value="<spring:message code="register.btn" />">
	</form:form>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	
	<!-- 생년월일 -->
	<script>
	  $(document).ready(function () {
		    setDateBox();
		});
		function setDateBox(){
			var date = new Date();
			var year = "";
			var com_year = date.getFullYear();
			
			$("#year").append("<option value = ''>년도</option>");
			
			for(var y = com_year ; y >= (com_year - 80); y--){
				$("#year").append("<option value = '" + y +"'>" + y + " 년" + "</option>");
			}
			
			var month = "";
			$("#month").append("<option value = ''>월</option>");
			for(var y = 1 ; y <= 12 ; y++){
				$("#month").append("<option value = '" + y +"'>" + y + " 월" + "</option>");
			}
			
			var day = "";
			$("#day").append("<option value = ''>일</option>");
			for(var y = 1; y <= 31; y++){
				$("#day").append("<option value = '" + y + "'>" + y + " 일" + "</option>");
			}
		}
	</script>
	
	<!-- 아이디 중복확인 절차 -->
	<script>
		$(".id_check").click(function(){
    		
    			console.log('${pageContext.request.contextPath}');
    			var memberId = $('.id_input').val();			// .id_input에 입력되는 값
    			if(memberId === null){
    				$('.msg1').css("display","none");
					$('.msg2').css("display", "none");		
    			}
    			var data = {memberId : memberId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
    			$.ajax({
    				type : "post",
    				url : "id_check",
    				data : data,
    				success : function(result){
    					console.log("성공 여부 " + result);
    					if(result != 'fail'){
    						$('.msg1').css("display","inline-block");
    						$('.msg2').css("display", "none");				
    					} else {
    						$('.msg2').css("display","inline-block");
    						$('.msg1').css("display", "none");				
    					}
    				}
    			});

    		});

  	</script>
  	
  	<!-- 다음 주소 api -->
	<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    		function sample4_execDaumPostcode() {
       			 new daum.Postcode({
           			 oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               		 var roadAddr = data.roadAddress; // 도로명 주소 변수
               		 var extraRoadAddr = ''; // 참고 항목 변수
					var detailAddr = document.getElementById("sample4_detailAddress").value; //id = "detailAddress"
               		 
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               		 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   		 extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                	if(data.buildingName !== '' && data.apartment === 'Y'){
                   		extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               		if(extraRoadAddr !== ''){
                	    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
    	
</script>
</body>
</html>
