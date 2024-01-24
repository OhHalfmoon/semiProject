<%@page import="java.util.Date" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
									<h1>신고 접수 목록</h1>
									<nav aria-label="breadcrumb" class="banner-breadcrumb">
										<ol class="breadcrumb">
											<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/cheat/penaltyList">-신고
													처리완료 목록 가기</a></li>

										</ol>
									</nav>
								</div>
							</div>
						</div>
					</section>
					<!-- ================ end banner area ================= -->


					<!--================Product Description Area =================-->
					<section class="product_description_area">
						<div class="container">
							<div class="d-flex">

								<h2 style="font-size: 20px; font-weight: 600; margin-bottom: 25px">접수 목록 </h2>
								<h2 style="margin-left:auto; font-size: 18px">총<span style="color: #4cd10f; ">${page.total}</span> 건
								</h2>
							</div>
							<c:forEach items="${cheatList}" var="cheat">
								<div class="mt-5">
									<div class="container" style="border-bottom: 1px solid #eee;">
										<div class="d-flex" style="border-bottom: 1px solid #eee;">
											<fmt:formatDate var="cheatDate" value="${cheat.chDate}" pattern="yyyy.MM.dd HH:mm:ss" />
											<h6 class="m-3 " style="font-size: 15px;">신고번호 : <span class="chNo">${cheat.chNo}</span></h6>
											<h6 class="my-3 ml-auto" style="font-size: 15px;">${cheatDate}</h6>
										</div>
										<div style="border-bottom: 1px solid #eee">
											<h6 class="mt-3 ml-3 mb-0" style="color: #4cd10f; ">신고 게시글</h4>
												<a href="${pageContext.request.contextPath}/board/${cheat.bno}" class="container">
													<div class="d-flex row  mt-0 mb-3">
														<div class="col-2">

															<div class="ml-5 m-4" style="box-shadow: 3px 3px 10px #ddd; width: 100px; height: 100px">
																<c:if test="${empty cheat.attachs}">
																	<img class="card-img"
																		src="${pageContext.request.contextPath}/resources/img/no-image-small.png" alt="">
																</c:if>
																<c:forEach items="${cheat.attachs}" var="attach">
																	<c:if test="${cheat.attachs != null}">
																		<img class="card-img"
																			src="${pageContext.request.contextPath}/displaySmall${attach.url}" alt="">
																	</c:if>
																</c:forEach>
															</div>
														</div>
														<div class="mt-5 col-9">
															<div class="d-flex">
																<h6>[${cheat.dong}]</h6>
																<h6 class="ml-3">${cheat.penaltyName}</h6>
															</div>
															<div class="d-flex mt-3">
																<h4 style="font-size: 18px;">${cheat.title}</h4>
																<h4 class="ml-auto" style=" font-size: 18px;">${cheat.price}원</h4>
															</div>
														</div>
													</div>
												</a>
										</div>
										<div>
											<h6 style="color: #4cd10f; " class="m-3">신고 유저명 : <span style="color: #333;"
													class="ml-1">${cheat.cheatName}</span></h6>
											<h6 style="color: #4cd10f; " class="m-3">신고 분류 : <span style="color: #333;"
													class="ml-2">${cheat.chReport}</span></h6>
											<div class="d-flex m-3">
												<h6 style="color: #4cd10f; ">신고 사유 : </h6>
												<div class="tab-content mx-3" style="border: 1px solid #ccc; min-height: 80px; width: 940px">
													<c:if test="${cheat.chEtc == null}">
														<h6>내용이 없습니다</h6>

													</c:if>
													<c:if test="${cheat.chEtc != null}">

														<h6>${cheat.chEtc}</h6>
													</c:if>
												</div>
											</div>
											<div class="container">
												<div class="d-felx" style="text-align: center;">
													<button type="button" style="font-size: 18px" id="cheat-btn"
														class="button button--active px-4 py-2 m-4 cheat-btn">신고처리</button>
												</div>
											</div>
										</div>
									</div>
								</div>


							</c:forEach>

							<nav class="col-12 blog-pagination justify-content-center d-flex mt-5">
								<ul class="pagination">

									<c:if test="${page.doublePrev}">
										<li class="page-item"><a class="page-link"
												href="cheatList${page.cri.queryString}&pageNum=${page.startPage -1}"><i
													class="fas fa-angle-double-left"></i></a></li>
									</c:if>
									<c:if test="${page.prev}">
										<li class="page-item"><a class="page-link"
												href="cheatList${page.cri.queryString}&pageNum=${page.cri.pageNum-1}"><i
													class="fas fa-angle-left"></i></a></li>
									</c:if>
									<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
										<li class="page-item ${page.cri.pageNum == i ? 'active' : ''}"><a class="page-link"
												href="cheatList${page.cri.queryString}&pageNum=${i}">${i}</a></li>
									</c:forEach>

									<c:if test="${page.next}">
										<li class="page-item"><a class="page-link"
												href="cheatList${page.cri.queryString}&pageNum=${page.cri.pageNum+1}"><i
													class="fas fa-angle-right"></i></a></li>
									</c:if>
									<c:if test="${page.doubleNext}">
										<li class="page-item"><a class="page-link"
												href="cheatList${page.cri.queryString}&pageNum=${page.endPage + 1}"><i
													class="fas fa-angle-double-right"></i></a></li>
									</c:if>
								</ul>

							</nav>
						</div>
					</section>
					<!-- remove Modal -->
					<div class="modal fade" id="cheat-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" style="margin-top: 300px; min-height: 300px; min-width: 600px " role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h3 class="modal-title ml-4 my-2" id="exampleModalLongTitle">신고승인</h3>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<form method="post">
								
								<div class="modal-body ml-4 my-2">
									<div class="d-flex">
										<h6 class="mt-2">처리 분류</h6>
										<select name="penaltyNo" class="ml-3">
											<c:forEach items="${penaltyNameList}" var="penaltyListName">
												<option value="${penaltyListName.penaltyNo}">
													${penaltyListName.penaltyNo}.${penaltyListName.penaltyName}</option>
											</c:forEach>
										</select>
									</div>
									<h6>상세사유</h6>
									<textarea name="penaltyEtc" class="form-control" style="width: 515px; height: 150px;"
										placeholder="상세 사유를 입력해주세요"></textarea>
								</div>
								<div class="modal-footer">

									<button id="pass"
										class="button button--active btn-cheat px-4 py-2">신고 승인</button>
									<button type="button" class="button button--active px-4 py-2" data-dismiss="modal">취소</button>
								</div>
								</form>

							</div>
						</div>
					</div>


					<!--================ end related Product area =================-->


					<jsp:include page="../includes/footer.jsp" />
					<script>
					$(document).ready(function() {
						
						$(function () {
							$(".cheat-btn").click(function () {
								var chNo = $(this).parent().parent().parent().parent().find('.chNo').text()
								$('#cheat-modal').modal('show')
								$("#pass").click(function () {
									event.preventDefault();
									var str = ""
									str += `<input type='hidden' name='chNo' value='`
									str += chNo
									str += `'>`;
									$("form").append(str).submit();
										
									//chNo값 히든으로 넘기는법
								})
							})

						})
					})

					</script>

				</body>

				</html>