# 8. 빈 생명주기 콜백

## 빈 생명주기 콜백

스프링은 간단하게 다음과 같은 라이프사이클을 가진다

**객체 생성 → 의존관계 주입** (생성자 주입은 예외)

초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 함 - 개발자는 의존관계 주입이 완료된 시점을 어떻게 알 수 있을까??

스프링은 의존관계 주입이 완료되면 **스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 다양한 기능을 제공**

스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다 

스프링 빈의 이벤트 라이프 사이클

스프링 컨테이너 생성 → 스프링 빈 생성 → 의존관계 주입 → 초기화 콜백 → 사용 → 소멸 전 콜백 → 스프링 종료

초기화 콜백: 빈이 생성되고 빈의 의존관계 주입이 완료된 후 호출

소멸전 콜백: 빈이 소멸되기 직전에 호출

cf. 객체의 생성과 초기화를 분리하자

생성자: 필수 정보(파라미터)를 받고 메모리를 할당해서 객체를 생성하는 책임을 가짐

초기화: 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는 등 무거운 동작을 수행

그래서 생성과 초기화를 나누는 것이 유지보수 관점에서 좋다

**스프링의 빈 생명주기 콜백 지원**

- 인터페이스 InitializingBean, DisposableBean
- 설정 정보에 초기화 메서드, 종료 메서드 지정
- @Postconstruct, @PreDestroy 애노테이션 지원

## 인터페이스 InitializingBean, DisposableBean

```c
생성자 호출, url = null
NetworkClient.afterPropertiesSet
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지
17:28:09.774 [Test worker] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@77b14724, started on Wed Oct 26 17:28:09 KST 2022
NetworkClient.destroy
close http://hello-spring.dev
```

초기화, 소멸 인터페이스 단점

스프링 전용 인터페이스, 해당 코드가 스프링 전용 인터페이스에 의존

초기화, 소멸 메서드의 이름을 변경할 수 없다

내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다

요즘에는 잘 사용하지 않음

## 빈 등록 초기화, 소멸 메서드

```java
public void init() {
        // 의존관계 주입이 끝나면 호출하겠다
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
```

빈 등록할 때 처리

```java
@Bean(initMethod = "init", destroyMethod = "close")
        public  NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
```

```c
생성자 호출, url = null
NetworkClient.init
connect: http://hello-spring.dev
call: http://hello-spring.dev message = 초기화 연결 메시지
17:42:03.165 [Test worker] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@77b14724, started on Wed Oct 26 17:42:03 KST 2022
NetworkClient.close
close http://hello-spring.dev
```

설정 정보 사용 특징

- 메서드 이름을 자유롭게 줄 수 있음
- 스프링 빈이 스프링 코드에 의존하지 않음
- 코드가 아니라 설정 정보 사용 → 코드를 고칠 수 없는 외부 라이브러리에도 적용 가능

종료 메서드 추론

- @Bean의 destoryMethod의 특별한 기능
- 라이브러리는 대부분 close, shutdown 이라는 이름의 종료 메서드 사용
- @Bean의 destroyMethod는 기본값이 `(inferred)`로 등록
- 추론 기능은 `close`, `shutdown` 라는 이름으 메서드를 자동으로 호출
- 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작함
- 추론 기능을 사용하기 싫으면 `destroyMethod=””` 사용

## 애노테이션 @PostConstruct, @PreDestroy

최신 스프링에서 가장 권장

스프링이 아닌 다른 컨테이너에서도 동작 (javax)

컴포넌트 스캔과 잘 어울린다

단점: 외부 라이브러리에 사용할 수 없음