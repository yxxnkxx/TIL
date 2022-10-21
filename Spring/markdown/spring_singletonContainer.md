# 5. 싱글톤 컨테이너

## 웹 애플리케이션과 싱글톤

대부분 스프링 애플리케이션은 웹 애플리케이션

웹 애플리케이션은 보통 여러 고객이 동시에 요청을 한다

스프링 없는 순수한 DI 컨테이너 AppConfig: 요청을 할 때마다 객체가 새로 생성

해결 방안: 객체가 1개만 생성, 생성된 객체 인스턴스를 공유해서 쓰기

## 싱글톤 패턴

객체 인스턴스를 2개 이상 생성하지 못하도록 막기

```java
package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
        
    }

    public static SingletonService getInstance() {
        return instance;

    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
```

**문제점**

- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감
- 의존관계상 클라이언트가 구체 클래스에 의존 → DIP를 위반
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높음
- 테스트하기 어려움
- 내부 속성을 변경하거나 초기화하기 어려움
- private 생성자로 자식 클래스를 만들기 어려움
- 유연성이 떨어짐
- 안티패턴으로 불리기도 한다

---

## 싱글톤 컨테이너

스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리함

스프링 컨테이너는 싱글톤 컨테이너 역할을 함 -= 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 함

→ 싱글톤 패턴의 단점 해결

- 싱글톤 패턴을 위한 지저분한 코드x
- DIP, OCP, 테스트, private 메서드 등 필요하지 않음

---

## 싱글톤 방식의 주의점

객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유

→ 상태를 유지(stateful)하게 설계하면 안됨

무상태(stateless)로 설계해야 함

- 특정 클라이언트에 의존적인 필드가 있으면 안됨
- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안됨
- 가급적 읽기만 가능해야 한다
- 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 함

---

## @Configuration과 싱글톤

```java
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
```

메서드가 매번 call되지 않음 → `memberRepository`는 처음에 생성할 때만 호출

---

## @Configuration과 바이트코드 조작의 마법

스프링 컨테이너는 싱글톤 레지스트리

스프링 빈이 싱글톤이 되도록 보장해야 한다 → 3번 호출되어야 하지만 1번만 호출됨

스프링: 클래스의 바이트코드를 조작하는 라이브러리

**Appconfig의 class 정보**

```java
bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$5fd9d40
```

순수한 클래스라면 `class hello.core.AppConfig`

스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것

`@Bean`이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어짐 → 싱글톤 보장

`@Configuration`을 삭제하면  `class.hello.core.AppConfig` → 순수한 클래스로 등록됨

싱글톤 방식이 깨짐 

- `@Bean`만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않음
- `@Configuration`이 없으면 메서드를 직접 호출할 때 싱글톤을 보장하지 않음