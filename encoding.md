# 인코딩이란?

- 정보의 형태나 형식을 변환하는 처리나 처리 방식
- 내용에는 변화가 없다
- 암호화로는 사용 불가능


## 인코딩 종류

- ASCII
- URL
- HTML
- Base64
- MS Script


### ASCII 인코딩

- ASCII (American Standard Code for Information Interchange) : 미국 정보교환 표준 코드
- 1Byte 중 7bit를 이용해서 0 ~ 127 까지 128개이 문자를 구성한 코드
- 128개의 문자는 영어 알파벳 대문자, 소문자, 보조문자, 제어문자로 구성
- 영문 알파벳을 사용하는 대표적인 문자 인코딩 방법


### URL 인코딩

- URL은 사용가능한 문자가 제한되어있다.
- URL 스펙인 RFC 1738에는 URL에 사용할 수 있는 문자는 알파벳, 숫자와 몇가지 특수문자로 제한되어있다.
- 따라서 한글 등의 다양한 문자를 URL에 표시하려면 URL에 허용하는 문자로 변환하여 표현해야 한다.
- URL 메타문자들에 대한 인코딩이 필요하다
- 형식 : 기존 문자열의 HEX 값 앞에 %를 사용 (ASCII Table의 출력가능 문자)
- 한글 : UTF-8 사용
- Force Full URL Encoding : 모든 문자열을 강제로 인코딩


### HTML 인코딩

- 문제가 있을만한 문자들을 인코딩하여 안전하게 HTML 문서와 함께 사용하기 위해 사용
- XSS의 대응방안으로 사용


### BASE64 인코딩

- 2진 데이터를 ASCII 형태의 텍스트로 표현 가능
- 웹 인증 중 기본인증에 사용
- 끝 부분의 Padding(==)으로 식별 가능
- 64개의 문자 사용 (영문 대, 소문자, 숫자 , + , / )
- 데이터를 6bit 단위로 표현

#### BASE64 인코딩 순서 

1. 아스키 테이블 맵핑
2. 2진수 변환
3. 6bit 단위로 분할
4. 10진수 변환
5. BASE64 테이블 맵핑
6. 패딩연산( 총 비트수 % 3 => 0 : 없음 / 1 : = / 2 : == )


### MS Script

- Microsoft 에서 제공하는 인코딩 기법으로 IE에서만 사용가능
- IE는 MS Script Decoder 를 가지고 있음
- ```<script language="Jscript.Encode">``` 를 명시하여 IE에게 알려주어야 함
- 범용성이 뛰어나지 않아 거의 사용하지 않는다.

# 출처
- https://itstory.tk/entry/인코딩이란-ASCII-URL-HTML-Base64-MS-Script-인코딩 [덕's IT Story]
- https://ko.wikipedia.org/wiki/%EB%AC%B8%EC%9E%90_%EC%9D%B8%EC%BD%94%EB%94%A9
- https://ko.wikipedia.org/wiki/%EC%9C%A0%EB%8B%88%EC%BD%94%EB%93%9C
- https://ko.wikipedia.org/wiki/MIME
- https://tools.ietf.org/html/rfc2045
- https://tools.ietf.org/html/rfc2046
- https://tools.ietf.org/html/rfc2047
