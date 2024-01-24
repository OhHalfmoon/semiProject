<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
  <jsp:include page="../includes/head.jsp"/>
  <style>
  	#remove:HOVER{
  		background-color: #ff8c60;
  		border-color: white;
  	}
  	.btn-remove:HOVER {
  		background-color: #ff8c60;
  		border-color: white;
	
}
  </style>
</head>
<body>
  <jsp:include page="../includes/header.jsp"/>
<!-- ================ start banner area ================= -->	
	<section >
		<div class="container" style="margin-top: 70px">
			 <h2 style="font-size: 20px; font-weight: 600"><span style="color: #4cd10f;">상</span>상하던 상품 <span style="color: #4cd10f;">상</span>세</h2>
    </div>
	</section>
	<!-- ================ end banner area ================= -->


  <!--================Single Product Area =================-->

 


<fmt:formatDate var="regdate" value="${board.regdate}" pattern="yyyy.MM.dd"/>

<%-- <fmt:formatDate var="uptime" value="${board.uptime}" pattern="yyyy.MM.dd HH:mm"/> --%>

 
	<div class="product_image_area pt-3">
	
		<div class="container">
			<div class="s_product_inner d-flex">
				<div class="m-0 p-0" style="box-shadow: 3px 3px 20px #ddd;">
				
						<div class="single-prd-item m-0 p-0" style=" width: 540px; height: 580px;">
						
						<c:forEach items="${board.attachs}" varStatus="status" var="attach">
						
						<c:choose> 
							
							<c:when test="${status.index == 0}" >
								
									
							            <div class="card text-center card-product m-0 p-0" style=" width: 540px; height: 580px;">
							              <div class="card-product__img m-0 p-0" style=" width: 540px; height: 580px;">
							              	<c:if test="${attach.attNo == null}">
												<img class="card-img"
													 src="${pageContext.request.contextPath}/resources/img/no-image.png"
													alt="">
											</c:if>
											<c:if test="${attach.attNo != null}">
											 <img class="card-img img-thumbnail  m-0 p-0" src="${pageContext.request.contextPath}/display${attach.url}"  style=" width: 540px; height: 580px; padding:0;margin:0; border:none; " alt="">
											</c:if>
							               
							                <ul class="card-product__imgOverlay">
							                	<c:if test="${attach.attNo == null}">
												<li><button class="button" type="button" style="cursor: text; box-shadow: 0 0 0 1px #4cd10f inset;background-color: #fff;"><span style="color:#ff8c60;">판매자가 올린 사진이 없어요</span>	</button></li>
											</c:if>
											<c:if test="${attach.attNo != null}">
											<li><button class="button" type="button" style="cursor: text; box-shadow: 0 0 0 1px #4cd10f inset;background-color: #fff;"><span style="color:#222;">전체 사진 ${board.attachs.size()}개</span>	</button></li>
							                  <li><button class="img-thumb" data-fancybox="gallery" data-src="${pageContext.request.contextPath}/displayOrigin${attach.url}" type="button">이미지 더보기</button></li>
							                  
											</c:if>
							                </ul>
							              </div>
							             
							           
							          
								</div>
							</c:when> 
							<c:otherwise> 
								<div style="display:none">
									
							            <div class="card text-center card-product m-0 p-0" style=" width: 540px; height: 580px;">
							              <div class="card-product__img" style=" width: 540px; height: 580px;">
							                <img class="card-img img-thumbnail" src="${pageContext.request.contextPath}/display${attach.url}"  style=" width: 540px; height: 580px; padding:0;margin:0; border:none; " alt="">
							                <ul class="card-product__imgOverlay">
							                  <li><button class="img-thumb" data-fancybox="gallery" data-src="${pageContext.request.contextPath}/displayOrigin${attach.url}" type="button">이미지 더보기</button></li>
							                  
							                </ul>
							              </div>
							             
							            </div>
							        	
								</div>
								
							</c:otherwise>
						</c:choose>
						
						</c:forEach>
						
					
						
						
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1">
					<div class="s_product_text"  style="margin-top: 20px">
						<div>
							<h6>상품등록날짜 : ${regdate}</h6>
							<%-- <h6>상품등록날짜 : ${board.uptimeWeek}</h6> --%>
						</div>
						<div class="d-flex" style="margin-top: 50px">
						<c:if test="${board.memberName == null}">
							<h3>탈퇴한 회원</h3>
						</c:if>
						<c:if test="${board.memberName != null}">
							<h3><a href="${pageContext.request.contextPath}/shop/${board.memberNo}" >${board.memberName}</a></h3>
					
						</c:if>
						</div>
						<h6>${board.addrName}</h6>
						
						
						<div style="margin-top: 280px">
							<h6><a class="active" href="${pageContext.request.contextPath}/board/category/${board.cateNo}">${board.cateName}</a></h6>
							<h3 class="mb-1">${board.title}</h3>
														
							
							
							

							<div class="card_area float-right">
							<c:if test="${pg.progress eq '3'}">
								<h2 class="ml-auto mr-5" style="font-size: 30px;">${pg.pgName}</h2>
							</c:if>
							
							<c:if test="${pg.progress ne '3'}">
								<h2 class="ml-auto mr-5" style="font-size: 30px;">${board.price}원</h2>
							</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================End Single Product Area =================-->

	<!--================Product Description Area =================-->
	<section class="product_description_area">
		<div class="container">
				<h2 style="font-size: 20px; font-weight: 600; margin-bottom: 25px"><span style="color: #4cd10f;">상</span>품 정보</h2>
			<div class="tab-content" style="border-top: 1px solid #eee; min-height: 300px" >
					<p>${board.content}</p>
			</div>
				
			<div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
				
			</div>
		</div>
		
		
		<c:if test="${member != null}">
		
		<c:if test="${member != board.memberNo }">
		
			<div class="container mt-3">
				<div class="d-flex" id="sendFormat">
					<button class="button button--active p-2" id="cheat-btn" style="line-height: 1;"><i class="icofont-ban mr-1" style="color: #ff8c60; "></i> 신고하기</button>
	
					<c:if test="${pg.progress ne '3'}">
					<button class="button button--active p-2 ml-auto" id="purchase" style="line-height: 1;"><i style="color: #004211;" class="icofont-verification-check "></i> 구매요청하기</button>
					</c:if>
					
					<c:if test="${pg.progress eq '3'}">
					<button class="button button--active p-2 ml-auto" style="line-height: 1;" ><i style="color: #004211;" class="icofont-verification-check "></i> 판매완료</button>
					</c:if>
				
		
					<button class="button button--active p-2 ml-2" id="sendMessage" style="line-height: 1;"><i style="color: #004211;" class="icofont-ui-message mr-1"></i> 쪽지 보내기</button>
				</div>
			</div>
		
		</c:if>
		</c:if>
	</section>
	<!--================End Product Description Area =================-->

	<!--================ Start related Product area =================-->  
	<section class="related-product-area section-margin--small mt-0">
		<div class="container pb-5">
		
			<h2 style="font-size: 20px; font-weight: 600">싱싱한 <span class="section-intro__style">Review</span></h2>
			<div class="tab-content" style="border-top: 1px solid #eee;" >	
				<div id="review" role="tabpanel" aria-labelledby="review-tab">

                        
                        <div class="card-body">
							<ul class="list-group chat">
							
                    </div>		
				</div>
			</div>
		</div>	
		<c:if test="${member == board.memberNo }">
		<div class="container my-5 py-5">
			<div class="d-felx" style="text-align: center;">
				
				<a href="${pageContext.request.contextPath}/board/modify?bno=${bno}" class="m-4"><button type="button" style="font-size: 18px" class="button button--active px-4 py-2">수정</button></a>
				<button type="button" style="font-size: 18px" id="uptime" class="button button--active px-4 py-2 m-4">끌어올리기</button>
				<button type="button" style="font-size: 18px" id="remove" class="button button--active px-4 py-2 m-4">삭제</button>
				
				
				
			
			</div>
		</div>
		</c:if>
	</section>
	
	<!-- remove Modal -->
<div class="modal fade" id="remove-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" style="margin-top: 300px; min-height: 300px; min-width: 600px " role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <h3 class="modal-title ml-4 my-2" id="exampleModalLongTitle">게시글 삭제</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <c:if test="${pg.progress ne '3'}">
      <div class="modal-body ml-4 my-2">
      	<h4>게시글을 정말 삭제하시겠습니까?</h4>
      	<h6 style="font-size: 15px; color: #ff8c60;">*삭제시 되돌릴 수 없습니다</h6>
        
      </div>
      <div class="modal-footer">
     
      <form method="post">
        <button formaction="${pageContext.request.contextPath}/board/remove" class="button button--active btn-remove px-4 py-2">삭제</button>
        <button type="button" class="button button--active px-4 py-2" data-dismiss="modal">닫기</button>
        <input type="hidden" name="bno" value="${bno}">
     </form>
      </div>
      </c:if>
      <c:if test="${pg.progress eq '3'}">
      <div class="modal-body ml-4 my-2">
      	<h4 style="color: #ff8c60;">판매완료된 게시글은 삭제할 수 없습니다</h4>
      	<h6 style="font-size: 15px; ">수정에서 비공개처리를 한다면 글을 보여주지 않을수 있어요</h6>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="button button--active px-4 py-2" data-dismiss="modal">닫기</button>
      </div>
      </c:if>
    </div>
  </div>
</div>



	<!-- uptime Modal -->
<div class="modal fade" id="uptime-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" style="margin-top: 300px; min-height: 300px; min-width: 600px " role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <h3 class="modal-title ml-4 my-2" id="exampleModalLongTitle">게시글 끌어올리기</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
 
      <div class="modal-body ml-4 my-2">
      	<h4>게시글을 끌어 올리시겠습니까?</h4>
      	<h6 style="font-size: 15px; color: #ff8c60;">*끌올시에 최상단에 보여집니다</h6>
        
      </div>
      <div class="modal-footer">
     
      <form method="post">
        <button formaction="${pageContext.request.contextPath}/board/uptime" class="button button--active px-4 py-2">끌어올리기</button>
        <button type="button" class="button button--active px-4 py-2" data-dismiss="modal">취소</button>
        <input type="hidden" name="bno" value="${bno}">
     </form>
 
      </div>
    </div>
  </div>
</div>
    
	<!--================ end related Product area =================-->  	
		<!-- remove Modal -->
<div class="modal fade" id="cheat-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" style="margin-top: 300px; min-height: 300px; min-width: 600px " role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <h3 class="modal-title ml-4 my-2" id="exampleModalLongTitle">게시글 신고</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     <form method="post" >
      <div class="modal-body ml-4 my-2">
      <h3>게시글을 신고하시겠어요?</h3>
      <h6 style="font-size: 15px; color: #ff8c60;">*신고받은 사용자는 제제를 받을 수 있습니다</h6>
	      	<h6 class="mt-5">신고 대상자 : ${board.memberName}</h6>
      	<div class="d-flex">
	      	<h6 class="mt-2">신고 분류 : </h6>
	       	<select name="cateNo" class="ml-3">
				<c:forEach items="${cheatNameList}" var="cheatListName">
					<option value="${cheatListName.cateNo}" >${cheatListName.cateNo}.${cheatListName.chReport}</option>
				</c:forEach>
			</select> 
			</div>
			<h6>상세사유 : </h6>
			<textarea name="chEtc" class="form-control" style="width: 515px; height: 150px;" placeholder="상세 사유를 입력해주세요"></textarea>
      </div>
      <div class="modal-footer">
     
        <button formaction="${pageContext.request.contextPath}/cheat/report" id="pass" class="button button--active btn-cheat px-4 py-2">신고 하기</button>
        <button type="button" class="button button--active px-4 py-2" data-dismiss="modal">취소</button>
        <input type="hidden" name="bno" value="${bno}">
      </div>
      </form>
      
    </div>
  </div>
</div>

    
	<!--================ end related Product area =================-->  	
	

<script>
	var cp = '${pageContext.request.contextPath}'
</script> 
<jsp:include page="../includes/footer.jsp"/>
<script>
$(function() {
	$("#remove").click(function () {
		$('#remove-modal').modal('show')
			
	
	})
	$("#uptime").click(function () {
		$('#uptime-modal').modal('show')
	})
	$("#cheat-btn").click(function () {
		$('#cheat-modal').modal('show')
	})
})

</script>
<script src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
	<script>
	Fancybox.bind("[data-fancybox]", {
		  infinite: false,
		});

	</script>
<script>

console.log('${trade[0].progress}'==[])
console.log('${trade[0].progress}')
console.log('${loginMember.memberNo}')
console.log('거래내역, ${trpg}')
$(function () {
	var progress = '${trade[0].progress}';
	var bno = '${board.bno}';
	
	replyService.getList({bno:bno}, function(result) {
		console.log(result)

		var str = "";

		for(var i in result) {			
		}
		if ((result == 0) && (progress==[])) {
			str += `
				<div class="form-group">등록된 후기가 없습니다</div>`;
			$(".chat").html(str);	
		}
		
		else if((result != 0) && (progress==[])) {
			str += getReplyLiStr(result[i]);
			$(".chat").html(str);	
		}			
		
		else if((result != 0) && (progress!=[])) {
			console.log(result[i].rno)
		str += getReplyLiStr2(result[i]);
			$(".chat").html(str);			
		}

		else if ((result == 0) && (progress=='3')){
			
			str += `
				<div class="form-group">
					<div style="color: gold; font-size: 2em; display: inline-block; " id="star">
					    <i class="fas fa-star"></i>
					    <i class="far fa-star"></i>
					    <i class="far fa-star"></i>					    
			  		</div>
			  		<div>
			  		<input type="hidden" class="rating" value="1">
			  		</div>
				</div>
				<div class="form-group col-12" >				
					<textarea class="comment w-100" rows="5" placeholder="후기를 입력해주세요"></textarea>
				</div>	
				</ul>
				</div>						  
            </div>          
			<button class="btn btn-primary float-right btn-sm mr-3" type="button" id="btnIn" >후기등록</button>
			</form>`;
			$(".chat").html(str);
		}		
	        $("#star").click(function getRating() {
	          console.log(event)
	          var x = parseInt((event.x - $(this).offset().left) / 45);
	          $(this).html(gridStar(x))
	          var grade = x + 1;
			$('.rating').attr("value", grade);
	        })
	        function gridStar(num) {
	          var str = ""; 
	          for(var i = 0 ; i < 3 ; i++) {
	            if(num >= i) {
	              str += '<i class="fas fa-star"></i> '
	            }
	            else{
	              str += '<i class="far fa-star"></i> '
	            }
	          }
	          return str;
	        }
	});
/* 	$(function () {
		var str =""
		if (progress==[]) {
			console.log(progress);
			console.log(progress==[]);
			str  +=
			`<div class="tradebox" data-tradeNo="\${obj.tradeNo}">
				<div class="header">
					<strong class="primary-font">판매중</strong>				
				</div>
			</div>
		</div>`;
		$(".tradebox").html(str);	
		}
	} */
	
	$(".d-flex").on("click", "#purchase", function() {
		var memberNo = '${loginMember.memberNo}'
		var str = "";
		if (memberNo==[]) {
			alert("로그인해주세요");
			$(location).attr('href', cp + '/member/login');	
		}
		
		else if (memberNo=='${trpg.memberNo}') {
			alert("이미 신청한 상품입니다.")
		}
		else {
		console.log(memberNo);
		var obj = {bno:bno, memberNo:memberNo}
		tradeService.add(obj, function(result) {
			var bno = '${board.bno}';
			var memberNo = '${loginMember.memberNo}';
			console.log(obj)
			console.log(result)						
		});
		alert("구매신청이 완료되었습니다.")
		str += `
			<button class="button button--active p-2" style="line-height: 1;"><i class="icofont-ban mr-1" style="color: #ff8c60; "></i> 신고하기</button>
			<button class="button button--active p-2 ml-auto" id="purchase" style="line-height: 1;" disabled> 구매요청완료</button>
			<button class="button button--active p-2 ml-2" style="line-height: 1;"><i style="color: #004211;" class="icofont-ui-message mr-1"></i> 쪽지 보내기</button>`;
		$("#sendFormat").html(str);
		}
	});	
	
//댓글 작성 이벤트
	$(".chat").on("click", "#btnIn", function() {
		console.log(${loginMember.memberNo});	

		var obj = {bno:bno, comment:$(".comment").val(), memberNo:'${loginMember.memberNo}', rating:$(".rating").val()}
		replyService.add(obj, function(result) {
				$(".form-group").find("textarea").val("");
				var bno = '${board.bno}';
				replyService.getList({bno:bno}, function(result) {
					var str = "";
					for(var i in result) {
						console.log(result[i]);
						str += getReplyLiStr2(result[i]);
					}
						$(".chat").html(str);	
				});
				alert("후기 작성이 완료되었습니다");
			}); 
	})

	function getReplyLiStr(obj) {
		console.log('날짜', new Date(obj.regdate).toISOString().replace('T', ' ').slice(0, -5));
		var date = new Date(obj.regdate).toISOString().replace('T', ' ').slice(0, -5)

		 return `<li class="list-group-item" data-rno="\${obj.rno}">
			<div class="header">
				<strong class="primary-font">\${obj.nickname}</strong>
				<small class="">\${obj.dong}</small>				
				<small class="float-right">\${date}</small><br>
				<small class="">평점 : \${obj.rating}점</small>
			</div>
			<div class="form-group col-10">
				<p>\${obj.comment}</p>
			</div>			
	
		</li>
		</ul>
		</div>						  
    </div>		
		</form>`;		
	}
	
	function getReplyLiStr2(obj) {
		var date = new Date(obj.regdate).toISOString().replace('T', ' ').slice(0, -5)
	
		 return `<li class="list-group-item" data-rno="\${obj.rno}">
			<div class="header">
				<strong class="primary-font">\${obj.nickname}</strong>
				<small class="">\${obj.dong}</small> 				
				<small class="float-right">\${date}</small><br>
				<small class="">평점 : \${obj.rating}점</small>
			</div>		
			<div class="form-group">
				<div style="color: gold; font-size: 2em; display: inline-block; " id="star">
				    <i class="fas fa-star"></i>
				    <i class="far fa-star"></i>
				    <i class="far fa-star"></i>					    
		  		</div>
	  		<div>
	  		<input type="hidden" class="rating" value="1">
	  		</div>
		</div>			
			<div class="form-group col-12" >				
				<textarea class="comment w-100" rows="5" placeholder="\${obj.comment}"></textarea>
			</div>
		</li>
		</ul>
		</div>						  
    </div>
    <button class="btn btn-primary float-right btn-sm mr-3" id="btnMod">후기수정</button>
		</form>`;
		
	}
	

	//댓글 수정 이벤트
	$(".chat").on("click", "#btnMod", function() {
		var obj = {bno:bno, comment:$(".comment").val(), memberNo:'${loginMember.memberNo}', rating:$(".rating").val()}	
		var str = "";	
		replyService.modify(obj, function(result) {
		
			console.log(result);

			replyService.getList({bno:bno}, function(result) {
				for(var i in result) {
					console.log(result[i]);
					str += getReplyLiStr2(result[i]);					
				}
				$(".chat").html(str);
			});	
			$(".chat").on("click", "#star", function() {
				var x = parseInt((event.x - $(this).offset().left) / 45);
		          $(this).html(gridStar(x))
		          var grade = x + 1;
				$('.rating').attr("value", grade);
		        })
		        function gridStar(num) {
		          var str = ""; 
		          for(var i = 0 ; i < 3 ; i++) {
		            if(num >= i) {
		              str += '<i class="fas fa-star"></i> '
		            }
		            else{
		              str += '<i class="far fa-star"></i> '
		            }
		          }
		          return str;
			}
			alert("후기 수정이 완료되었습니다");			
		});
	});


})
// 쪽지 보내기 버튼 클릭 시
$("#sendMessage").click(function () {
    window.location.href = "${pageContext.request.contextPath}/room/chat/${board.bno}";
});


</script>
</body>
</html>