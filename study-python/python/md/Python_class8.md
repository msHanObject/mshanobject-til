Python class *__7__*
===

### *__공공 데이터__*

### *__서울특별시 관광지 입장정보__*

이번 수업에서는 저번 수업에서 생성하게 된 `서울특별시_관광지입장정보_2011_2016.json`파일을 다뤄 보도록 하겠습니다.

<br>

#### *__json파일처리__*

json파일을 다루는 데 필요한 설정은 json과 pandas를 import하는 것 뿐입니다.

```python
import json
import pandas as pd
```
<br>

#### *__json.loads(open().read())__*

아래코드는 json파일을 로드하는 내용입니다.
`open`하려는 파일명, 작업방식, 인코딩 형식을 지정하여 파일을 `read`하고,
read된 json파일을 불러와서 jsonTP라는 객체에 담습니다.

```python
fileName='서울특별시_관광지입장정보_2011_2016.json'
jsonTP= json.loads(open(fileName, 'r', encoding='utf-8').read())
```
그렇게 담겨진 json파일 데이터를 출력해보도록 하겠습니다.

```python
jsonTP
```




    [{'ForNum': 53165,
      'NatNum': 222484,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 33436,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 164752,
      'NatNum': 518742,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 2990,
      'NatNum': 97869,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 26324,
      'NatNum': 45339,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 263728,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 103723,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 18972,
      'NatNum': 137870,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 76839,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 47,
      'NatNum': 16681,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 56041,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 0,
      'NatNum': 27909,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 2046,
      'NatNum': 8386,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 441,
      'NatNum': 7901,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 6542,
      'NatNum': 44530,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 33938,
      'NatNum': 509114,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 16,
      'sido': '서울특별시',
      'yyyymm': '201110'},
     {'ForNum': 62683,
      'NatNum': 160836,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 20705,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 126301,
      'NatNum': 304318,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 3469,
      'NatNum': 61697,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 23679,
      'NatNum': 37579,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 199058,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 85835,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 16045,
      'NatNum': 107467,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 112560,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 32,
      'NatNum': 5795,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 39012,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 0,
      'NatNum': 27599,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 3329,
      'NatNum': 7909,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 441,
      'NatNum': 3413,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 7176,
      'NatNum': 33425,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 30793,
      'NatNum': 454189,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 16,
      'sido': '서울특별시',
      'yyyymm': '201111'},
     {'ForNum': 44722,
      'NatNum': 75991,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 11017,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 132399,
      'NatNum': 237330,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 3133,
      'NatNum': 21267,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 18226,
      'NatNum': 24223,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 233828,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 93554,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 13935,
      'NatNum': 52140,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 61115,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 10,
      'NatNum': 1885,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 32412,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 0,
      'NatNum': 19308,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 5214,
      'NatNum': 6956,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 333,
      'NatNum': 1823,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 4704,
      'NatNum': 16634,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 52484,
      'NatNum': 642290,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 16,
      'sido': '서울특별시',
      'yyyymm': '201112'},
     {'ForNum': 57349,
      'NatNum': 144268,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 2457,
      'NatNum': 29285,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 138532,
      'NatNum': 520733,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 4008,
      'NatNum': 125906,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 16198,
      'NatNum': 21956,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 20894,
      'NatNum': 265513,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 4047,
      'NatNum': 125261,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 14512,
      'NatNum': 143940,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 0,
      'NatNum': 147860,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 78,
      'NatNum': 19554,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 5860,
      'NatNum': 35768,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 0,
      'NatNum': 25009,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 28799,
      'NatNum': 12870,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 318,
      'NatNum': 7365,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 44389,
      'NatNum': 500556,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201210'},
     {'ForNum': 45427,
      'NatNum': 89575,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 1082,
      'NatNum': 19029,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 113319,
      'NatNum': 192476,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 3748,
      'NatNum': 52830,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 12797,
      'NatNum': 15881,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 16857,
      'NatNum': 231618,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 3537,
      'NatNum': 96291,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 13791,
      'NatNum': 97241,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 0,
      'NatNum': 120222,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 21,
      'NatNum': 4841,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 5364,
      'NatNum': 21556,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 0,
      'NatNum': 31214,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 13172,
      'NatNum': 6319,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 139,
      'NatNum': 2561,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 40071,
      'NatNum': 562680,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201211'},
     {'ForNum': 27814,
      'NatNum': 28061,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 546,
      'NatNum': 5978,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '운현궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 105370,
      'NatNum': 133012,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 1451,
      'NatNum': 15722,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 8817,
      'NatNum': 8068,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 13033,
      'NatNum': 227077,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 1138,
      'NatNum': 78179,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '서울역사박물관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 11151,
      'NatNum': 44855,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 0,
      'NatNum': 213467,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '서울시립미술관 본관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 15,
      'NatNum': 1252,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 6625,
      'NatNum': 14090,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 0,
      'NatNum': 18881,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 24492,
      'NatNum': 9167,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 13,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 62,
      'NatNum': 860,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 14,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 66868,
      'NatNum': 670056,
      'addrCd': 1171,
      'gungu': '송파구',
      'resNm': '롯데월드',
      'rnum': 15,
      'sido': '서울특별시',
      'yyyymm': '201212'},
     {'ForNum': 51382,
      'NatNum': 144549,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 107719,
      'NatNum': 385056,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 4441,
      'NatNum': 103908,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 12425,
      'NatNum': 22552,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 14406,
      'NatNum': 265316,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 13137,
      'NatNum': 123934,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 37,
      'NatNum': 6163,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 5948,
      'NatNum': 72607,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 0,
      'NatNum': 24073,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 34428,
      'NatNum': 9290,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 99,
      'NatNum': 7139,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 3325,
      'NatNum': 40085,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201310'},
     {'ForNum': 39748,
      'NatNum': 107904,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 101941,
      'NatNum': 211840,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 3198,
      'NatNum': 60978,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 9353,
      'NatNum': 18896,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 11066,
      'NatNum': 192502,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 13426,
      'NatNum': 166366,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 18,
      'NatNum': 2634,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 5474,
      'NatNum': 42760,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 0,
      'NatNum': 33703,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 29330,
      'NatNum': 7266,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 41,
      'NatNum': 3289,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 3121,
      'NatNum': 23575,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201311'},
     {'ForNum': 25550,
      'NatNum': 38553,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 101942,
      'NatNum': 145544,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 1986,
      'NatNum': 20035,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 6048,
      'NatNum': 13896,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 9083,
      'NatNum': 286992,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 13282,
      'NatNum': 99301,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 6,
      'NatNum': 706,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 6732,
      'NatNum': 14607,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 0,
      'NatNum': 23245,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 43129,
      'NatNum': 11886,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 16,
      'NatNum': 1248,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 1445,
      'NatNum': 11890,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201312'},
     {'ForNum': 58996,
      'NatNum': 182385,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 207722,
      'NatNum': 468796,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 3860,
      'NatNum': 116874,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 6809,
      'NatNum': 24230,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 11623,
      'NatNum': 241780,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 18733,
      'NatNum': 148878,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 31,
      'NatNum': 8134,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 5926,
      'NatNum': 79529,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 0,
      'NatNum': 26868,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 38218,
      'NatNum': 8912,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 9,
      'NatNum': 7077,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 1149,
      'NatNum': 37914,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201410'},
     {'ForNum': 47051,
      'NatNum': 136085,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 164220,
      'NatNum': 288936,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 3668,
      'NatNum': 75897,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 6022,
      'NatNum': 15959,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 8967,
      'NatNum': 197616,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 19173,
      'NatNum': 123477,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 9,
      'NatNum': 4055,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 5443,
      'NatNum': 41879,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 0,
      'NatNum': 33254,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 34628,
      'NatNum': 6000,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 16,
      'NatNum': 3662,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 918,
      'NatNum': 26030,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201411'},
     {'ForNum': 29928,
      'NatNum': 41561,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 129946,
      'NatNum': 190501,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 1693,
      'NatNum': 19473,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 3891,
      'NatNum': 5951,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 7195,
      'NatNum': 258781,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 14723,
      'NatNum': 44272,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 12,
      'NatNum': 486,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 6263,
      'NatNum': 27205,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 0,
      'NatNum': 24527,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 42443,
      'NatNum': 7478,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 5,
      'NatNum': 1036,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 823,
      'NatNum': 8869,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201412'},
     {'ForNum': 49946,
      'NatNum': 189933,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 203207,
      'NatNum': 403641,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 3936,
      'NatNum': 113075,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 5519,
      'NatNum': 21238,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 17862,
      'NatNum': 242371,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 21610,
      'NatNum': 144902,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 78,
      'NatNum': 19231,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 6269,
      'NatNum': 71906,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 0,
      'NatNum': 27564,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 21961,
      'NatNum': 7623,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 17,
      'NatNum': 5111,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 1022,
      'NatNum': 36678,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201510'},
     {'ForNum': 44845,
      'NatNum': 126038,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 188000,
      'NatNum': 219760,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 3106,
      'NatNum': 61218,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 4763,
      'NatNum': 24921,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 14001,
      'NatNum': 222495,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 19798,
      'NatNum': 117633,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 15,
      'NatNum': 7229,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 5537,
      'NatNum': 35371,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 0,
      'NatNum': 27430,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 21274,
      'NatNum': 5535,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 10,
      'NatNum': 2927,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 714,
      'NatNum': 23381,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201511'},
     {'ForNum': 33342,
      'NatNum': 59738,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 170379,
      'NatNum': 148961,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 1783,
      'NatNum': 27172,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 3406,
      'NatNum': 14936,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 13127,
      'NatNum': 281457,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 15197,
      'NatNum': 68749,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 16,
      'NatNum': 2221,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 5588,
      'NatNum': 44232,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 0,
      'NatNum': 22587,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 29520,
      'NatNum': 8822,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 3,
      'NatNum': 1647,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 492,
      'NatNum': 14454,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201512'},
     {'ForNum': 66671,
      'NatNum': 182621,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 236910,
      'NatNum': 512873,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 5468,
      'NatNum': 121351,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 8813,
      'NatNum': 24122,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 14386,
      'NatNum': 262585,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 29549,
      'NatNum': 150871,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 102,
      'NatNum': 17881,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 6272,
      'NatNum': 76689,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 0,
      'NatNum': 26965,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 23237,
      'NatNum': 7392,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 11,
      'NatNum': 5253,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 554,
      'NatNum': 39194,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201610'},
     {'ForNum': 55860,
      'NatNum': 143481,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 148113,
      'NatNum': 194951,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 5605,
      'NatNum': 60322,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 6887,
      'NatNum': 16678,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 11950,
      'NatNum': 190929,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 25262,
      'NatNum': 101022,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 40,
      'NatNum': 7507,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 5559,
      'NatNum': 47882,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 0,
      'NatNum': 24905,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 16675,
      'NatNum': 4179,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 49,
      'NatNum': 2915,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 370,
      'NatNum': 27041,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201611'},
     {'ForNum': 39559,
      'NatNum': 52082,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창덕궁',
      'rnum': 1,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 142110,
      'NatNum': 151317,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '경복궁',
      'rnum': 2,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 3447,
      'NatNum': 20397,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '창경궁',
      'rnum': 3,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 4905,
      'NatNum': 11575,
      'addrCd': 1111,
      'gungu': '종로구',
      'resNm': '종묘',
      'rnum': 4,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 9439,
      'NatNum': 298297,
      'addrCd': 1117,
      'gungu': '용산구',
      'resNm': '국립중앙박물관',
      'rnum': 5,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 20499,
      'NatNum': 52797,
      'addrCd': 1114,
      'gungu': '중구',
      'resNm': '덕수궁',
      'rnum': 6,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 4,
      'NatNum': 2406,
      'addrCd': 1135,
      'gungu': '노원구',
      'resNm': '태릉 ·  강릉 · 조선왕릉전시관',
      'rnum': 7,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 5597,
      'NatNum': 28405,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문형무소역사관',
      'rnum': 8,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 0,
      'NatNum': 20579,
      'addrCd': 1141,
      'gungu': '서대문구',
      'resNm': '서대문자연사박물관',
      'rnum': 9,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 26168,
      'NatNum': 6187,
      'addrCd': 1144,
      'gungu': '마포구',
      'resNm': '트릭아이미술관',
      'rnum': 10,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 1,
      'NatNum': 1785,
      'addrCd': 1165,
      'gungu': '서초구',
      'resNm': '헌릉ㆍ인릉',
      'rnum': 11,
      'sido': '서울특별시',
      'yyyymm': '201612'},
     {'ForNum': 125,
      'NatNum': 14507,
      'addrCd': 1168,
      'gungu': '강남구',
      'resNm': '선릉·정릉',
      'rnum': 12,
      'sido': '서울특별시',
      'yyyymm': '201612'}]



<br>

#### *__json 데이터를 DataFrame형태로 바꾸기__*

위에 출력된 json 데이터는 너무나도 방대하고 보기 힘듭니다.
그래서 아래코드를 통해 보고 싶은 내용만 선택해서 DataFrame형태로 만들고 출력하겠습니다.

```python
tour_DF= pd.DataFrame(jsonTP, columns={'yyyymm', 'resNm', 'ForNum'})
print(tour_DF)
```




<div>
<style>
    .dataframe thead tr:only-child th {
        text-align: right;
    }

    .dataframe thead th {
        text-align: left;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>ForNum</th>
      <th>resNm</th>
      <th>yyyymm</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>53165</td>
      <td>창덕궁</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>1</th>
      <td>0</td>
      <td>운현궁</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>2</th>
      <td>164752</td>
      <td>경복궁</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>3</th>
      <td>2990</td>
      <td>창경궁</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>4</th>
      <td>26324</td>
      <td>종묘</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>5</th>
      <td>0</td>
      <td>국립중앙박물관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>6</th>
      <td>0</td>
      <td>서울역사박물관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>7</th>
      <td>18972</td>
      <td>덕수궁</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>8</th>
      <td>0</td>
      <td>서울시립미술관 본관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>9</th>
      <td>47</td>
      <td>태릉 ·  강릉 · 조선왕릉전시관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>10</th>
      <td>0</td>
      <td>서대문형무소역사관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>11</th>
      <td>0</td>
      <td>서대문자연사박물관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>12</th>
      <td>2046</td>
      <td>트릭아이미술관</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>13</th>
      <td>441</td>
      <td>헌릉ㆍ인릉</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>14</th>
      <td>6542</td>
      <td>선릉·정릉</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>15</th>
      <td>33938</td>
      <td>롯데월드</td>
      <td>201110</td>
    </tr>
    <tr>
      <th>16</th>
      <td>62683</td>
      <td>창덕궁</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>17</th>
      <td>0</td>
      <td>운현궁</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>18</th>
      <td>126301</td>
      <td>경복궁</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>19</th>
      <td>3469</td>
      <td>창경궁</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>20</th>
      <td>23679</td>
      <td>종묘</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>21</th>
      <td>0</td>
      <td>국립중앙박물관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>22</th>
      <td>0</td>
      <td>서울역사박물관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>23</th>
      <td>16045</td>
      <td>덕수궁</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>24</th>
      <td>0</td>
      <td>서울시립미술관 본관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>25</th>
      <td>32</td>
      <td>태릉 ·  강릉 · 조선왕릉전시관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>26</th>
      <td>0</td>
      <td>서대문형무소역사관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>27</th>
      <td>0</td>
      <td>서대문자연사박물관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>28</th>
      <td>3329</td>
      <td>트릭아이미술관</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>29</th>
      <td>441</td>
      <td>헌릉ㆍ인릉</td>
      <td>201111</td>
    </tr>
    <tr>
      <th>...</th>
      <td>...</td>
      <td>...</td>
      <td>...</td>
    </tr>
    <tr>
      <th>207</th>
      <td>102</td>
      <td>태릉 ·  강릉 · 조선왕릉전시관</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>208</th>
      <td>6272</td>
      <td>서대문형무소역사관</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>209</th>
      <td>0</td>
      <td>서대문자연사박물관</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>210</th>
      <td>23237</td>
      <td>트릭아이미술관</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>211</th>
      <td>11</td>
      <td>헌릉ㆍ인릉</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>212</th>
      <td>554</td>
      <td>선릉·정릉</td>
      <td>201610</td>
    </tr>
    <tr>
      <th>213</th>
      <td>55860</td>
      <td>창덕궁</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>214</th>
      <td>148113</td>
      <td>경복궁</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>215</th>
      <td>5605</td>
      <td>창경궁</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>216</th>
      <td>6887</td>
      <td>종묘</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>217</th>
      <td>11950</td>
      <td>국립중앙박물관</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>218</th>
      <td>25262</td>
      <td>덕수궁</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>219</th>
      <td>40</td>
      <td>태릉 ·  강릉 · 조선왕릉전시관</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>220</th>
      <td>5559</td>
      <td>서대문형무소역사관</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>221</th>
      <td>0</td>
      <td>서대문자연사박물관</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>222</th>
      <td>16675</td>
      <td>트릭아이미술관</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>223</th>
      <td>49</td>
      <td>헌릉ㆍ인릉</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>224</th>
      <td>370</td>
      <td>선릉·정릉</td>
      <td>201611</td>
    </tr>
    <tr>
      <th>225</th>
      <td>39559</td>
      <td>창덕궁</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>226</th>
      <td>142110</td>
      <td>경복궁</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>227</th>
      <td>3447</td>
      <td>창경궁</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>228</th>
      <td>4905</td>
      <td>종묘</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>229</th>
      <td>9439</td>
      <td>국립중앙박물관</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>230</th>
      <td>20499</td>
      <td>덕수궁</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>231</th>
      <td>4</td>
      <td>태릉 ·  강릉 · 조선왕릉전시관</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>232</th>
      <td>5597</td>
      <td>서대문형무소역사관</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>233</th>
      <td>0</td>
      <td>서대문자연사박물관</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>234</th>
      <td>26168</td>
      <td>트릭아이미술관</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>235</th>
      <td>1</td>
      <td>헌릉ㆍ인릉</td>
      <td>201612</td>
    </tr>
    <tr>
      <th>236</th>
      <td>125</td>
      <td>선릉·정릉</td>
      <td>201612</td>
    </tr>
  </tbody>
</table>
<p>237 rows × 3 columns</p>
</div>


<br>

#### *__set_index__*
하지만 index가 무의미한 번호로 되어 있는 것 보단 년도 값을 가진 `yyyymm`으로 설정하는 게
더 보기 좋을 것 같습니다. `set_index`를 통해 index를 바꾸어서 DataFrame를 재구성한뒤 일부만 출력하겠습니다.

```python
tour_DF = tour_DF.set_index('yyyymm')
print(tour_DF.head())
```


<div>
<style>
    .dataframe thead tr:only-child th {
        text-align: right;
    }

    .dataframe thead th {
        text-align: left;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>ForNum</th>
      <th>resNm</th>
    </tr>
    <tr>
      <th>yyyymm</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>201110</th>
      <td>53165</td>
      <td>창덕궁</td>
    </tr>
    <tr>
      <th>201110</th>
      <td>0</td>
      <td>운현궁</td>
    </tr>
    <tr>
      <th>201110</th>
      <td>164752</td>
      <td>경복궁</td>
    </tr>
    <tr>
      <th>201110</th>
      <td>2990</td>
      <td>창경궁</td>
    </tr>
    <tr>
      <th>201110</th>
      <td>26324</td>
      <td>종묘</td>
    </tr>
  </tbody>
</table>
</div>



컬럼의 수가 2가지 밖에 안되어서 세로만 긴 형태의 자료가 출력되었습니다.
위 `tour_DF`을 `pandas.DataFrame.T`을 통해 역전시켜서 출력하면 더욱 보기 좋겠죠?

```python
tour_DF.head().T
```




<div>
<style>
    .dataframe thead tr:only-child th {
        text-align: right;
    }

    .dataframe thead th {
        text-align: left;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th>yyyymm</th>
      <th>201110</th>
      <th>201110</th>
      <th>201110</th>
      <th>201110</th>
      <th>201110</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>ForNum</th>
      <td>53165</td>
      <td>0</td>
      <td>164752</td>
      <td>2990</td>
      <td>26324</td>
    </tr>
    <tr>
      <th>resNm</th>
      <td>창덕궁</td>
      <td>운현궁</td>
      <td>경복궁</td>
      <td>창경궁</td>
      <td>종묘</td>
    </tr>
  </tbody>
</table>
</div>
