### Uniform Resource Identifier

URI는 로케이터(locator), 이름(name) 또는 둘다 추가로 분류될 수 있다

- URL: 리소스의 위치
- URN: 리소스의 이름

URL - foo://example.com:8042/over/there?name=ferret#nose

URN - urn:exmaple:animal:ferret:nose

**URI**

- Uniform: 리소스 식별하는 통일된 방식
- Resource: 자원, URI로 식별할 수 있는 모든 것(제한 없음)
- Identifier: 다른 항목과 구분하는데 필요한 정보

**URL**

- Locator: 리소스가 있는 위치를 지정

**URN**

- Name: 리소스에 이름을 부여

\*\*위치는 변할 수 있지만, 이름은 변하지 않는다

ex. 책의 ISBN

URN 이름만으로 실제 리소스를 찾을 수 있는 방법이 보편화되지 않음

앞으로는 URI를 URL과 같은 의미로 설명

URL 분석

[https://www.google.com/search?q=hello&hl=ko](https://www.google.com/search?q=hello&hl=ko)

scheme://\[userinfo@\]host\[:port\]\[/path\]\[?query\]\[#fragment\]

- 프로토콜 https
- 호스트명 [www.google.com](http://www.google.com)
- 포트 번호 443
- 패스 (/search)
- 쿼리 파라미터 (q=hello&hl=ko)

**host**

호스트명, 도메인명이나 IP 주소를 직접

**포트**

일반적으로 생략

**path**

리소스 경로, 계층적 구조

**query**

key=value 형태

?로 시작, &로 추가 가능
