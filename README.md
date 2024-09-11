# kmong-dvdrental

### **데이터 베이스 테이블 명세**

- 기존의 것과 거의 비슷하게 갔습니다.
- 렌탈 상세 테이블에 '반환 일자' 추가
- 아래 DDL 구문을 그대로 실행시키면 테이블이 생성됩니다. 

**dvd 테이블**

CREATE TABLE `dvd23` (  `id` int NOT NULL AUTO_INCREMENT,  `title` varchar(255) NOT NULL,  `genre` varchar(100) DEFAULT NULL,  `lead_actor` varchar(255) DEFAULT NULL,  `is_rented` tinyint(1) DEFAULT '0',  PRIMARY KEY (`id`),  UNIQUE KEY `idx_title` (`title`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



**회원 테이블**

CREATE TABLE `member23` (
  `id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



**렌탈(대출) 테이블**

CREATE TABLE `rental23` (
  `rental_id` int NOT NULL AUTO_INCREMENT,
  `member_id` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rental_date` datetime NOT NULL,
  PRIMARY KEY (`rental_id`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



**렌탈(대출) 상세 테이블**

CREATE TABLE `rental_detail23` (
  `rental_item_id` int NOT NULL AUTO_INCREMENT,
  `rental_id` int NOT NULL,
  `dvd_id` int NOT NULL,
  `is_returned` tinyint(1) DEFAULT "0",
  `return_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rental_item_id`),
  KEY `rental_id` (`rental_id`),
  KEY `dvd_id` (`dvd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



### **DB 설정 정보**

src/main/java/config/DatabaseConnection.java 에서 설정에 맞게 수정하신 후 maven build 진행하시면 됩니다.

아래는 현재 파일에 있는 값입니다. 

(접속 id "root" , 패스워드 "0000") 패스워드가 없을시 "" 으로 수정 후 maven build 하시면 됩니다. 

```java
public class DatabaseConnection {
	private static final String DATABASE_NAME = "rental_db";
	private static final String PROPERTIES = "?characterEncoding=utf-8";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPERTIES;
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    
  (생략)
}
```



### **실행 방법**

dvdrental.war 파일을  tomcat 서버의 webapps 폴더 하위에 위치시킨후 서버 구동시키면 됩니다.

기본 url은 http://localhost:8080/dvdrental 입니다.

<p style="color: red;">첨부된 war파일로 바로 실행하려면 mysql 계정 정보가 아이디 "root", 패스워드 0000 이여야 됩니다. 해당 WAR파일은 개발산책 전용 MYSQL 계정을 기반으로 만들어진것이라 고객님의 계정정보와 다를수 있습니다.</p>

<p style="color: red;">계정정보가 다르다면, DatabaseConnection.java  파일을 고객님의 mysql 계정으로 수정 후 maven build를 진행해 주시면 됩니다.</p>



### **eclipse에서 maven 빌드방법**

##### 1. Maven 프로젝트 확인

먼저, Maven 프로젝트가 설정되어 있어야 합니다. 프로젝트가 Maven 프로젝트로 설정되어 있지 않다면, Eclipse에서 **Maven**으로 변환해야 합니다.

- 프로젝트를 마우스 오른쪽 클릭 후, **Configure -> Convert to Maven Project**를 선택합니다.



##### 2. **`pom.xml` 파일 설정 확인**

`pom.xml` 파일에서 프로젝트의 패키징 방식이 `war`로 설정되어 있는지 확인해야 합니다.

```xml
<packaging>war</packaging>
```



##### 3. **Maven Build 실행**

Maven을 사용하여 WAR 파일을 생성하는 방법입니다.

1. **프로젝트 선택**: Eclipse 프로젝트 탐색기에서 Maven 프로젝트를 마우스 오른쪽 클릭합니다.
2. **Run As -> Maven Build...**를 선택합니다.
3. **Goals**에 `clean package`를 입력합니다.
   - `clean`은 이전에 생성된 파일들을 삭제하고 새로 빌드를 시작하는 명령입니다.
   - `package`는 Maven이 프로젝트를 빌드하여 `WAR` 파일을 생성하는 명령입니다.
4. **Run** 버튼을 클릭합니다.

##### 4. **WAR 파일 확인**

빌드가 성공적으로 완료되면, WAR 파일이 다음 경로에 생성됩니다.

- **`프로젝트 경로/target/`** 폴더에 생성된 `.war` 파일을 확인할 수 있습니다. (안뜰경우 F5버튼으로 새로고침)





### **개발 정보**

- 언어 : java17
- 서버 : tomcat 9
- 빌드 : maven 
- 데이터베이스 : mysql
- IDE : Eclipse
- 구현 : java dao, dto 사용, 페이지는 jsp



### **개발 사항**

**대출(렌트) 이력 페이지** **(url : /dvdrental/rent)**

- 렌트 이력 테이블 출력
  - 체크박스, 대출번호, 일시 ,dvd 제목, 대출자 ID, 반납여부, 반납일

- 반납 기능
  - 렌트리스트에서 '미반환' 상태인것들만 체크박스 체크 가능
  - 체크 된 미반환 건들에 한해 '반환' 버튼 클릭시 반환처리


**메인페이지 (url : /dvdrental)**

- 현재 모든 dvd 현황 테이블 출력
  - 체크박스, dvd 타이틀, 장르, 주연배우, 대여상태

- dvd 제목 검색 기능 (like 검색)
- 회원 id 입력 및 체크박스 입력 후 대여 버튼을 클릭하여 대여 상태로 변경
  - 체크 박스 미선택시 alert 메시지
  - 회원 id가 미존재시 alert 메시지


**회원 관리 페이지**  **(url : /dvdrental/member)**

- 현재 회원 리스트 테이블 출력
  - 체크박스, 아이디, 이름, 이메일

- 회원 등록 기능
  - 클릭시 회원 등록페이지로 이동

- 삭제 기능
  - 체크박스 선택후 클릭시 회원 delete
  - 체크박스 미선택시 alert 메시지


**회원 등록 페이지** **(url : /dvdrental/registerMember)**

- 회원 등록 form 
  - 회원아이디
    - 30자 제한
  - 이름
  - e메일 
    - 이메일 형식 체크 ('@' 가 들어가 있는지)



**DVD 관리 페이지** **(url : /dvdrental/dvd)**

- dvd 리스트 테이블 출력
  - 체크박스, 타이틀, 장르, 주연배우
- dvd 등록 버튼
  - dvd 등록페이지 이동
- dvd 삭제 버튼
  - 체크박스 선택후 삭제
  - 미선택시 alert 메시지



**DVD 등록 페이지**

- DVD 등록 form 
  - 타이틀
    - 30자 제한
  - 장르
    - 20자 제한
  - 주연배우
    - 100자 제한



문의사항은 언제든지 편하게 카카오톡이나 크몽으로 문의 주시면 됩니다. 감사합니다 ^^