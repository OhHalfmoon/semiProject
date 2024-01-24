
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <jsp:include page="/WEB-INF/views/includes/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
	<section class="section-margin">
		<div class="container">
			<div class="section-intro pb-60px">
				<h3 class="text-center">주소를 설정하고 동네 주민들과 <span style="color: #4cd10f;">싱싱한</span> 거래를 해보세요</h3>
			</div>
		</div>
		
		<div class="card-body index inline-block;text-align: center;"">
			<div class="d-flex flex-column align-items-center">
				<h4>내 주소록</h4>
				<ul class="list-group chat"></ul>
			</div>
			<br>
			<div class="d-flex flex-column align-items-center">
				<a href="javascript:history.back();"><button class="button button--active p-2 ml-auto" style="line-height: 1;">뒤로가기</button></a>
			</div>
		</div>
	   
	   
  </section>
  <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/address.js"></script>
 <script>
 var cp = '${pageContext.request.contextPath}'
 $(function() {
	 var memberNo = '${loginMember.memberNo}';
	 addressService.getList({memberNo:memberNo}, function(result) {
		 for(var i in result) {
			 console.log(result[i].addrNo)
			 
		 }
		 var str = "";
		 for (var i in result) {
			 console.log(result.length)			 
			str += getAddrListr(result[i]);		

		 }
		 if (result.length < 3) {
			 str += newStr(result);				 
		 }
		 console.log(str)
		 $(".chat").html(str);
	 })
	 
  	function getAddrListr(obj) {
		console.log(obj);
		console.log(obj.mainAddr);
		if (obj.mainAddr) {
	 		str = `<li class="list-group-item" data-addrNo="\${obj.addrNo}" data-main="\${obj.mainAddr}">

				<div class="state d-flex flex-wrap">				
					<div class="col-md-8 form-group">
						<input type="text" class="form-control text-truncate" id="addr" name="addr" placeholder="\${obj.addr}" readonly>
					</div>	
	                <div class="col-md-4 form-group">
	                	<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="addressSearch">주소검색</button>	
	           		</div>
	            	<div class="col-md-8 form-group">
            			<input type="text" class="form-control" id="addrDetail" name="addrDetail" placeholder="\${obj.addrDetail}">
        			</div>	 
		        	 <input type="hidden" name="x">
		             <input type="hidden" name="y">
		             <input type="hidden" name="dong">											    		    			    	
				</div>
				<div class="d-flex">
					<div class="col-md-4 form-group">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="submit">주소수정</button>	                		
            		</div>

					<div class="col-md-4 mainHome">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="change" disabled>메인주소</button>	
					</div>
				</div>
			</li>
		`;
 		return str;							
		}
		else {
	 		str = `<li class="list-group-item" data-addrNo="\${obj.addrNo}" data-main="\${obj.mainAddr}">

				<div class="state d-flex flex-wrap">				
					<div class="col-md-8 form-group">
						<input type="text" class="form-control text-truncate" id="addr" name="addr" placeholder="\${obj.addr}" readonly>
					</div>	
	                <div class="col-md-4 form-group">
	                	<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="addressSearch">주소검색</button>	
	           		</div>
	            	<div class="col-md-8 form-group">
            			<input type="text" class="form-control" id="addrDetail" name="addrDetail" placeholder="\${obj.addrDetail}">
        			</div>	 
		        	 <input type="hidden" name="x">
		             <input type="hidden" name="y">
		             <input type="hidden" name="dong">											    		    			    	
				</div>
				<div class="d-flex">
					<div class="col-md-4 form-group">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="submit">주소수정</button>	                		
            		</div>

					<div class="col-md-4 mainHome">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="change">메인주소 설정</button>	
					</div>
					<div class="col-md-4 mainDelete">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="delete">주소 삭제</button>	
					</div>
				</div>
			</li>
		`;	
	 	return str;
		} 
	}
	 
  	function newStr(obj) {
		console.log(obj);
		console.log(obj.mainAddr);
	 		 
	 		str = `<li class="list-group-item" >

					<div class="state state d-flex flex-wrap">				
						<div class="col-md-8 form-group">
							<input type="text" class="form-control text-truncate" id="addr" name="addr" placeholder="주소를 검색해주세요" readonly>
						</div>	
		                <div class="col-md-4 form-group">
		                	<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="addressSearch">주소검색</button>
		           		</div>
		            	<div class="col-md-8 form-group">
                			<input type="text" class="form-control" id="addrDetail" name="addrDetail" placeholder="세부주소를 입력해주세요">
            			</div>	 
			        	 <input type="hidden" name="x">
			             <input type="hidden" name="y">
			             <input type="hidden" name="dong">											    		    			    	
					</div>
					<div class="col-md-4 form-group">
						<button class="button button--active p-2 ml-auto" style="line-height: 1;" id="newAddress">신규주소 등록</button>
            		</div>
				</li>
				<br>
			`;
	 		return str;
 		  
	}	 
	 	 
	 $(".chat").on("click","#change", function() {				 
		 var str = "";
		 var obj = {memberNo:memberNo, addrNo:$(this).parent().parent().parent().attr("data-addrNo") }
		 var main = $(this).parent().parent().parent().attr("data-main")
		 console.log($(this).parent().parent().parent().attr("data-addrNo"));
		 console.log($(this).parent().parent().parent().attr("data-main"));
 		 if (main == 'true') {
 			 alert("이미 메인주소로 설정되어있습니다.");			 
 		 }
 		 else {
  	 		 addressService.modifyMain(obj, function(result) {
			 addressService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {
 					str += getAddrListr(result[i]);		
				 }	
				 if (result.length < 3) {
					 str += newStr(result);				 
				 }
 				$(".chat").html(str); 				 
 			 })
 		})
 		console.log(this);
			$(".chat").html(str);  
			
			
	 		alert("수정완료"); 
 		 } 
	 })
	 
	 $(".chat").on("click","#submit", function() {				 
		 var str = "";
		 var obj = {memberNo:memberNo, 
				 	addrNo:$(this).parent().parent().parent().attr("data-addrNo"),
				 	addr:$(this).parent().parent().parent().children().children().children().eq(0).attr("value"),
				 	addrDetail:$(this).parent().parent().parent().children().children().eq(2).children().eq(0).val(),
				 	y:$(this).parent().parent().parent().children().children().eq(4).attr("value"),
				 	x:$(this).parent().parent().parent().children().children().eq(3).attr("value"),
				 	dong:$(this).parent().parent().parent().children().children().eq(5).attr("value")
				 	}
		 console.log(obj);
		 addressService.modify(obj, function(result) {
			 addressService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {
 					str += getAddrListr(result[i]);		
				 }	
				 if (result.length < 3) {
					 str += newStr(result);				 
				 }
 				$(".chat").html(str); 				 
 			 })
 		})
 		console.log(this);
			$(".chat").html(str);  
			
			
	 		alert("수정완료");		 
	 })

	 $(".index").on("click","#newAddress", function() {				 
		 var str = "";
		 var obj = {memberNo:memberNo, 
				 	addr:$(this).parent().parent().children().children().children().eq(0).attr("value"),
				 	addrDetail:$(this).parent().parent().children().children().eq(2).children().eq(0).val(),
				 	y:$(this).parent().parent().children().children().eq(4).attr("value"),
				 	x:$(this).parent().parent().children().children().eq(3).attr("value"),
				 	dong:$(this).parent().parent().children().children().eq(5).attr("value"),
				 	mainAddr:0
				 	}
		 console.log(obj);
		 addressService.add(obj, function(result) {
			 addressService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {		 
 					str += getAddrListr(result[i]);		
				 }	
				 if (result.length < 3) {
					 str += newStr(result);				 
				 }
 				$(".chat").html(str); 				 
 			 })
 		})
 		console.log(this);
			$(".chat").html(str);  
			
			
	 		alert("등록완료");	
	 })	
	 
	 $(".chat").on("click","#delete", function() {
		 var main = $(this).parent().parent().parent().attr("data-main")
		 var addrNo = $(this).parent().parent().parent().attr("data-addrNo");
		 console.log(addrNo);
 		 if (main == 'true') {
 			 alert("메인주소는 삭제할 수 없습니다.");			 
 		 }
 		 else {
 			 
 		 
		 var str = "";
		 addressService.remove(addrNo, function(result) {
			 addressService.getList({memberNo:memberNo}, function(result) {
 				 for(var i in result) {		 
 					str += getAddrListr(result[i]);		
				 }	
				 if (result.length < 3) {
					 str += newStr(result);				 
				 }
 				$(".chat").html(str); 				 
 			 })
 		})
 		console.log(this);
			$(".chat").html(str);  
			
			
	 		alert("삭제완료");	 
 		 }
	 })	 	 
	 
	 
	 
	 
	 
	 $(".chat").on("click","#addressSearch", function(){ //주소입력칸을 클릭하면
		    //카카오 지도 발생

		    var search = $(this).parent().parent().children().children().eq(0);
		    var detail = $(this).parent().parent().children().eq(2).children().eq(0);
		    var searchX = $(this).parent().parent().children().eq(3);
		    var searchY = $(this).parent().parent().children().eq(4);
		    var searchDong = $(this).parent().parent().children().eq(5);


		    new daum.Postcode({
		        oncomplete: function(data) { //선택시 입력값 세팅
					search.attr("value", data.address); // 주소 넣기
		            detail.focus(); //상세입력 포커싱
		            
		            console.log(search)
		            console.log(data);

		            $.ajax({
		                url : 'https://dapi.kakao.com/v2/local/search/address.json?query=' + data.address,
		                headers: { 'Authorization': 'KakaoAK PLEASE ENTER YOUR KAKAO API KEY HERE'},
		                type: 'GET',
		                success : function(data) {
		                console.log(data);
		                console.log(data.documents[0].road_address.x);
		                console.log(data.documents[0].road_address.y);
		                console.log(data.documents[0].road_address.region_3depth_name);
						
		                searchX.attr('value',data.documents[0].road_address.x);
		                searchY.attr('value',data.documents[0].road_address.y);
		                searchDong.attr('value',data.documents[0].road_address.region_3depth_name);		                
		            	}
		                
		            })
		            
		        }
		    }).open();
		});
	  
	 
})

 
 
 
 
 </script>
 
</body>
</html>