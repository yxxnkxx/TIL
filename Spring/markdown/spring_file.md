# 파일 업로드

1. pom.xml에 commons-fileupload 추가

```xml
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>
```

2. servlet-context에 multipartResolver 추가

```xml
<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<beans:property name="defaultEncoding" value="UTF-8"></beans:property>
		<beans:property name="maxUploadSize" value="-1"></beans:property>
	</beans:bean>
```

defaultEncoding = 인코딩 방식

maxUploadSize= 최대 업로드 크기, -1인 경우 무한

3. form 수정

```xml
<form method="POST" enctype="multipart/form-data">

...

<input type="file" id="file" name="file" class="input-image" accept="image/*">
```

method: POST,

enctype=”multipart/form-data”로 설정

이미지 파일만 받고싶은 경우 `accept="image/*"`

4. ProductController

```java
@PostMapping("/product/regist")
	public String regist(Model model, @RequestPart(required = false) MultipartFile file, Product product)
			throws IllegalStateException, IOException {

		// 파일이 존재하는지 검사
		if (file != null && file.getSize() > 0) {
			UUID uuid = UUID.randomUUID();
			String realName = file.getOriginalFilename(); // 파일 확장자를 포함한 전체 파일 이름

			String saveFolder = "C:\\temp"; // 저장할 폴더 이름
			String uniqueName = uuid.toString() + "_" + realName; // 중복 방지를 위한 uuid + 원래 파일 이름
			product.setFileName(uniqueName);
			product.setFileUri(saveFolder + "\\" + uniqueName);
			file.transferTo(new File(saveFolder, uniqueName)); // saveFolder에 파일 업로드
		}

	}
```

`@RequestPart(required = false)` → 해당 필드가 쿼리스트링에 존재하지 않아도 예외가 발생하지 않음

[https://velog.io/@lecharl/스프링12ResponseBody-파일3-다운로드](https://velog.io/@lecharl/%EC%8A%A4%ED%94%84%EB%A7%8112ResponseBody-%ED%8C%8C%EC%9D%BC3-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C)

5. file download

```java
	@GetMapping("/download")
	@ResponseBody
	public void downloadFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("filename", fileName);

		response.setHeader("fileName", fileName.substring(fileName.lastIndexOf('_') + 1));
		File file = new File("C:\\temp\\" + fileName); // 파일이 저장된 경로 
		byte[] data = FileUtils.readFileToByteArray(file); // 파일을 바이트로 변환 

		response.getOutputStream().write(data); // data를 클라이언트에 보냄
	}
```

`@ResponseBody`: 페이지 이동 없이 화면에 보여주는 것

다운로드 링크를 누르면 이미지가 뜨고 헤더에 **fileName:** temp.jfif 로 설정되어 있음