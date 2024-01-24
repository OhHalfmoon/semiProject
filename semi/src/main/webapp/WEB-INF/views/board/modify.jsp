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
	<section>
		<div class="container" style="margin-top: 70px">
			<h2 style="font-size: 20px; font-weight: 600">
				<span style="color: #4cd10f;">상</span>상하던 상품 <span
					style="color: #4cd10f;">추</span>천 글 수정하기
			</h2>
		</div>
	</section>
	<!-- ================ end banner area ================= -->


	<section class="checkout_area mt-5">
		<div class="container">
			<div class="returning_customer">
				<form class="contact_form" method="post" id="boardform">
					<div class="check_title d-flex"
						style="border-bottom: 1px solid #ccc">
						<h2>기본정보</h2>
						<h2 style="color: #ff8c60; margin-left: auto; margin-right: 15px; font-size: 15px">
							*필수입력
						</h2>
					</div>
					<div class="col-12 mt-5 pb-4" style="border-bottom: 1px solid #ccc">
							
						<div style="margin-left: 100px; margin-right: 100px">
							<div class="d-flex"">
								<label for=" title">
									<h6 style="line-height: 2.2; font-weight: 600">제목</h6>
								</label> <input style="width: 750px; margin-left: 20px;" type="text"
									class="form-control" value="${board.title}" placeholder="제목을 입력해주세요" id="title"
									name="title">
							</div>

							<div class="d-flex justify-content-end"
								style="margin-right: 80px;">
								<p style="color: #777; margin-top: 5px;">* 부적절한 게시글 및 거래 금지
									품목 작성시 게시글 및 사용자에게 제제가 있을 수 있습니다.</p>
								<button class="button button--active" type="button"
									style="line-height: 0.6; margin-top: 3px; padding: 10px; margin-left: 20px; height: 30px;">거래금지품목</button>
							</div>
						</div>

					</div>
					<div class="col-12 mt-5 pb-5" style="border-bottom: 1px solid #ccc">
						<div class="d-flex "
							style="margin-left: 100px; margin-right: 100px">
							<h6 style="line-height: 3.8; font-weight: 600">카테고리</h6>
							<div class="sorting"
								style="margin-left: 10px; margin-right: 50px">
								<select name="cateNo">
									<c:forEach items="${category}" varStatus="status">
										<option value="${category[status.index].cateNo}"${category[status.index].cateNo eq board.cateNo ? 'selected' : ''}>
											<c:out value="${category[status.index].cateName}"></c:out>
										</option>
										
									</c:forEach>
								</select>
							</div>
							<div style="margin-left: auto;" class="d-flex">
								<h6 style="line-height: 3.8; font-weight: 600">주소</h6>
								<div class="sorting "
									style="margin-left: 10px; text-align: right;">
									<select name="addrNo">
										<c:forEach items="${addr}" varStatus="status">
											<option value="${addr[status.index].addrNo}" ${addr[status.index].addrNo eq board.addrNo ? 'selected' : ''}>
												<c:out value="${addr[status.index].addr}"></c:out>&nbsp;&nbsp;
												<c:out value="${addr[status.index].addrDetail}"></c:out>
											</option>
										</c:forEach>
									</select> <br> <a class="mt-2" href="${pageContext.request.contextPath}/member/address">주소록 추가/수정하기</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12 mt-5 pb-5" style="border-bottom: 1px solid #ccc">

						<div style="margin-left: 100px; margin-right: 100px">
							<div class="d-flex"">
								<label for=" price">
									<h6 style="line-height: 2.2; font-weight: 600">가격</h6>
								</label> <input style="width: 500px; margin-left: 20px;" type="number"
									class="form-control" value="${board.price}" placeholder="가격을 입력해주세요" id="price"
									name="price">
								<h6 style="line-height: 2.2; font-weight: 600">&nbsp;&nbsp;원</h6>
							<ul class=" ml-auto mr-5">
								<li class="filter-list"><input class="pixel-radio" type="checkbox" name="blind" id="blind" style="width: 16px;height: 16px;"><label for="blind" style="font-size: 15px">글 비공개</label></li>
							</ul>
							</div>
						</div>
					</div>
					
					<div class="check_title d-flex"
						style="border-bottom: 1px solid #ccc; margin-top: 100px">
						<h2>부가정보</h2>
						<h2 style="color: #4cd10f; margin-right: 15px; font-size: 15px">
							사진과 설명이 상세하면 많은 상추친구가 관심을 가질거에요!
							</h2>
					</div>
					<div class="col-12 mt-5 pb-5" style="border-bottom: 1px solid #ccc">

						<div style="margin-left: 100px; margin-right: 100px">
							<div class="d-flex">
								<h6 style="line-height: 2.2; font-weight: 600">상품 이미지</h6>
								<h2 style="margin-left:auto; color: #4cd10f;line-height: 2.2; margin-right: 15px; font-size: 15px; font-weight: 200">
								사진을 드래그하면 보여질 순서를 바꿀 수 있어요
								</h2>
							
							</div>
							<div class="">
							<div class="uploadResult row d-flex image-sort">
							<div class="col-4 image-add">
								<div class="card text-center card-product ">
									<label style="cursor: Pointer;" class="p-0 m-3" for="file"> 
										<img class="card-img" style="box-shadow: 3px 3px 20px #ddd; width: 260px; height: 280px" src="${pageContext.request.contextPath}/resources/img/image_add.png">
									</label> 
									<input type="file" name="file" multiple id="file" accept="image/jpeg,image/png" style="display: none;" form="fileform">
									
								</div>
							
							</div>
							
							
							<c:forEach items="${board.attachs}" var="attach">
							<c:if test="${attach.attName != null}">
								<div class="col-4 image-thumb">
							            <div class="card text-center card-product">
							              <div class="card-product__img data" data-uuid="${attach.attUuid}" data-path="${attach.attPath}" data-name="${attach.attName}" data-file="${attach.url}">
							                <img class="card-img img-thumbnail mt-3" src="${pageContext.request.contextPath}/display${attach.url}"  style="box-shadow: 3px 3px 20px #ddd; width: 260px; height: 280px; padding:0;margin:0; border:none; " alt="">
							                <ul class="card-product__imgOverlay">
							                  <li><button class="img-thumb" data-fancybox="gallery" data-src="${pageContext.request.contextPath}/displayOrigin${attach.url}" type="button">이미지 원본보기</button></li>
							                  <li><button class="btn-remove" type="button"><i style="font-size: 28px" class="icofont-close"></i></button></li>
							                </ul>
							              </div>
							              <h6 class="mt-2">[${attach.attName}]</h6>
							            </div>
							          </div>	
								</c:if>	
							</c:forEach>
						    </div>								
								
							</div>
							
						

						</div>
					</div>

					<div class="col-12 mt-5 pb-5" style="border-bottom: 1px solid #ccc">

						<div style="margin-left: 100px; margin-right: 100px">
							<label for="content">
								<h6 style="line-height: 2.2; font-weight: 600">상품 설명</h6>
							</label>
							<textarea name="content" id="editor">${board.content}</textarea>
						</div>
					</div>
					<div class="col-12 " style="margin: 100px auto; text-align: center;">
						<button type="submit" style="font-size: 18px"
							class="button button--active button-submit">글 등록하기</button>
						

					</div>
				</form>
				<form name="fileform" id="fileform"></form>
			</div>

		</div>
	</section>
	<!--================End Checkout Area =================-->

	<jsp:include page="../includes/footer.jsp" />
	
	<script>
					ClassicEditor.create(document.querySelector('#editor'), {

					});
	</script>
	<script>
					$(function () {
						
						$("form .button-submit").click(function() {
							event.preventDefault();
							//title, content, writer, attachs[0].uuid
							var str = '';
							
							$(".data").each(function(i) {
								var uuid = $(this).data('uuid');
								str += `
								<input type="hidden" name="attachs[\${i}].attUuid" value="\${uuid}">
								<input type="hidden" name="attachs[\${i}].attName" value="\${$(this).data('name')}">
								<input type="hidden" name="attachs[\${i}].attPath" value="\${$(this).data('path')}">
								`;
								
							})
							console.log(str);
							$("#boardform").append(str).submit();
							//console.log($("#boardform").serializeArray());
							
							
						})
	
							$("#file").change(function () {
								event.preventDefault();
								let files = $("#file").get(0).files;
	
								if (!checkExtension(files)) {
									alert("조건 불일치")
									return false;
								}
								let formData = new FormData();
	
								for (let i in files) {
									formData.append("files", files[i])
								}
	
								$.ajax({
									url: "${pageContext.request.contextPath}/uploadA",
									processData: false,
									contentType: false,
									data: formData,
									method: "post",
									success: function (data) {
										showUploadedFile(data);
									}
								})
								$("#fileform").get(0).reset();
							})
								function checkExtension(files) {
	
									const MAX_SIZE = 5 * 1024 * 1024;
									const EXCLUDE_EXT = new RegExp("(.*?)\.(js|jsp|asp|php)");
									for (let i in files) {
										if (files[i].size >= MAX_SIZE
											|| EXCLUDE_EXT.test(files[i].name)) {
											return false;
										}
									}
									return true;
	
								}
								function showUploadedFile(uploadResultArr) {
									var str = "";
									var imgStr = "";
									for (var i in uploadResultArr) {
										let obj = uploadResultArr[i];
										str +=  `
										<div class="col-4 image-thumb">
							            <div class="card text-center card-product">
							              <div class="card-product__img data" data-uuid="\${obj.attUuid}" data-path="\${obj.attPath}" data-name="\${obj.attName}" data-file="\${obj.url}">
							                <img class="card-img img-thumbnail mt-3" src="${pageContext.request.contextPath}/display\${obj.url}"  style="box-shadow: 3px 3px 20px #ddd; width: 260px; height: 280px; padding:0;margin:0; border:none; " alt="">
							                <ul class="card-product__imgOverlay">
							                  <li><button class="img-thumb" data-fancybox="gallery" data-src="${pageContext.request.contextPath}/displayOrigin\${obj.url}" type="button">이미지 원본보기</button></li>
							                  <li><button class="btn-remove" type="button"><i style="font-size: 28px" class="icofont-close"></i></button></li>
							                </ul>
							              </div>
							              <h6 class="mt-2">[\${obj.attName}]</h6>
							            </div>
							          </div>
							          `;
									
									}
									$(".uploadResult").append(str);
									console.log(str)
								}
							$(".uploadResult").on("click", ".btn-remove", function() {
								var file = $(this).parent().parent().parent().data("file");
								
										
								$.ajax({
									url : "${pageContext.request.contextPath}/deleteFile"+file,
									success : function(data) {
										$('[data-uuid="' + data + '"]').parent().parent().remove()
										
									
									}})
								
							})
							$(".fancybox").bind("[data-fancybox]", {
							  // Your custom options
							   Thumbs : {
						          type: "modern"
						        }//안먹음
							});
	/* 
						 	$(".uploadResult").on("click",".img-thumb" ,function() {
								event.preventDefault();
								var param = $(this).find("img").data("src")
								$("#showImageModal").find("img").attr("src", "${pageContext.request.contextPath}/display?" + param).end().modal("show");		
							})  */

							 $( ".image-sort").sortable({
								 sort : function(event, ui) {
									
								},
								cursor: "move",
								cancel: ".image-add"
								 
							 })
						
						
					})
</script>
</body>

</html>