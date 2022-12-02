# DB

# 데이터베이스

여러 사람이 공유하고 사용할 목적으로 통합 관리되는 정보의 집합

논리적으로 연관된 하나 이상의 자료의 모음으로 그 내용을 고도로 구조화함으로써 검색과 갱신의 효율화를 꾀한 것

몇 개의 자료 파일을 조직적으로 통합하여 자료 항목의 중복을 없애고 자료를 구조화하여 기억시켜 놓은 자료의 집합체

<h1> DBMS </h1>

<details>
<summary>DBMS</summary> 
<div markdown="1">

## DBMS(Database Management System)

데이터베이스 관리 프로그램

데이터베이스 조작 인터페이스 제공 (데이터의 무결성 보장)

효율적인 데이터 관리 기능 제공

데이터베이스 구축 기능 제공

데이터 복구, 사용자 권한 부여, 유지보수 기능 제공

</div>
</details>

<h1> SQL</h1>

<details>
<summary>SQL</summary> 
<div markdown="1">

# 관계형 데이터 베이스

테이블 기반의 데이터베이스

데이터를 테이블 단위로 관리

- 하나의 데이터(record)는 여러 속성(Attribute)을 가진다
- 데이터 중복을 최소화
- 테이블 간의 관계를 이용하여 필요한 데이터 검색 가능

테이블(Table)

- 실제 데이터가 저장되는 곳
- 행과 열의 2차원 구조를 가진 데이터 저장 장소

**관계형 데이터 베이스 관리 시스템 (Relational Database Management System)**

# SQL

Structured Query Language

관계형 데이터 베이스에서 데이터 조작과 데이터 정의를 위해 사용하는 언어

- 데이터 조회
- 데이터 삽입 삭제 수정
- DB Object 생성 및 변경 삭제
- DB 사용자 생성 및 삭제, 권한 제어

## 특징

배우고 사용하기 쉽다

대소문자를 구별하지 않는다 (데이터의 대소문자는 구분)

절차적인 언어가 아니라 선언적 언어

DBMS에 종속적이지 않다

- DML(Data Manipulation Language): 데이터 조작 언어
  - 데이터베이스에서 데이터를 조작하거나 조회할 때 사용
  - 테이블의 레코드를 CRUD (Create, Read, Update, Delete)
- DDL(Data Definition Language): 데이터 정의 언어
  - 데이터 베이스 객체(table, view, user, index)의 구조를 정의
- TCL(Transaction Control Language): 트랜잭션 제어 언어
  - 트랜잭션 단위로 실행한 명령문을 적용하거나 취소
- DCL(Data Control Language): 데이터 제어 언어
  - Database, Table 접근 권한이나 CRUD 권한 정의
  - 특정 사용자에게 테이블의 검색권한 부여/금지

## 종류

| 분류 | 문장(키워드)    | 설명                                                                                                                         |
| ---- | --------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| DML  | SELECT          | 데이터 조회                                                                                                                  |
|      | INSERT          | 테이블에 새 행을 입력                                                                                                        |
|      | UPDATE          | 기존 행을 변경                                                                                                               |
|      | DELETE          | 테이블에서 행을 삭제                                                                                                         |
| DDL  | CREATE          | 테이블 등 데이터 객체 생성                                                                                                   |
|      | ALTER           | 테이블 등 데이터 객체 변경                                                                                                   |
|      | DROP            | 테이블 등 데이터 객체 삭제                                                                                                   |
|      | RENAME          | 테이블 등 데이터 객체의 이름을 변경                                                                                          |
| TCL  | COMMIT ROLLBACK | DML문이 변경한 내용을 관리 변경사항을 저장(COMMIT)하거나 취소(ROLLBACK)할 때 사용 DML변경 내용은 트랜잭션 단위로 그룹화 가능 |
| DCL  | GRANT           | 데이터베이스 접근권한 부여                                                                                                   |
|      | REVOKE          | 데이터베이스 접근권한 삭제                                                                                                   |

</div>
</details>

<h1> JDBC</h1>

<details>
<summary>JDBC</summary> 
<div markdown="1">

# JDBC

[https://shs2810.tistory.com/18](https://shs2810.tistory.com/18)

[https://devlog-wjdrbs96.tistory.com/139](https://devlog-wjdrbs96.tistory.com/139)

JDBC = Java Database Connectivity

자바와 데이터베이스를 연결해서 데이터를 주고 받게 해주는 프로그래밍 인터페이스

각 DBMS에 맞는 드라이버가 필요함!

## 작업 순서

1. JDBC 사용 (Driver Loading)
2. DB 연결 (Connection 생성)
3. SQL 준비 및 실행
4. DB 연결 해제 (종료)

### 1. JDBC 사용 (Driver Loading)

```java
Class.forName("com.mysql.Jdbc.Driver");
```

### 2. DB 연결 (connection)

```java
String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";

public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
```

[localhost:3306](http://localhost:3306) = 로컬 환경에서 MySQL의 포트 번호

board : 가져오려는 schema 이름

username과 password는 MySQL에서의 username과 password

이 과정을 통해 연결 가능

### 3. SQL 준비 및 실행

1. Statement + SELECT

```java
Statement stmt = con.createStatement();

String sql = "SELECT * from board";
ResultSet result = stmt.executeQuery(sql);
```

```java
conn = util.getConnection();
stmt = conn.createStatement();
rs = stmt.executeQuery(sql);

while (rs.next()) {
		Board board = new Board();
		board.setId(rs.getInt("id"));
		board.setTitle(rs.getString("title"));
}
```

`rs.getString`, `rs.getInt` = column의 index 또는 column의 label 이름으로 데이터를 가져올 수 있음

`rs.next()` : DB의 row 한 줄을 불러옴, false일 경우 더 이상 불러올 row가 없다는 뜻

1. PreparedStatement + UPDATE / DELETE / INSERT

예시는 insert

```java
String sql = "INSERT INTO board (id, title, content) VALUES (?,?,?)";
Connection conn = util.getConnection();

PreparedStatement pstmt = conn.prepareStatement(sql);

pstmt.setString(1, board.getId());
pstmt.setString(2, board.title());
pstmt.setString(3, board.getContent());

int result = pstmt.executeUpdate(); // 영향 받은 row의 개수
System.out.println(result);
```

- `pstmt.setString(parameterIndex, x)` = ‘?’의 순서에 따라 x값을 할당해줌
- `executeUpdate()` : 테이블의 내용을 변경하는 문장에 사용(create, drop, insert, delete, update)
  - 영향받은 row의 개수를 반환, 아무 것도 반환하지 않으면 0
    ```java
    either (1) the row count for SQL Data Manipulation Language (DML) statements
    		or (2) 0 for SQL statements that return nothing
    ```

### [참고] Statement와 PreparedStatement의 차이?

[https://flatsun.tistory.com/386](https://flatsun.tistory.com/386)

[https://devbox.tistory.com/entry/Comporison](https://devbox.tistory.com/entry/Comporison)

- Statement

```java
Statement stmt = con.createStatement();
ResultSet result = stmt.executeQuery(sql);
```

- createStatement 메소드에 파라미터가 없다.
- 실행 전까지는 무슨 쿼리를 실행하는지 알 수 없음 (executeQuery의 매개변수로 sql을 넣음)
- 쿼리문을 실행할 때마다 생성하며 반복 실행되는 경우에 효율이 떨어짐 (수행하는 과정에서 매번 컴파일)
- 쿼리문을 프로그램 외부에서 작성한 뒤 내부에서 실행하는 SQL Injection 공격에 취약
- 전달되는 SQL문은 완성된 형태 → 한눈에 파악하기 쉬움

- PreparedStatement

```java
String sql = "SELECT * FROM board WHERE id =?"; // ? -> 미완성
pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, id);
rs = pstmt.executeQuery(); // 실행 시 매개변수 x
```

- ‘?’ = Bind 변수, 값을 가변적으로 바꿀 때 사용
- 쿼리문을 미리 생성함 (`prepareStatement()` 메소드를 통해)
- 쿼리를 파라미터에 넣지 않고 수행 → 실행시마다 쿼리를 생성하지 않아서 속도가 빠름
- Bind 변수로 인해 SQL문이 완성된 형태가 아님 → 한눈에 파악하기 어려움

### 4. DB 연결 해제

Connection, Statement, ResultSet에 대해 close

```java
public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

```

```java
/**
	 * 사용한 리소스들을 정리한다. Connection, Statement, PreparedStatement, ResultSet 모두
	 * AutoCloseable 타입 ... 을 이용하므로 필요에 한번에 정리가능
	 *
	 * @param autoCloseables
	 */
	public void close(AutoCloseable... autoCloseables) {
		for (AutoCloseable ac : autoCloseables) {
			if (ac != null) {
				try {
					ac.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
```

</div>
</details>

---

<h1> 데이터베이스 개론</h1>

- [데이터베이스 기본 개념](./db%EA%B8%B0%EB%B3%B8%EA%B0%9C%EB%85%90.md)

- [데이터베이스 관리 시스템](./%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4%EA%B4%80%EB%A6%AC%EC%8B%9C%EC%8A%A4%ED%85%9C.md)

- [DBMS](./dbms.md)

- [데이터 모델링](./datamodel.md)

- [관계 데이터 모델](./%EA%B4%80%EA%B3%84%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%AA%A8%EB%8D%B8.md)

- [관계 데이터 연산](./%EA%B4%80%EA%B3%84%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%97%B0%EC%82%B0.md)

- [정규화](./09%20%EC%A0%95%EA%B7%9C%ED%99%94/09%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%80%E1%85%B2%E1%84%92%E1%85%AA%20e6f7d5209f274ab4a623f8eade1665f4.md)

- [회복과 병행제어](./10%20%ED%9A%8C%EB%B3%B5%EA%B3%BC%20%EB%B3%91%ED%96%89%EC%A0%9C%EC%96%B4/10%20%ED%9A%8C%EB%B3%B5%EA%B3%BC%20%EB%B3%91%ED%96%89%EC%A0%9C%EC%96%B4.md)

- [보안과 권한관리](./11%20%EB%B3%B4%EC%95%88%EA%B3%BC%20%EA%B6%8C%ED%95%9C%EA%B4%80%EB%A6%AC.md)
