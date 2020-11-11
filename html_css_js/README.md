# Notice

## Javascript

### Name conflict
If the id attribute of the html element and the JavaScript function name are the same, the function cannot be used.

For example:
```html5
<button type="button" id="myFunction" onclick="myFunction()">Do something</button>
```
