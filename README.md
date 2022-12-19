# 1.Spring Mini Homework

### 요구사항

> html,css,js를 넣지않고 PostMan을 사용하여 기능을 확인하자.
<figure>
    <img src="https://res.cloudinary.com/postman/image/upload/t_team_logo/v1629869194/team/2893aede23f01bfcbd2319326bc96a6ed0524eba759745ed6d73405a3a8b67a8">
</figure>


* 전체 게시글 목록 조회 API
* 게시글 작성 API
* 선택한 게시글 조회 API
* 선택한 게시글 수정 API
* 선택한 게시글 삭제 API (비밀번호 확인할 것)

### API명세

| Method | URL | Request                                                                                           | Response                                                                                                                                                                                           |
|--------|---|---------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | /board/list  | {<br>"title": "bad",<br>"username": "kim",<br>"pwd": "123",<br>"contents": "haha!!!"<br>}         | {<br>"createdAt": "2022-12-06T17:42:41.533665",<br>"modifiedAt": "2022-12-06T17:42:41.533665",<br>"id": 1,<br>"title": "bad",<br>"username": "kim",<br>"pwd": "123",<br>"contents": "haha!!!"<br>} |
| GET    | /board/list | -                                                                                                 | {<br>"createdAt": "2022-12-06T19:16:15.619785",<br>"modifiedAt": "2022-12-06T19:16:15.619785",<br>"id": 1,<br>"title": "bad",<br>"username": "kim",<br>"pwd": "123",<br>"contents": "haha!!!"<br>} |
| GET    | /board/list/{id} | -                                                                                                 | {<br>"createdAt": "2022-12-06T19:16:15.619785",<br>"modifiedAt": "2022-12-06T19:16:15.619785",<br>"id": 1,<br>"title": "bad",<br>"username": "kim",<br>"pwd": "123",<br>"contents": "haha!!!"<br>} |
| PUT    | /board/list/{id} | {<br>"title": "update!!",<br>"username": "park - update!!",<br>"contents": "sad - update!!!"<br>} | Long id                                                                                                                                                                                            |
| DELETE | /board/list/{id} | {<br>  "pwd":"pwd"{<br>}                                                                               | Long id                                                                                                                                                                                            |



### 2022.12.06 진행상황

* 전체 게시글 목록 조회 - Done
* 게시글 작성 API - Done
* 선택한 게시글 조회 API - Done
* 선택한 게시글 수정 API - Done
* 선택한 게시글 삭제 API (비밀번호 확인할 것) - Done

### 2022.12.14 요구사항에 따른 진행상황(회원가입만 진행된 상태)
| Function | Method   | URL          | Request                                            | Response   |
|----------|--------|--------------|----------------------------------------------------|----------------|
| 회원가입     | POST | /user/signup | {<br>"username": "String",<br>"pwd": "String"<br>} | |
| 로그인      | POST    | /user/login  | {<br>"username": "String",<br>"pwd": "String"<br>} | |



### 2022.12.14 요구사항에 따른 진행상황

1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.

### 2022.12.15 요구사항에 따른 진행상황

1. 전체 게시글 목록 조회 API - Done
   - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
   - 작성 날짜 기준 내림차순으로 정렬하기
2. 게시글 작성 API - Done
   - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
   - 제목, 작성자명(username), 작성 내용을 저장하고
   - 저장된 게시글을 Client 로 반환하기
3. 선택한 게시글 조회 API - Done
   - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기
     (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
4. 선택한 게시글 수정 API - Done
   - ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
   - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
   - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
5. 선택한 게시글 삭제 API - Done
   - ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
   - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
   - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

### 2022.12.15 요구사항에 따른 진행상황 API 명세( About User )
| Function | Method   | URL         | Request                                            | Response                                         |
|----------|--------|-------------|----------------------------------------------------|--------------------------------------------------|
| 회원가입     | POST | user/signup | {<br>"username": "String",<br>"pwd": "String"<br>} | {<br>"msg": "회원가입 성공",<br>"statusCode": 200<br>} |
| 로그인      | POST    | user/login  | {<br>"username": "String",<br>"pwd": "String"<br>} | {<br>"msg": "로그인 성공",<br>"statusCode": 200<br>}  |

### 2022.12.15 요구사항에 따른 진행상황 API 명세( About Board )
| Function       | Method | URL              | Request                                                                               | Response                                                                                                                                                                                                                                                                        |
|----------------|--------|------------------|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 전체 게시글 조회      | GET    | /board/list      | -                                                                                     | List`<Board>`                                                                                                                                                                                                                                                                   |
| 회원별 게시글 작성(추가) | POST   | /board/list      | JWT Bearer {<br>"title": "String",<br>"username":"String",<br>"contents":"String"<br>} | {<br>"createdAt": "2022-12-16T00:32:45.02639",<br>"modifiedAt": "2022-12-16T00:32:45.02639",<br>"id": Long,<br>"title": String,<br>"username": String,<br>"contents": String,<br>"user": {<br>"id": Long,<br>"username": String,<br>"pwd": String,<br>"boardList": []<br>}<br>} |
| 특정 게시글 조회      | GET    | /board/list/{id} | -                                                                                     | {<br>"createdAt": "2022-12-16T00:32:45.02639",<br>"modifiedAt": "2022-12-16T00:32:45.02639",<br>"id": Long,<br>"title": String,<br>"username": String,<br>"contents": String,<br>"user": {<br>"id": Long,<br>"username": String,<br>"pwd": String,<br>"boardList": []<br>}<br>} |
| 회원별 게시글 수정     | PUT    | /board/list/{id} | JWT Bearer {<br>"title": "String",<br>"username":"String",<br>"contents":"String"<br>}                                                                                      | {<br>"createdAt": "2022-12-16T00:32:45.02639",<br>"modifiedAt": "2022-12-16T00:32:45.02639",<br>"id": Long,<br>"title": String,<br>"username": String,<br>"contents": String,<br>"user": {<br>"id": Long,<br>"username": String,<br>"pwd": String,<br>"boardList": []<br>}<br>} |
| 회원별 게시글 삭제     | DELETE | user/login/{id}   |JWT Bearer | {<br>"msg": "삭제 성공",<br>"statusCode": 200<br>}                                                                                                                                                                                                                                  |

### 2022.12.19 요구사항에 따른 진행상황 
1. 회원 가입 API 회원 권한 부여하기 (ADMIN, USER) 
   - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능 
   - API 명세는 아직 크게 달라진 것이 없어서 따로 첨부하지 않음.
   - 작업 상황 현재 Done!
   - 내일 댓글에 대한 작업을 이어나갈 예정.