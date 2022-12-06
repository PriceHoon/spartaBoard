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

