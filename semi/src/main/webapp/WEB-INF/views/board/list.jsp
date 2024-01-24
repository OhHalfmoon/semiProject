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
					<h1>전국 곳곳<span> 상추</span>보기!</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">전체 상품 보기</li>
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
					<div class="d-flex mb-3">
						<div class="sorting mt-3">
						 <c:if test="${loginMember eq null}">
						
							<a href="${pageContext.request.contextPath}/member/login"><h6 style="color: #78e284">로그인 하시면 우리동네 상추를 만나볼 수 있어요!</h6></a>
						</c:if>
						<c:if test="${loginMember ne null}">
							<a href="${pageContext.request.contextPath}/board/listaddr"><h6 style="color: #78e284">우리동네 상추만 만나러가기</h6></a>
          				</c:if>
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
											href="list${page.cri.queryString}&pageNum=${page.startPage -1}"><i
												class="fas fa-angle-double-left"></i></a></li>
									</c:if>
									<c:if test="${page.prev}">
										<li class="page-item"><a class="page-link"
											href="list${page.cri.queryString}&pageNum=${page.cri.pageNum-1}"><i
												class="fas fa-angle-left"></i></a></li>
									</c:if>
									<c:forEach begin="${page.startPage}" end="${page.endPage}"
										var="i">
										<li class="page-item ${page.cri.pageNum == i ? 'active' : ''}"><a
											class="page-link"
											href="list${page.cri.queryString}&pageNum=${i}">${i}</a></li>
									</c:forEach>

									<c:if test="${page.next}">
										<li class="page-item"><a class="page-link"
											href="list${page.cri.queryString}&pageNum=${page.cri.pageNum+1}"><i
												class="fas fa-angle-right"></i></a></li>
									</c:if>
									<c:if test="${page.doubleNext}">
										<li class="page-item"><a class="page-link"
											href="list${page.cri.queryString}&pageNum=${page.endPage + 1}"><i
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
	$(".amount").change(function() {

			let page = '${page.cri.pageNum}';
			let amount = $(this).val();
		
			let obj = {
				pageNum : page,
				amount : amount,
			};
			location.search = $.param(obj);
			
		})
	</script>
</body>
</html>