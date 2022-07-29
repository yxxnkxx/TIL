# CSS

## 0715

### CSS

h1: 태그, 클래스 이름, id

---

Selector

ID - 특정 한 개만 됨

Class - 여러 개 가능

Selector: #으로 표시

```css
b {
    color: red;
    font-size: 20px;
}
#apple {
    color: green;
}

.blue-property {
    color: blue;
}

///////////////////////////

<ul>
  <li class="blue-property">마이크로소프트 윈도우 10 레드스톤 버전 이상에서는 ⊞ Win+. 혹은 ⊞ Win+;을 누른다.</li>
  <li id="apple">Apple macOS에서는 Ctrl+⌘ Cmd+Space를 누른다.</li>
  <li class="blue-property">테스트1</li>
  <li>테스트2</li>
  <li class="blue-property">테스트3</li>
</ul>
```

Descendant Selector: 안에 있기만 하면 됨

Child Selector: 바로 밑에 있어야 함

id는 하나만 → 여러개여도 에러 나지 않음

framework: 뼈대, 골격

java → spring → web service