## Javascript -모듈(2/5):모듈화

### * 모듈이 없는 형태

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
</head>
<body>
  <script type="text/javascript">
    function welcome() {
      return 'Hello world';
    }
    arlert(welcome());
  </script>
</body>
</html>
```

최신 버전 javascript에서는 `type="text/javascript"` 문구 생략가능!

### * 모듈화 한 형태

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <script src="greeting.js"></script>
</head>
<body>
  <script type="text/javascript">
    arlert(welcome());
  </script>
</body>
</html>
```

```javascript
//greeting.js
function welcome() {
  return 'Hello world';
}
```
