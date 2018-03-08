Object Model
===

###Window란?

window객체의 의미는 크게 두가지를 의미한다.
하나는 웹 브라우저에서 제공하는 전역객체를 의미하고, 다른 하나는 창이나 프레임을 의미한다. 그리고 모든 객체가 소속된 객체이기도 하다. 이러한 특성을 ECMAScript에서는 Global 객체라고 부른다. 

###DOM이란?

Document Object Model 이란 뜻으로, document란 객체를 제공하며 이는 window객체의 프로퍼티에 해당한다.
document라는 객체는 `<body>`, `<img>` 같은 문서 안의 태그들을 제어할 수 있다.

###BOM이란?

BOM(Browser Object Model)이란 웹브라우저의 창이나 프래임을 추상화해서 프로그래밍적으로 제어할 수 있도록 제공하는 수단이다. BOM은 전역객체인 Window의 프로퍼티와 메소드들을 통해서 제어할 수 있다. 현재의 웹브라우저가 가리키는 url을 알아낸다거나, 현재 웹브라우저의 페이지를 리로드 하는 등...
웹페이지의 내용을 제외한 브라우저의 각종 요소들을 객체화시킨 것이다. 전역객체 window의 프로퍼티에 속한 객체들이 이에 속한다.

###JavaScript Core

javascript라는 언어를 통해 browser를 제어할 수도 있고, GAS(Google Apps Script)를 제어할 수도 있으며, node.js라는 서버측 자바스크립트를 제어할 수도 있다.
즉, JavaScript 언어 자체에 정의되어 있는 객체들을 뜻한다.
