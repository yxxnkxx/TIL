# Git

## 0717

경고 메시지

```html
warning: LF will be replaced by CRLF in bora.txt.
The file will have its original line endings in your working directory
```

window

```html
git config --global core.autocrlf true
```

---

사용자 이름, 이메일 설정

`git config --global user.email "EMAIL"`

`git config --global user.name "NAME"`

---

push

`git remote add origin [깃헙주소]`

`git branch -M main`

`git push -u origin main`

---

### Repository 언어 변경

```java
* linguist-vendored
*.java linguist-vendored=false
```

모든 언어를 일단 무시하고, 인식할 언어를 설정

[https://doing7.tistory.com/53](https://doing7.tistory.com/53)