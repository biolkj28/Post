# Post
JWT,Spring Security, Mockito 사용 게시판 페이지 구현

[구현 기능]

[프론트]
1. 댓글 UI 추가 
2. JWT 토큰 쿠키저장 및 로그인 사, ajax 헤더에 jwt 토큰 추가 이벤트 추가 
3. 로그아웃 시 JWT 토큰 삭제, 로그인 페이지 이동 이벤트 구현
4. 댓글, 수정, 삭제 버튼 이벤트 구현
5. 수정 모달 추가 및 이벤트 구현
6. 수정 시 변화 없을 시 alert 표시 후 메서드 종료 기능 구현


[서버]

[로그인]

1. JWT 필터에서 token 이 존재 하지 않으면 response 에 로그인 필요 메시지반환 기능 추가
2. 로그인, 회원가입 기능 구현
3. AuthenticationFailureHandler 상속받아 FormLoginFailer 구현

[댓글]

4. 댓글 crud 추가 
5. 조회 제외, crud user 권한있어야 접근 할 수 있도록 구현
6. 회원 가입 시 validate 메서드 구현
7. [Repository]게시글 Id 값으로 최신 날짜 댓글 리스트 가져오는 메서드 추가 
8. [Repository] user id 와 댓글 Id로 삭제하는 메서드추가 

[게시판]

7. 게시판 컨트롤러 권한 설정
8. 작성 유저만 댓글 및 게시글 변경 하도록 수정


[공통]

각 공통 오류 메시지 Enum 생성
