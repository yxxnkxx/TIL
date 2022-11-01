HandlerInterceptor를 통한 요청 가로채기

- Controller가 요청을 처리하기 전/후 처리
- 실제 Business Logic과 분리되어 처리해야 하는 기능을 넣고 싶을 때 유용하다

```java
@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		// 세션에 로그인 정보가 있다면 그대로 진행
		if (session.getAttribute("loginUser") != null) {
			return true;
		}
		// 세션에 로그인 정보가 없다면 redirect
		else {
			response.sendRedirect("index");
			return false;
		}
	}
}
```

로그인 정보가 없을 때 미리 handle하기

- 로그인 정보가 있으면 그대로 진행, (return true)
- 로그인 정보가 없으면 index 페이지로 redirect

**servlet-context.xml**

```xml
<!-- 인터셉터 빈으로 등록 -->
	<beans:bean class="interceptor.SessionInterceptor" id="confirm"/>
	

	<!-- 인터셉터 설정, 순서 주의 -->
	<interceptors>
		<interceptor>
			<mapping path="/regist"/>
			<beans:ref bean="confirm"/>
		</interceptor>
	</interceptors>
```

cf. `<exclude-mapping path=”/~~”` 

interceptor를 실행할 경로에서 특정 경로를 제외할 수 있음

cf. 경로 매핑방식

- 한글자를 매핑 : /?
- 여러글자를 매핑: /*
- 여러 경로를 매핑: /**

mapping path: 어떤 경로로 요청이 들어올 떄 interceptor를 실행할지

