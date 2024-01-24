   
# 📌프로젝트명

> 상추조
> project : "상상하던 당신의 물품을 추천합니다" 상추마켓  
> 주제 : 위치기반 중고거래 플랫폼 


![](../header.png)

## 🛠개발 환경 설정🛠

> Front-end
- JQuery & AjAX, BootStrap4

> Back-end
- JAVA 1.8, Spring framework5, Framework Mybatis, MariaDB
  
> API & Library
- 다음 지도 API, 카카오 맵 API, WebSocket Library,  
CKEditor, FancyBox, mailgun
 
> Tools
- STS, DBeaver, SVN

![](../header.png)
![](../header.png)
## 사용 예제
![ApiKey](https://github.com/OhHalfmoon/semiProject/assets/132035690/540753df-038e-46c1-b9d7-e7987873ba27)
- 중고거래라는 컨셉을 활용하기 위해 회원가입시 주소명을 받고 있습니다.   
- 카카오 api key를 지운상태입니다.  
   src/main/webapp/WEB-INF/views/member/address.jsp 페이지에서 API추가가 필요합니다.  
#addressSearch 클릭이벤트 JS함수의 ajax에서 headers 부분에 추가하시면 해당 기능을 사용할 수 있습니다. 
- 프로젝트는 git이 아닌 svn을 통해 진행하였으며, 프로젝트 종료 후 조원별 git으로 commit하였습니다. 


![](../header.png)
## 구현내용

### <a href="https://jonas-portfolio.notion.site/jonas-portfolio/Semi-Spring-Legacy-71fe114d63864152bff202ba181c04c2">Notion Link</a> <br>
### <a href="https://ovenapp.io/view/uzP0FsOGlJcAkMrYQmxIQDuodaBQ0kqX#uizuV">프로토타입 화면예시</a> <br>
### 🧑‍🤝‍🧑조원구성  
- 팀장 이시화 : 게시글 C.R.U.D, 각 조건별 리스트, 첨부파일, 관리자
- 팀원 방한솔 : 회원 C.R.U.D, 이메일인증, 마이페이지
- 팀원 오상현 : 주소(위치기반), 거래 신청 및 승인여부, 후기작성
- 팀원 이영미 : VO 및 DTO구현, 프로젝트 문서보안, 회의록 작성
- 팀원 함준혁 : 실시간 채팅, 상점 페이지



### 🧑‍🤝‍🧑담당역할 내용  
    
1. 위치기반(Address) :
- 다음API를 통해 회원으로 부터 입력받은 주소의 정확한 도로명주소를 받아냅니다.
- 받아낸 도로명주소를 카카오API에 입력하여 해당 주소의 좌표값(x,y)을 받을 수 있습니다.
  ![address](https://github.com/OhHalfmoon/semiProject/assets/132035690/22b4eda8-cd20-420d-8102-f201cbf63174)
-  회원의 주소록에는 도로명주소, 세부주소, 위도, 경도, 대표주소여부를 받아와 저장합니다.
-  회원가입시 받은 주소가 대표주소로 기본설정되며, 마이페이지의 주소록설정에서 추가주소 등록 및 대표주소 변경이 가능합니다.
-  판매상품 등록시 주소를 새롭게 등록이 가능하며, 상품 등록시 결정한 주소의 PK값을 게시글(Board)이 FK로 받습니다.
-  상품에 등록된 위경도값과 로그인한 회원의 대표주소 위경도값을 계산하여 거리를 산출하고,  
이를 통해 설정한 km반경값(1km, 3km, 5km)에 따라 게시글이 화면에 나타납니다.
<br/>
<br/>



 2. 거리계산 :    
- 최초의 거리계산은 JAVA Service 메서드에서 처리하려했습니다.
- 한 메서드에서 여러번의 DB호출이 이루어지는 단점이 발생했습니다.  
이를 해결하고자 DB에서 함수를 설정하여 DB가 다중으로 호출되어 서버 부하를 방지하였습니다.

![distance](https://github.com/OhHalfmoon/semiProject/assets/132035690/be999950-9753-4be2-ba4b-120360356f01)
- 거리를 구하는 공식은 구면좌표계 공식을 사용했습니다.
<br/>
<br/>

3. 상품 거래 신청 수락 및 거절(Trade) : 
- 물품구매는 거래요청, 거래중, 거래완료 3단계로 이루어집니다.
- 상품 상세보기 페이지에서 구매요청하기 버튼을 통해 새로운 거래를 insert 합니다.
![trade](https://github.com/OhHalfmoon/semiProject/assets/132035690/4b2fb34b-ae88-43e8-95d5-379bd68458fd)
- 최초 요청시 거래단계(Progress)값은 1을 기본값(거래요청)으로 시작합니다.
- 요청을 수락할 경우 거래단계(Progress)값이 1씩 증가(update)하며, 거래중은 2, 거래완료는 3의 값을 갖습니다.
- 1개의 상품에 여러 요청을 받을 수 있으며, 구매자는 중복요청을 할 수 없습니다.
- 마이페이지에서 구매요청, 받은요청을 확인 할 수 있으며 판매자, 구매자 모두 요청취소(Delete)가 가능합니다.
- 판매완료 상품(Progress=3)에는 구매요청을 보낼 수 없도록 하였습니다.
<br/>
<br/>
  
4. 후기(Reply) :
-  판매완료 상품의 후기는 구매자만 작성 및 수정이 가능합니다.
-  구매후기는 text후기와 별점(Rating)으로 이루어져 있습니다.
-  별점은 1점을 기본으로 부여하며, 흰색 별을 클릭할 경우 노란색 별로 변경되어 별점이 추가됩니다.
-  클릭이벤트로 위치좌표값을 수식을 통해 점수로 환산하여 후기평점을 측정하였습니다.
  

![star1](https://github.com/OhHalfmoon/semiProject/assets/132035690/43e7faff-ff5f-409d-9915-229a433a18ab)
![star2](https://github.com/OhHalfmoon/semiProject/assets/132035690/2aa6acac-7230-42af-ac3c-ec7632bebfe8)




![](../header.png)
## Document
>#### 파일첨부 목록 : 발표ppt, API정의서, Table명세서, TestCase, 업무분장표, 요구사항 정의서

  

  


