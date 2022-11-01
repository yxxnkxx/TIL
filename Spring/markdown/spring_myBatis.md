# MyBatis

## 1. pom.xml에 의존성 추가

```xml
		<!-- Datasource -->
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-dbcp2</artifactId>
			<version>2.8.0</version>
		</dependency>

		<!-- mysql 연결 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.22</version>
		</dependency>

		<!-- mybatis 연동 -->
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.5.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>2.0.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
```

## 2. applicationContext.xml

```xml
	<bean id="dataSource"
		class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName"
			value="com.mysql.cj.jdbc.Driver"></property>
		<property name="url"
			value="jdbc:mysql://localhost:3306/{db이름}?serverTimezone=UTC"></property>
		<property name="username" value="이름"></property>
		<property name="password" value="비밀번호"></property>
	</bean>

	<!-- sqlSessionFactory를 등록-->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<!-- mapper.xml 경로 -->
		<property name="mapperLocations" value="classpath:mappers/*Mapper.xml"/>
		<!-- DTO들의 기본 패키지 등록 -->
		<property name="typeAliasesPackage" value="dao가 위치한 패키지"/>
	
	</bean>
	

	<!-- dao interface들의 위치를 지정 -->
	<mybatis-spring:scan base-package="dao가 위치한 패키지"/>

```

- dataSource 빈 등록
    - driverClassName, url, username, password
- sqlSessionFactory 등록
    - dataSource 연결
    - mapperLocations - mapper.xml의 경로 지정
    - typeAliasesPackage - dto들의 기본 패키지 등록
- mybatis-spring:scan
    - Dao 패키지 스캔

## 3. Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="Dao의 위치+클래스 이름">
	
	<!-- 쿼리문-->

</mapper>
```

**mapper namespace**

SQL mapper 파일은 루트 엘리먼트 <mapper>를 작성하는 것으로 시작한다.

<mapper>의 namespace 속성은 자바의 패키지처럼 여러개의 SQL문을 묶는 용도로 사용한다.

mapper 파일에 작성하는 모든 SQL문은 <mapper> 하위에 놓여야 한다.

[https://atoz-develop.tistory.com/entry/MyBatis-설정-파일-SQL-Mapper-작성-방법](https://atoz-develop.tistory.com/entry/MyBatis-%EC%84%A4%EC%A0%95-%ED%8C%8C%EC%9D%BC-SQL-Mapper-%EC%9E%91%EC%84%B1-%EB%B0%A9%EB%B2%95)

SQL 문법에 따라 각 태그 안에 쿼리문 작성

- id: Dao에서 함수명과 일치, 각 SQL문을 구분하는 것
- parameterType: 입력되는 파라미터의 종류
- resultType: SELECT문에서의 실행 결과를 담을 객체 - 클래스 이름
- resultMap: 객체의 변수명과 SQL에서의 column명이 다를 경우 resultMap을 따로 정의하여 사용, resultType과 resultMap 중 하나만 사용한다

**mapper내부 쿼리문**

```xml
	<insert id="insertProduct" parameterType="Product" >
		INSERT INTO product
		VALUES (#{code}, #{name}, #{price}, #{quantity}, #{brand});
	</insert>
	
	
	<delete id="deleteProduct" parameterType="String">
		DELETE FROM product
		WHERE code = #{code}
	</delete>

	<update id="updateProduct" parameterType="Product">
		UPDATE product
		SET 변수명 = #{변수명}
	</update>

	<select id="selectAllProduct" resultType="Product">
		SELECT * FROM product
	</select>

```

cf. #{}과 ${}의 차이

[https://mine-it-record.tistory.com/300](https://mine-it-record.tistory.com/300)

| #{} | ${} |
| --- | --- |
| PreparedStatement | Statement |
| ?로 전달 | 특정 값으로 전달 |
| 동적 | 정적 |
| 사용자의 입력을 받거나 데이터가 많은 경우 | 자주 바뀌지 않는 경우 |

 ㅇ