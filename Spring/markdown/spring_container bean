# 4. 스프링 컨테이너와 빈

 

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

`ApplicationContext` : 스프링 컨테이너, 인터페이스

스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다

`@Bean` 이 붙은 애들을 하나씩 호출 → 메서드 이름을 빈 이름으로, 반환하는 객체를 빈 객체로 등록

**빈 이름은 항상 다른 이름을 부여**

스프링 빈 의존관계 설정

- 설정 정보(AppcConfig)를 참고해서 의존관계 주입

스프링은 빈을 생성하고 의존관계를 주입하는 단계가 나누어져 있음

→ 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한꺼번에 처리

---

## 컨테이너에 등록된 모든 빈 조회

```java
@Test
    @DisplayName("빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + " obejct = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + " obejct = " + bean);

            }
        }
```

## 스프링 빈 조회 - 기본

```java
ac.getBean(빈이름, 타입)
```

---

## 스프링 빈 조회 상속관계

부모 타입으로 조회하면 자식 타입도 함께 조회된다

**Object로 조회하면 모든 스프링 빈을 조회**

---

## BeanFactory와 ApplicationContext

- B**eanFactory**
    - 스프링 컨테이너의 최상위 인터페이스
    - 스프링 빈을 관리하고 조회하는 역할
    - getBean()을 제공
- **ApplicationContext**
    - BeanFactory 기능을 모두 상속 받아서 제공
    - 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능 + 부가기능이 필요함
    - ApplicationContext의 분리 기능 (BeanFactory와의 차이)
        - 메시지소스를 활용한 국제화 기능
        - 환경 변수: 로컬, 개발, 운영을 구분해서 처리
        - 애플리케이션 이벤트
        - 편리한 리소스 조회: 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

BeanFactory를 직접 사용할 일은 거의 없다

---

## 다양한 설정 형식 지원 - 자바 코드, xml

### 애노테이션 기반 자바코드 설정 사용

`new AnnotationConfigApplicationContext(AppConfig.class)`

---

## 스프링 빈 설정 메타 정보 - BeanDefinition

역할과 구현을 개념적으로 나눈 것

BeanDefinition : 빈 설정 메타 정보

스프링 컨테이너는 메타 정보를 기반으로 스프링 빈을 생성

`AnnotationConfigApplicationContext`: `AnnotatedBeanDefinitionReader`를 사용해서 `AppConfig.class`를 읽고 `BeanDefinition`을 생성한다

`GenericXmlApplicationContext`: `XmlBeanDefinitionReader`를 사용해서 `appConfig.xml`설정 정보를 읽고 `BeanDefinition`을 생성한다