# 데이터베이스 기본 개념

## 01 데이터베이스의 필요성

데이터: 현실 세계에서 단순히 관찰하거나 측정하여 수집한 사실이나 값, 자료

정보: 데이터를 의사 결정에 유용하게 활용할 수 있도록 처리하여 체계적으로 조직한 결과물

정보 처리: 데이터에서 정보를 추출하는 과정 또는 방법

정보 시스템: 조직 운영에 필요한 데이터를 수집하여 저장해두었다가 의사 결정이 필요할 때 처리하여 유용한 정보를 만들어주는 수단

## 02 데이터베이스의 정의와 특징

### 데이터베이스의 정의

데이터베이스 = 특정 조직의 여러 사용자가 ‘공유’하여 사용할 수 있도록 ‘통합’해서 ‘저장’한 운영 데이터의 집합

- 공유 데이터(shared data)
  - 특정 조직의 여러 사용자가 함께 소유, 이용
  - → 사용 목적이 다른 사용자들을 두루 고려하여 구성해야 한다
- 통합 데이터(integrated data)
  - 데이터 중복성 허용x (의도적으로 허용하는 경우도 있음)
  - 데이터의 중복을 최소화, 통제가 가능한 중복만 허용
- 저장 데이터(stored data)
  - 컴퓨터가 접근할 수 있는 매체에 저장
- 운영 데이터(operational data)
  - 조직을 운영, 조직의 주요 기능을 수행하기 위해 꼭 필요
  - 지속적으로 유지

### 데이터베이스의 특징

- 실시간 접근 가능
  - 사용자의 데이터 요구에 실시간으로 응답
- 계속 변화
  - 현실 세계의 상태를 정확히 반영
  - 삽입, 삭제, 수정 → 동적인 특징
- 동시 공유 가능
  - 여러 사용자가 동시에 이용
  - 여러 사용자가 서로 다른 데이터를 동시에 사용 + 같은 데이터를 동시에 사용
- 내용으로 참조 가능(content reference)
  - 저장된 주소나 위치가 아닌 값, 내용으로 참조할 수 있음

## 03 데이터 과학 시대의 데이터

### 형태에 따른 데이터 분류

- 정형 데이터
  - 구조화된 데이터
  - 엑셀의 스프레드 시트, 관계 데이터베이스의 테이블
- 반정형 데이터
  - 데이터 내용 안에 구조에 대한 설명이 함께 존재
  - → 구조를 파악하는 ‘파싱’과정이 필요
  - 주로 파일 형태로 저장
  - HTML, XML, JSON 문서나 웹 로그, 센서 데이터 등
- 비정형 데이터
  - 정해진 구조가 없이 저장된 데이터
  - 소셜 데이터의 텍스트, 영상, 이미지, 음성, 워드나 pdf 문서 - 멀티미디어 데이터

### 특성에 따른 데이터 분류

- 범주형 데이터
  - 종류를 나타내는 값을 가진 데이터
  - 예: 성별, 학년 등
  - 명목형 데이터
    - 서열이 없는 값을 가지는 데이터(성별, 성격 유형)
  - 순서형 데이터
    - 서열이 있는 값을 가지는 데이터(학년, 회원 등급)
  - 대부분 문자 타입, 질적 데이터(크기 비교나 산술적인 연산x)
- 수치형 데이터
  - 양적 측면에서 크기 비교와 산술적인 연산이 가능한 숫자 값을 가진 데이터
  - 양적 데이터
  - 이산형 데이터
    - 단절된 숫자 값을 가지는 데이터
    - 주로 정수
  - 연속형 데이터
    - 연속적으로 이어진 숫자 값을 가지는 데이터
    - 주로 실수

</div>
</details>
