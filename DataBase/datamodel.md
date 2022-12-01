# 04 데이터 모델링

## 01 데이터 모델링과 데이터 모델의 개념

- 데이터 모델링: 현실 세계에 존재하는 데이터를 컴퓨터 세계의 데이터베이스 옮기는 변환 과정
- 추상화(abstraction): 데이터베이스에 저장하여 관리할 만한 가치가 있는 중요 데이터를 찾아내는 과정

현실 세계 → (개념적 모델링) → 개념 세계 → (논리적 모델링) → 컴퓨터 세계

- 개념적 모델링: 현실 세계에서 중요 데이터를 추출하여 개념 세계로 옮기는 작업
- 논리적 모델링: 개념 세계의 데이터를 데이터베이스에 저장할 구조를 결정하고 이 구조를 표현하는 작업
- 데이터 모델: 데이터 모델링의 결과물을 표현하는 도구
  - 데이터 구조
    - 개념적 데이터 모델-현실 세계를 개념 세계로 추상화했을 때 어떤 요소로 이루어져 있는지를 표현하는 개념적 구조
    - 논리적 데이터 모델-데이터를 어떤 모습으로 저장할 것인지를 표현하는 논리 구조
      자주 변하지 않고 정적
  - 연산: 데이터 구조에 따라 개념 세계나 컴퓨터 세계에서 실제로 표현된 값을 처리하는 작업
    값이 연산에 의해 변경될 수 있으므로 동적이다
  - 제약조건: 데이터 무결성 유지
    - 구조적 측면의 제약 사항
    - 의미적 측면의 제약 사항

## 02 개체-관계 모델

1976년 피터 첸

개체(entity)와 개체 간의 관계를 이용해 현실 세계를 개념적 구조로 표현하는 방법

개체-관계 다이어그램(E-R Diagram): 현실 세계를 개념적으로 모델링하여 그림으로 표현

### 1. 개체

현실 세계에서 저장할 만한 가치가 있는 중요 데이터를 가지고 있는 사람이나 사물 등

꼭 물리적 존재x - 개념/사건처럼 개념적으로 존재해도 가능

- 특징

다른 개체와 구별되는 이름을 갖고 있음

각 개체만의 고유한 특성, 상태(속성)을 하나 이상 가지고 있음

개체 타입: 개체를 고유한 이름과 속성들로 정의한 것

개체 인스턴스/개체 어커런스: 개체를 구성하고 있는 속성이 실제 값을 가짐으로써 실체화된 개체

개체 집합: 특정 개체 타입에 대한 인스턴스들을 모아 놓은 것

- 예시
  - 개체 타입: 고객 (이름, 주소, 연락처, 적립금)
  - 개체 인스턴스: <정소화, 서울시 구로그, 010-1111-1111, 0> . . .

파일 구조에서 개체=레코드, 속성=필드, 개체 타입=레코드 타입, 개체 인스턴스=레코드 인스턴스

E-R 다이어그램에서 사각형으로 표현

### 2. 속성

개체가 가지고 있는 고유한 특성

그 자체만으로는 의미가 없지만 관련 있는 속성들을 모아 개체를 구성하면 하나의 중요한 의미 표현 가능

의미 있는 데이터의 가장 작은 논리적 단위

E-R 다이어그램에서 타원으로 표현

종류

- **속성 값의 개수**
  - 단일 값 속성: 특정 개체를 구성하는 속성 값이 하나인 경우 (이름, 적립금)
  - 다중 값 속성: 속성이 값을 여러개 가질 수 있는 경우 (연락처-집, 휴대폰) (이중 타원)
- **의미의 분해 가능성**
  - 단순 속성: 의미를 더 분해할 수 없는 속성 (적립금, 가격, 이름)
  - 복합 속성: 의미를 분해할 수 있어 값이 여러 개의 의미 포함 (주소, 생년월일 등)
    복합 속성이더라도 전체 단위로 입력/검색되는 경우 단순 속성으로 처리해도 됨
- **기존 속성 값에서 유도**
  - 유도 속성: 값이 별도로 저장x, 기존의 다른 속성 값에서 유도 (가격+할인율 → 판매가격) (점선 타원)
- 널 속성
  아직 결정되지 않았거나 모르는 값
  해당되는 값이 없는 (존재하지 않는) 값
  공백이나 0과는 다르다
- 키 속성
  개체 집합에 존재하는 각 개체 인스턴스들을 식별하는 데 사용(밑줄)
  이 값으로 개체 인스턴스를 식별할 수 있어야 한다.

![Untitled](./img/chap4-1.png)

### 3. 관계

개체와 개체가 맺고 있는 의미 있는 연관성

개체 집합들 사이의 대응 관계, 매핑을 의미

E-R 다이어그램에서 마름모로 표현

**관계의 유형**

- 개체타입의 수
  - 일대일 관계 (남편-아내)
  - 일대다 관계 (부서-사원)
  - 다대다 관계 (고객-책)

**관계의 참여 특성**

필수적 참여/전체 참여: 개체 A의 모든 개체 인스턴스가 관계에 반드시 참여해야 한다

선택적 참여/부분 참여: 일부만 참여해도 된다

필수적 참여 관계: 이중선

**관계의 종속성**

개체 B가 독자적으로 존재할 수 없고 다른 개체 A의 존재 여부에 종속적

존재 종속: 개체 A가 존재해야 개체 B가 존재, 개체 A가 삭제되면 개체 B도 함께 삭제

개체 B(다른 개체의 존재 여부에 의존적) - 약한 개체

개체 A(다른 개체의 존재 여부를 결정) - 강한 개체

학부모 - 학생, 직원 - 부양가족

일반적으로 일대다 관계, 약한 개체는 강한 개체의 관계에 **필수적 참여**

식별→ 구별자, 부분키 (부양가족 개체의 키: 직원번호, 이름)

## 03 논리적 데이터 모델

### 1. 논리적 데이터 모델의 개념과 특성

E-R 다이어그램으로 표현된 개념적 구조를 데이터베이스에 표현하는 행태를 결정 → 데이터베이스 관리 시스템 종류가 중요하다

정의: 사용자 입장에서 선택한 데이터베이스 관리 시스템에 따라 E-R 다이어그램으로 표현된 개념적 구조를 데이터베이스에 어떤 형태로 저장할지를 논리적으로 표현한 구조

데이터베이스 스키마: 논리적 데이터 모델로 표현된 데이터베이스의 논리적 구조

종류: 관계 데이터 모델, 계층 데이터 모델, 네트워크 데이터 모델

### 2. 계층 데이터 모델

논리적 구조가 트리 형태

개체 간 관계는 링크(연결선) - 일대다 관계만 표현할 수 있음

- **특징**

다대다 관계를 표현할 수 없음 - 별도의 개체를 추가로 생성하여 이를 표현

루트 역할을 하는 개체가 존재, 사이클이 존재하지 않음

일대다 관계를 맺는 개체들 사이에 상하 관계가 성립 (부모-자식 개체)

부모 개체 하나는 자식 개체를 여러 개 가질 수 있지만, 자식 객체는 부모 개체를 하나만 가질 수 있음

단점-제약이 존재해서 자연스럽게 모델링하기 어려움

-데이터의 삽입 삭제 수정 등을 연산하거나 원하는 데이터를 검색하기 쉽지 않다

### 3. 네트워크 데이터 모델

데이터베이스의 논리적 구조: 그래프 또는 네트워크 형태

화살표로 관계를 표현 - 일대다 관계만 표현하 수 있음

계층 데이터 모델과 달리 **두 개체 간의 관계를 여러 개 정의**할 수 있음

일대다 관계의 개체: 오너-멤버 관계

오너 개체 하나가 맴버 개체 여러 개, 멤버 개체 하나가 오너 개체 여러 개와 관계를 맺을 수 있음

- **특징**

같은 개체들 사이의 관계를 2개 이상 표현 가능 (좀 더 자연스럽게 모델링)

계층 데이터 모델보다 구조가 복잡해질 수 있다 - 연산, 데이터 검색이 계층 데이터 모델보다 어려워진다