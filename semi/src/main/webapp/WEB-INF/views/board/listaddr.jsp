<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../includes/head.jsp" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<!-- ================ start banner area ================= -->
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>싱싱한 <span style="color: #78e284">우리동네 </span> 상추마켓</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page"> 우리동네마켓</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- ================ end banner area ================= -->


	<!-- ================ category section start ================= -->
	<section class="section-margin--small mb-5">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Start Filter Bar -->
					<div class="d-flex mb-5 ">
						<h6 style=" margin-top: 20px; margin-right: 5px">우리 동네로 부터</h6>
						
						<div class="sorting">
						<c:set var="boundaries" value="1,3,5" />
							<select name="option" class="boundary">
								<c:forTokens items="${boundaries}" delims="," var="boundary">
									<option value="${boundary}" ${param.option == boundary ? 'selected' : '' }>${boundary}Km까지 보기</option>
								</c:forTokens>
							</select> 
						</div>
						<div class="sorting ml-auto">
							<c:set var="amounts" value="12,24,36" />
							<select class="amount">
								<c:forTokens items="${amounts}" delims="," var="amount">
									<option value="${amount}" ${page.cri.amount == amount ? 'selected' : '' }>${amount}개씩 보기</option>
								</c:forTokens>
							</select> 
							<form>
								<input type="hidden" name="pageNum" value="1"> 
								<input type="hidden" name="amount" value="${page.cri.amount}">
							
							</form>
						</div>
					</div>
					<!-- End Filter Bar -->
					<!-- Start Best Seller -->
					<section class="lattest-product-area pb-40 category-list">
						<div class="row">
						
							<c:if test="${empty list}">
						<div class="container mt-5" style="text-align: center;">
							<h3 class="mt-5">이런! 상추가 없어요! 나부터 상추를 하러 가볼까요?</h3>
							 <a class="button button--active my-5" href="${pageContext.request.contextPath}/board/write">판매하러가기</a>
						</div>
						</c:if>
							<c:forEach items="${list}" var="board">
							 <c:forEach items="${board.attachs}" var="attach"> 
								
								<div class="col-md-6 col-lg-4 col-xl-3">
									<div class="card text-center card-product">
										<div class="card-product__img" style="box-shadow: 3px 3px 20px #ddd;">
										<a href="${pageContext.request.contextPath}/board/${board.bno}">
											<c:if test="${attach.attNo == null}">
												<img class="card-img"
													 src="${pageContext.request.contextPath}/resources/img/no-image.png"
													alt="">
											</c:if>
											<c:if test="${attach.attNo != null}">
											<img class="card-img"
												 src="${pageContext.request.contextPath}/display${attach.url}"
												alt="">
											</c:if>
											</a>
											<ul class="card-product__imgOverlay">

												<li><a href="${pageContext.request.contextPath}/shop/${board.memberNo}"><button>상점 보러가기</button></a></li>
											
											</ul>
										</div>
										<div class="card-body row p-3" style="text-align: left;">
											<p class="col-6">${board.cateName}</p>
											<p class="col-6 " font-size: 15px; style="text-align: right;">
												
											</p>
											<h4 class=" col-12 card-product__title mb-0">
												<a href="${pageContext.request.contextPath}/board/${board.bno}">${board.title}</a>
											</h4>
											<p class="col-12" style="font-size: 13px;">${board.addrName}</p>
											<c:if test="${board.memberName == null}">
												<p class="col-6 mt-4">탈퇴한 회원</p>
											</c:if>
											<c:if test="${board.memberName != null}">
												<p class="col-6 mt-4">${board.memberName}</p>
											</c:if>
											<h5 class="card-product__price col-6 mt-4"
												style="text-align: right;">${board.price}원</h5>
										</div>
									</div>
								</div>

							 </c:forEach>  
							</c:forEach>
							


							<nav class="col-12 blog-pagination justify-content-center d-flex mt-5">
								<ul class="pagination">

									<c:if test="${page.doublePrev}">
										<li class="page-item"><a class="page-link"
											href="listaddr${page.cri.queryString}&pageNum=${page.startPage -1}&option=${param.option}"><i
												class="fas fa-angle-double-left"></i></a></li>
									</c:if>
									<c:if test="${page.prev}">
										<li class="page-item"><a class="page-link"
											href="listaddr${page.cri.queryString}&pageNum=${page.cri.pageNum-1}&option=${param.option}"><i
												class="fas fa-angle-left"></i></a></li>
									</c:if>
									<c:forEach begin="${page.startPage}" end="${page.endPage}"
										var="i">
										<li class="page-item ${page.cri.pageNum == i ? 'active' : ''}"><a
											class="page-link"
											href="listaddr${page.cri.queryString}&pageNum=${i}&option=${param.option}">${i}</a></li>
									</c:forEach>

									<c:if test="${page.next}">
										<li class="page-item"><a class="page-link"
											href="listaddr${page.cri.queryString}&pageNum=${page.cri.pageNum+1}&option=${param.option}" ><i
												class="fas fa-angle-right"></i></a></li>
									</c:if>
									<c:if test="${page.doubleNext}">
										<li class="page-item"><a class="page-link"
											href="listaddr${page.cri.queryString}&pageNum=${page.endPage + 1}&option=${param.option}"><i
												class="fas fa-angle-double-right"></i></a></li>
									</c:if>
								</ul>

							</nav>
							<!-- End Best Seller -->
						</div>
				</div>
			</div>
	</section>
	<!-- ================ category section end ================= -->



	<jsp:include page="../includes/footer.jsp" />
	<script>
	$(function() {
		$(".amount").change(function() {
	
				let page = '${page.cri.pageNum}';
				let amount = $(this).val();
				let option = $(".boundary").val();			
				let obj = {
					pageNum : page,
					amount : amount,
					option : option
				};
				location.search = $.param(obj);
				
			})
		$(".boundary").change(function() {
	
				let page = '${page.cri.pageNum}';
				let amount = $(".amount").val();
				let option = $(this).val();			
				let obj = {
					pageNum : page,
					amount : amount,
					option : option
				};
				location.search = $.param(obj);
				
			})
		
	})
	</script>
</body>
</html>