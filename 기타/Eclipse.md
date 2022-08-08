# Eclipse

### 단축키

자동완성: `Ctrl + Space`

자동정렬: `Ctrl + Shift + F`

저장 시 자동정렬

Window > Preferences > Java > Editor > Save Actions > Perform the selected actions on save > Format source code > (Format all lines/ Format edited lines)

[https://hianna.tistory.com/653](https://hianna.tistory.com/653)

문장 지우기: `Ctrl + D`

블럭 단위 이동: `Alt + 방향키`

단축키 목록: `Ctrl + Shift + L`

다음줄로 이동: `Shift + Enter`

자동 import: `Ctrl + Shift + O`

### 에러

`code recommenders cannot download its model repository index`

해결: [Code Recommenders] > [Models] > ‘Enable auto-download’ <> [Apply]

출처: [https://bada744.tistory.com/24](https://bada744.tistory.com/24) 

---

### Getter / Setter

Source → Generate Getter and Setter

```java
private boolean hungry;

public boolean isHungry() {
	return hungry;
}

public void setHungry(boolean hungry) {
	this.hungry = hungry;
}
```