# Memo

회원 기능과 이미지 첨부가 가능한 메모 CRUD를 구현한 Spring Boot 웹 프로젝트입니다.

## 주요 기능

### 회원
- 회원가입
- 비밀번호 암호화
- 로그인 / 로그아웃
- 로그인 상태 확인을 위한 인터셉터 적용

### 메모
- 메모 작성
- 메모 목록 조회
- 메모 수정
- 메모 삭제
- 이미지 파일 첨부
- 메모 삭제 시 연결된 이미지 파일 함께 삭제

## 기술 스택

- Java 17
- Spring Boot 3.0.2
- Spring MVC
- MyBatis 3.0.0
- MySQL
- JSP / JSTL
- Gradle

## 실행 방법

Java 17과 MySQL을 준비하고 프로젝트의 DB 및 파일 저장 경로 설정을 로컬 환경에 맞게 구성한 뒤 실행합니다.

```bash
./gradlew bootRun
```

Windows:

```bash
gradlew.bat bootRun
```

## 목적

회원 인증부터 DB CRUD, 파일 업로드, 인터셉터까지 일반적인 웹 애플리케이션의 기본 기능을 직접 구현하며 Spring MVC와 MyBatis의 동작 방식을 학습하기 위해 만든 프로젝트입니다.
