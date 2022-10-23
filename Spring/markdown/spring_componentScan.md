# 6. 컴포넌트 스캔

컴포넌트 스캔을 사용하려면 먼저 `@ComponentScan`을 설정 정보에 붙여주면 된다

참고: `@Configuration`이 붙은 설정 정보도 자동으로 등록되기 때문에 `excludeFilters`를 사용해서 컴포넌트 스캔 대상에서 제외

`@ComponentScan`은 `@Component`가 붙은 모든 클래스를 스프링 빈으로 등록

스프링 빈의 기본 이름은 클래스명을 사용하되, 맨 앞글자만 소문자를 사용

`MemberServiceImpl` 클래스 → `memberServiceImpl`

빈 이름 직접 지정도 가능 → `@Component(”memberServiceImpl”)`

---

`@Autowired` 의존관계 자동 주입

생성자에 `@Autowired`를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입

이때 기본 조회 전략 = **타입이 같은 빈을 찾아서 주입**

→ `getBean(MemberRepository.class)`와 같다고 이해하면 됨

---

## 탐색 위치와 기본 스캔 대상

모든 패키지에서 스캔하려면 시간이 오래 걸림 → `basePackages` 지정

```java
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
```

```java
14:56:31.973 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberServiceImpl'
14:56:31.986 [Test worker] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memoryMemberRepository'
```

2개만 적용됨

`basePackageClasses` : 지정한 클래스가 속한 패키지가 `basePackages`가 됨

지정하지 않으면 `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨

권장하는 방법: 패키지 위치를 지정하지 않고 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것

---

### 컴포넌트 스캔 기본 대상

@Component 뿐만 아니라 다음 내용도 추가로 대상에 포함

- @Component: 컴포넌트 스캔
- @Controller: 스프링 MVC 컨트롤러
- @Service: 스프링 비즈니스 로직
- @Repository: 스프링 데이터 접근 계층에서 사용
- @Configuration: 스프링 설정 정보에서 사용

참고: 애노테이션에는 상속관계가 없음, 애노테이션이 특정 애노테이션을 들고 있는 것을 인식할 수 있는건 자바 언어x, 스프링이 지원하는 기능

스프링의 부가 기능 수행

- @Controller: 스프링 MVC 컨트롤러로 인식
- @Service: 특별한 처리x, 개발자들이 핵심 비즈니스 로직이 여기 있겠구나라고 인식
- @Repository: 스프링 데이터 접근 계층으로 인식, 데이터 계층의 예외를 스프링 예외로 변환
- @Configuration: 스프링 설정 정보로 인식, 스프링 빈이 싱글톤을 유지하도록 추가 처리

---

## Filter

FilterType 옵션

- **ANNOTATION**: 기본값, 애노테이션을 인식해서 동작
    - org.example.SomeAnnotation
- **ASSIGNABLE_TYPE**: 지정한 타입과 자식 타입을 인식해서 동작
    - org.example.SomeClass
- **ASPECTJ**: AspectJ 패턴 사용
    - org.example..*Service+
- **REGEX**: 정규 표현식
    - org\.example\.Default.*
- **CUSTOM**: TypeFilter라는 인터페이스를 구현해서 처리
    - org.example.MyTypeFilter
    

---

## 중복 등록과 충돌

자동 빈 등록 vs 자동 빈 등록

컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록될 때 이름이 같은 경우 오류 발생

`ConflictingBeanDefinitionException`

수동 빈 등록 vs 자동 빈 등록

이 경우 수동 빈 등록이 우선권을 가짐 → 수동 빈이 자동 빈을 오버라이딩

```java
Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing [Generic bean: class [hello.core.member.MemoryMemberRepository];
```

설정이 꼬여서 이런 결과가 만들어지며 잡기 어려운 버그 

→ 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꿈