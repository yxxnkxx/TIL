## SOLID

### SRP: 단일 책임 원칙(single responsibility principle)

**한 클래스는 하나의 책임만 가져야 한다**

하나의 책임은 모호한 개념이다.

SRP를 판단할 수 있는 중요한 기준은 **변경** - 변경이 있을 때 파급 효과가 적으면 SRP를 잘 따른 것

한 클래스가 여러 책임을 가지고 있으면 클래스 내부 함수끼리 결합도가 높아져 하나의 기능 변경에도 파급 효과가 클 수 있다.

### OCP: 개방-폐쇄 원칙(Open/closed principle)

가장 중요한 원칙,

소프트웨어 요소는 **확장에는 열려 있으나 변경에는 닫혀 있어야 한다**

**다형성**을 활용하여 이해 - 인터페이스를 구현하는 클래스를 하나 만들어서 새로운 기능을 구현_(확장)_, 새로운 클래스를 만드는 것은 변경이 아님_(인터페이스는 고정)_

**역할과 구현의 분리**

```
public interface Service {
    public void doSomething();
}

public class ServiceImpl implements Service {
    public void doSomething() {
        // do something
    }
}

public class Test {
    private Service service = new ServiceImpl();
}
```

위와 같은 코드는 다형성을 지켜서 구현한 코드다.

`Service`라는 인터페이스를 구현하고, `ServiceImpl`은 인터페이스를 구현한 구체 클래스다.

-   문제점

`Test` class에서 `Service`를 사용하기 위해서는 `ServiceImpl`이라는 구현 클래스를 직접 선택해야 한다.

위 코드는 다형성을 준수했지만, 구현 객체(ServiceImpl)을 변경하기 위해서는 Test 클래스에서 코드를 직접 변경해야 한다  
OCP 원칙 위반

→ **객체를 생성하고 연관관계를 맺어주는 별도의 조립, 설정자가 필요함**(스프링 컨테이너)

### LSP: 리스코프 치환 원칙(Liskov substitution principle)

**프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다**

다형성에서 하위 클래스는 **인터페이스** 규약을 다 지켜야 한다

인터페이스를 구현한 구현체를 믿고 사용하려면 이 원칙이 필요함

단순히 컴파일에 성공하는 것을 넘어서는 이야기

ex) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능 (뒤로 가면 LSP 위반)

### ISP: 인터페이스 분리 원칙(Interface segregation principle)

**특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다**

자동차 인터페이스 → 운전 인터페이스, 정비 인터페이스로 분리

사용자 클라이언트 → 운전자 클라이언트, 정비사 클라이언트로 분리

인터페이스가 명확, 대체 가능성이 높아짐

### DIP: 의존 관계 역전 원칙 (Dependency inversion principle)

프로그래머는 추상화에 의존해야지, 구체화에 의존하면 안된다.

구현 클래스에 의존하지 말고 **인터페이스에 의존하라는 뜻**

**역할(Role)에 의존**해야 한다.

```
public interface Service {
    public void doSomething();
}

public class ServiceImpl implements Service {
    public void doSomething() {
        // do something
    }
}

public class Test {
    private Service service = new ServiceImpl();
}
```

→ 인터페이스에 의존하지만, 구현 클래스도 동시에 의존, DIP 위반

**다형성만으로는 DIP를 지킬 수 없음**

이러한 이유로 SOLID 원칙을 준수하기 위해 Spring Container를 사용한다.
