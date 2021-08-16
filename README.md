# AUCSUSU MARKET
## Kubernetes를 활용한 경매 방식 중고거래 웹 어플리케이션
----
### 개발 배경
> 요즘 중고거래가 굉장히 많이 이루어지고 있습니다. 
저희는 중고거래를 이용하면서 가격책정에 많은 어려움을 느꼈습니다.
그래서 이를 경매 방식으로 전환한다면 구매자와 판매자 모두 만족하는 적정 가격에 거래가 이루어질 수 있을 것이라 생각하여 
이를 개발하기로 하였습니다. 

### 프로젝트 설명
네이버 클라우드, 스프링부트와 JPA로 개발한 컨테이너 기반의 경매 방식의 중고 거래 사이트

### 프로젝트 실행
Issues 참고
(쿠버네티스 성공하면 나중에 추가)

### 접속 방법
www.aucsusu.kro.kr
<br>

<br>
네이버 클라우드 플랫폼의 Global DNS 활용(2021년 8월 말 만료예정)

### 기능
1. 회원 가입/수정/탈퇴<br>
      - 회원가입<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450708-63da70e8-ab94-4ed0-bffe-e537180b6dfd.png"
      width="80%" height="80%"> <!--회원가입-->
      - 회원정보 수정/탈퇴<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450922-ce497cb6-3c63-4311-a53d-952f507cb0ea.png"
      width="80%" height="80%"> <!--회원수정,탈퇴-->
    
2. 로그인/로그아웃<br>
      - 로그인<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450818-99428355-0853-4589-a1cc-965c798010ab.png"
      width="80%" height="80%">
      - 로그아웃<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129451066-ec2f51a9-8296-48f7-b718-af77158fae80.png"
      width="80%" height="80%"> <!--인덱스 페이지-->

3. 상품 등록/조회/수정/삭제
    <br>
      - 상품 등록<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450852-8b6f89dd-70d7-4383-93ec-8b39f135b336.png"
      width="80%" height="80%"> <!--등록-->
      - 상품 목록<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129451290-ac8c58e0-1328-46bc-ae17-056825af4e37.png"
      width="80%" height="80%"> <!--목록-->
      - 상품 상세 조회<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450957-2a0080a5-f38e-4449-8c83-60b0f7f15504.png"
      width="80%" height="80%"> <!--상세조회-->
      - 상품 경매 등록 내역<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450897-286aed32-faf9-4018-8fcf-523a23bda970.png"
      width="80%" height="80%"> <!--등록내역-->
      - 가격 제안 상품 내역<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129450986-dee5396e-e708-4b8e-a09c-5c0723e94779.png"
      width="80%" height="80%"> <!--입찰내역-->
      

4. 가격 제시/낙찰
    - 현재가 보다 높은 가격만 제시 가능

5. 이용자간 대화<br>
      - 채팅<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129451015-f4907784-9e7e-47bc-85d5-ac0657116544.png"
      width="80%" height="80%"> <!--채팅-->
      - 채팅 내역<br>
      <img src = "https://user-images.githubusercontent.com/58590696/129451021-6787469a-e0cc-4751-8c79-e16e1b03cf4a.png"
      width="80%" height="80%"> <!--채팅 목록-->

6. 물품/사용자 신고
      - 일정 횟수 이상 신고된 사용자는 상품 등록 불가
      - 일정 횟수 이상 신고된 물품은 숨김 처리(상세 조회 불가)
