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
					style="color: #4cd10f;">추</span>천 글쓰기
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
									class="form-control" placeholder="제목을 입력해주세요" id="title"
									name="title">
							</div>

							<div class="d-flex justify-content-end"
								style="margin-right: 80px;">
								<p style="color: #777; margin-top: 5px;">* 부적절한 게시글 및 거래 금지
									품목 작성시 게시글 및 사용자에게 제제가 있을 수 있습니다.</p>
								<button class="button button--active" id="ben" type="button"
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
										<option value="${category[status.index].cateNo}">
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
											<option value="${addr[status.index].addrNo}">
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
									class="form-control" placeholder="가격을 입력해주세요" id="price"
									name="price">
								<h6 style="line-height: 2.2; font-weight: 600">&nbsp;&nbsp;원</h6>
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
								
								
						          </div>								
								
							</div>
							
						<!-- 	
							<div class="uploadResult my-3">
								<ul class="list-group file-name my-3"></ul>
								<ul class="nav thumbs"></ul>
							</div> -->

						</div>
					</div>

					<div class="col-12 mt-5 pb-5" style="border-bottom: 1px solid #ccc">

						<div style="margin-left: 100px; margin-right: 100px">
							<label for="content">
								<h6 style="line-height: 2.2; font-weight: 600">상품 설명</h6>
							</label>
							<textarea name="content" id="editor"></textarea>
						</div>
					</div>
					<div class="col-12" style="margin: 100px auto; text-align: center;">
						<button type="submit" style="font-size: 18px"
							class="button button--active button-submit">글 등록하기</button>

					</div>
				</form>
				<form name="fileform" id="fileform"></form>
			</div>

		</div>
	</section>
	<!--================End Checkout Area =================-->
<!-- Modal -->
<div class="modal fade" id="modal-ben" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" style="margin-top: 100px; min-width: 800px " role="document">
    <div class="modal-content">
      <div class="modal-header">
       <h3 class="modal-title ml-4 my-2" id="exampleModalLongTitle">거래 금지 품목</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body mx-4 my-2">
      	<h4 style="color: #ff8c60;">거래 금지 품목 작성시 게시글 및 사용자에게 제제가 있을 수 있습니다.</h4>
      	<div style="border: 1px solid #eee;">
      		<h6 style="font-size: 16px; white-space: pre-wrap; padding: 10px "> 
거래금지품목은 이용약관 및 현행 법률에 따라 즉시 삭제 및 계정 차단이 될 수 있으므로 운영정책을 참고하셔서 이용 부탁 드립니다.  


개인정보 : 개인정보를 사고파는 행위와 면허증, 허가증, 등록증 등을 위, 변조하는 행위는 형법에 따라 처벌받을 수 있습니다.
예시) 주민등록증, 여권, 학생증, 수험표, 운전면허증, 대포통장, 듀얼폰, 듀얼넘버 등 
 

온라인 개인 계정 : 개인 계정 및 인증번호를 통해 각종 피해가 발생되고 있어 번개장터 운영정책에 따라 제재 됩니다.
예시) 카톡계정, 인스타 계정, 페이스북 계정, 네이버 계정, 카카오톡, 네이버, 페이스북 인증번호 등

불법현금융통 : 정보통신망 이용촉진 및 정보보호등의 관한 법률 제 72조 통신과금을 이용해 매입하는 행위는 불법현금융통으로 제재 됩니다. 
예시) 불법 현금융통을 목적으로 한 모든 상품 (불법대출, 돈구해요, 개인돈빌려요, 리니지M등)
         
사행성 : 불법 도박 및 사행성 상품은 번개장터 운영정책에 따라 제재 됩니다.
예시)토토, 카지노, 바카라, 화투, 포커, 한게임, 섯다, 바둑이, 홀덤, 세븐등 머니쿠폰, 전용장비, 라이브스코어인증 등

성인용품 : 방송통신심의위원회의 정보통신에 관한 심의 규정에 따라 음란물로 구분되는 상품은 인터넷으로 유통이 불가하며, 성인 전용 상품 판매 시 운영정책에 따라 제재 됩니다.
예시) 각종 성인용품, 특수콘돔 등 

청소년유해상품 : 청소년유해 매체물/약품/물건은 청소년 보호법에 따라 제재 됩니다.
예시) 청소년불가 등급 및 선정적 매체 (잡지, DVD, 음반, 서적, 영화 티켓 등), 레이져포인터, 캠핑용 나이프, 눈알젤리, 가스건, 공업용/산업용 강력접착제(본드) 등

반려동물/야생동물 : 반려동물 및 야생동물은 번개장터 운영정책에 따라 제재 됩니다.
예시) 반려동물 판매/구매, 반려동물 무료나눔, 유/무료 분양, 박제품, 야생동물 수렵품(이빨, 뼈 등)

비인도적 거래(장난글 포함) : 타인의 명예를 훼손하는 사람거래 관련 게시글은 아동복지법 및 정신통신망법(명예훼손)에 따라 제재 됩니다.
예시) 친구팔아요, 가족(아들, 딸 등) 팔아요 등 

주류 : 주세법에 따라 주류소매업 및 의제주류판매업 면허를 받은 자는 허가된 장소에서만 주류를 판매하여야 하고, 통신판매는 할 수 없도록 규정되어 있습니다.
예시) 양주, 맥주, 소주, 와인, 사케, 막걸리, 위스키, 보드카, 무알콜 맥주, 무알콜 칵테일 등

담배(전자담배용품) : 담배사업법에 따라 담배의 온라인 판매는 금지되어 있으며, 담뱃잎을 사용하지 않았다 하더라도 그 유사한 형태의 끽연용 제품은 담배와 동일하게 판매를 규제하고 있습니다.
예시) 담배, 전자담배, 전자담배 액상 등

군용품/군복류 : 군복 및 군용장구의 단속에 관한 법률에 따라 임의로 제조 및 판매할 경우 1년 이하의 징역 또는 1천만원 이하의 벌금에 처하도록 규정하고 있습니다. (※ 구형의 경우 제재 미적용 )
예시) 총기, 총포, 권총집, 수통, 야전삽, 천막류, 모포, 방탄모, 방탄복, 야전배낭, 전투복, 전투모, 전투화, 계급장, 야전복, 방한복, 비행복, 특전복, 유사군복 등 

경찰제복/경찰장비 : 경찰제복 및 경찰장비의 규제에 관한 법률에 따라 제조 및 판매가 금지됩니다. 
예시) 경찰공무원의 제복, 경찰의 직무수행을 위하여 필요한 장비, 경찰제복 또는 경찰장비와 형태ㆍ색상 및 구조 등이 유사하여 외관상으로는 식별이 곤란한 물품 포함

게임 계정/아이템/대리육성(듀오,강의)/코드 및 쿠폰 : 게임산업 진흥에 관한 법률 제32조 불법게임물 등의 유통금지 조항에 따라 제재 됩니다.
예시) 오버워치계정, 피파계정, 롤계정, 동물의 숲, 게임아이템(롤스킨, 피파귀속거래, 발로란트, 배틀그라운드), 스팀코드, 별풍선 삽니다/팝니다 등


신고증(허가증) 제출 필요 상품 : 신고증(허가증)을 제출하지 않고 판매 시 번개장터 운영정책에 따라 제재됩니다.
예시) 건강기능식품, 농축수산물(미가공), 의료기기, 식품 소분 판매, 제조 식품, 화장품(향수) 제조/판매, 상품권 예약판매, 종자/씨앗/묘목/삽수, 렌트카 및 공간대여, 부동산 중개 등 

의약품 : 모든 의약품(동물용 의약품 포함)은 약사법에 의거 약국 개설자(해당 약국에 근무하는 약사 또는 한약사 포함)만이 판매 가능하며, 약국 개설자의 경우에도 지방자치단체장의 승인을 얻은 경우를 제외하고는 인터넷을 통한 판매가 불가합니다.
예시) 감기약, 알러지약, 진통제, 낙태유도제, 임신테스트기, 두통약, 수면제, 마취제, 프로포폴, 식욕억제제, 구충제, 펜벤다졸, 파나쿠어, 시알리스, 비아그라, 의약성분이 포함된 다이어트 식품 등

특정 의료기기 : 유축기, MD크림, 코로나 자가진단키트, 인슐린주사기, 메디폼, 듀오덤은 '의료기기 판매 허가증' 소지 여부와 관계 없이 거래를 금지하고 있습니다. 
    
콘택트렌즈/시력보정용안경 : 의료기사 등에 관한 법률 제 12조 5항에 의거 온라인 판매가 불가합니다.
예시) 각종 렌즈(콘텍트, 써클, 컬러, 미용렌즈), 시력보정용안경, 도수안경

불법시술 : 현행 의료법령에 따라 의료인만 반영구화장과 같은 문신 시술을 할 수 있고 가정용 의료기기도 피부관리실에서 사용 불가합니다.
예시) 타투, 출장 시술, 타투 기계 등

상품권 : 재난기본소득은 부정한 방법으로 지급받은 경우 지역 조례에 따라 환수 조치 될 수 있으며, 선불카드/지역화폐를 거래하는 경우 접근 매체를 넘겨주거나 받는 행위로 판매자, 구매자 모두 처벌 받을 수 있습니다. (전자금융거래법 제49조 4항 1호)
예시) 문화누리카드, 온누리상품권, 지역사랑상품권, 지역화폐, 티머니 교통카드, 네이버페이 포인트 등

비트코인/가상화폐/암호화폐 : 암호화폐를 판매할 경우 번개장터 운영 정책에 따라 제재됩니다. 
예시) 모든 유형의 비트코인, 가상화폐, 암호화폐, 비상장코인, 파이코인, 페이코인, NFT 등

불법상품 : 부정경쟁방지 및 영업비밀보호에 관한 법률에 따라 판매가 불가합니다.
예시) 개인차 렌트, 개통불가휴대폰, 불법소프트웨어복제품, 신용불량자(미납연체자)대상 휴대폰 개통 및 판매, 적합성 평가를 받지 않은 불법방송통신기자재 등 법률을 위반하는 상품, 불법 프로그램 (해킹프로그램, 악성프로그램, 번개장터 상품등록 프로그램)

안전인증표시 없는 안전인증대상제품 : 안전 인증 표시 없는 안전 인증 대상 제품은 전기용품 및 생활용품 안전관리법상 판매 금지됩니다.
예시) 안전 인증, 안전 확인 받지 않은 단전지


리콜 대상 상품 : 리콜 대상 상품은 소비자기본법에 따라 제조 및 판매가 금지됩니다. 

리콜 대상 상품이란 물품 등의 결함으로 인하여 문제 발생 소지가 있어 점검, 수리, 교환 등의 조치가 필요한 상품을 말합니다. 
예시) 쿠첸 10인용 전기압력밥솥 (CRT-RPK1040I 포함 6개 모델) 등 

지식재산권(상표권/저작권 /디자인권) 침해 상품 :  상표법 제 108조를 위반한 상품은 민/형사상의 처벌을 받을 수 있습니다.
예시) 위조 상품, 가품, 레플리카, 이미테이션, 커펌 기기, 온라인 수강권 (강의 자료) 등 타인의 상표권 또는 저작권을 무단으로 사용하여 영리목적으로 판매하는 모든 상품, 정품 여부가 불분명한 상품

 
비매품 : 화장품 샘플, 견본품은 화장품법에 따라 판매 금지됩니다. 

이 외 실물이 없는 (택배/우편 등의 배송 과정이 불필요한) 각종 비매품 판매 시 번개장터 운영정책에 따라 제재 됩니다. 

단, 실물이 있더라도 아래 상품들은 거래 금지 품목에 해당됩니다.

브랜드사 노벨티 상품(정품 여부를 확인할 수 없음), 백화점 VIP 개인 혜택(백화점 요청에 의함)
예시) 실물이 없는 각종 이벤트 쿠폰, 할인쿠폰 (코드/PIN/바코드 등 번호만 발송하는 형태), 통신사데이터, 각종 온라인몰 적립 포인트 또는 혜택, 네이버페이 포인트 등등 택배/우편 등의 배송이 불필요한 무형의 각종 비매품, 백화점 VIP 혜택(주자권, 라운지 이용권, 구매 실적 거래 등)

화장품 소분판매 : 화장품 원 상품을 판매의 목적으로 의도적으로 소분하여 판매하는 것은 화장품법에 따라 판매가 불가합니다.
예시) 200ml 본품을 10ml로 소분 판매 등 

개인정보활용 : 개인정보를 활용한 상품 판매는 번개장터 운영정책에 따라 제재 됩니다.
예시) 유투브/넷플릭스 등 OTT 서비스 계정 공유 및 이용권 판매, 각종 사이트 인증번호, 커뮤니티 계정, 온라인 강의 계정, 조건부 무료나눔(개인정보 및 라스인증 요구), 가입유도 후 추천코드 등록요청 , 코로나19 백신 대리 예약

유해화학물질/유류 : 유독물, 관찰물질, 취급 제한 금지물질, 사고대비물질, 그 밖에 유해성 또는 위해성이 있거나 그러한 우려가 있는 화학물질 및 경유/휘발유/등유 등의 유류는 거래 불가합니다.
예시) 농약, 독극물, 이산화질소, 벤젠, 파라벤, 트리클로산, 포름알데히드, 라돈, 경유, 휘발유, 등유, 질소 등   

총포류 : 총포소지 허가증 발급이 필요한 상품으로 판매 불가 합니다.
예시) 총포, 도검, 화약류, 분사기, 전기충격기, 석궁, 총구에 컬러파츠(색깔 표시) 없는 비비탄 총

혈액(헌혈증) : 혈액관리법에서는 금전·재산상의 이익이나 기타 대가적 급부를 주거나 받음으로써 혈액 또는 헌혈증서를 매매하는 행위를 금지합니다.


촬영여부를 알기 어렵도록 제작된 초소형 카메라 및 변형카메라
예시) 변형카메라 : 볼펜, 시계, 안경, 자동차 키, 라이터, 액자 등의 모양

암표 : 암표 매매란 흥행장, 경기장, 역, 나루터, 정류장, 그 밖에 정하여진 요금을 받고 입장시키거나 승차 또는 승선 시키는 곳에서 웃돈을 받고 입장권, 승차권 또는 승선권을 다른 사람에게 되파는 것을 뜻합니다.

열차 승차권은 철도사업법 제 10조 2항에 따라 부정 판매 및 양도할 수 없으며, 무료로 받은 입장권/초대권 등은 무료 나눔만 가능합니다. 
예시) 내일로 패스 / 내일로 바우처 등의 열차 승차권 /  주최측 요청에 의한 재판매 불가 티켓

개인 사용 목적으로 해외 직구한 전자제품 :
적합성 평가 면제조건에 해당하는 “개인이 사용할 목적의 제품”을 해외직구로 구입하여 사용하다가 중고 판매를 하고자 하는 경우 반입일로부터 1년이 경과한 경우에만 판매가 가능합니다.


주식/코인/해외선물 등 투자 정보 리딩방 : 주식/코인/해외선물 리딩방 등 투자 정보를 공유하는 상품은 사기 피해 및 분쟁 발생 가능성이 있어 번개장터 운영정책에 따라 제재 됩니다.
예시) 국내주식 리딩, 비트코인 리딩, 해외선물 리딩 등

기타 : 폰테크 관련 상품, 상품 상태를 알 수 없는 랜덤박스, 쿠팡 나중결제 한도 판매, 유통기한 경과 상품(가공식품, 화장품, 생활용품 등 모두 포함), 개봉된 식품, 목적 불분명한 알바, 영리목적으로 제3자에게 자료 및 정보 제공 등 거래 시 번개장터 운영정책에 따라 제재 됩니다.

 
 
위 거래금지품목 이외  전자상거래 또는 통신판매로 유통/판매가 금지되어 있거나, 사회통념상 매매에 부적합하다고 '회사'가 판단하는 경우 운영정책에 따라 제재 됩니다.

 
-출처: 번개장터-
</h6>
        </div>
      </div>
      <div class="modal-footer">
       <button type="button" class="button button--active px-4 py-2" data-dismiss="modal">확인</button>
        
      </div>
    </div>
  </div>
</div>
	<jsp:include page="../includes/footer.jsp" />
	<script>
$(function() {
	$("#ben").click(function () {
		$('#modal-ben').modal('show')
		
			
		})
	})

	


</script>
	<script>
					ClassicEditor.create(document.querySelector('#editor'), {

					});
	</script>
	<script>
	Fancybox.bind("[data-fancybox]", {
		  infinite: false,
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
							console.log($("#boardform").serializeArray());
							
							
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
							              <div class="card-product__img data" data-uuid="\${obj.attUuid}" data-path="\${obj.attPath}" data-name="\${obj.attName}" data-file="\${obj.url}" >
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