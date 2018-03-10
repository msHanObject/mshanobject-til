Python Class *__3__*
===

<br>

### *__Excel 다루기__*

데이터 자료들은 구조화하기 편한 엑셀파일로 만들어져 있는 경우가 많다.
따라서 이번에는 엑셀파일을 다루어 보는 시간을 갖도록 하겠다.

먼저 아래와 같이 `pandas`를 `import`하도록 한다.
```python
import pandas as pd
```
<br>

#### *__Pandas.read_csv()__*
Pandas에는 csv파일을 읽어서 Dataframe 객체에 테이블형 데이터를 리턴하는 기능을 제공하는 `read_csv`가 있다.
아래와 같이 디렉토리경로/파일이름+확장자명까지 포함된 파일경로를 값으로 넘겨주고 head부분을 우선 출력해보도록 한다.
```python
df = pd.read_csv('/~디렉토리경로/파일이름.csv')
print(df.head())
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
      <th>0</th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
      <th>4</th>
      <th>5</th>
      <th>6</th>
      <th>7</th>
      <th>8</th>
      <th>9</th>
      <th>10</th>
      <th>11</th>
      <th>12</th>
      <th>13</th>
      <th>14</th>
      <th>15</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>NaN</td>
      <td>№ Summer</td>
      <td>01 !</td>
      <td>02 !</td>
      <td>03 !</td>
      <td>Total</td>
      <td>№ Winter</td>
      <td>01 !</td>
      <td>02 !</td>
      <td>03 !</td>
      <td>Total</td>
      <td>№ Games</td>
      <td>01 !</td>
      <td>02 !</td>
      <td>03 !</td>
      <td>Combined total</td>
    </tr>
    <tr>
      <th>1</th>
      <td>Afghanistan (AFG)</td>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>2</th>
      <td>Algeria (ALG)</td>
      <td>12</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
      <td>3</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>15</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
    </tr>
    <tr>
      <th>3</th>
      <td>Argentina (ARG)</td>
      <td>23</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
      <td>18</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>41</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
    </tr>
    <tr>
      <th>4</th>
      <td>Armenia (ARM)</td>
      <td>5</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
      <td>6</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>11</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
    </tr>
  </tbody>
</table>
</div>



읽어온 csv파일의 내용이 불필요한 인덱스와 컬럼을 포함하고 있으니 아래와 같이 코드를 수정해서
자료를 다시 구성하여 읽어들이도록 한다. 읽은 자료는 `df`라는 dataframe객체에 할당하여 사용하도록 한다.
```python
df = pd.read_csv('/디렉토리경로/파일이름.csv', index_col = 0, skiprows = 1)
print(df.head())
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
      <th>№ Summer</th>
      <th>01 !</th>
      <th>02 !</th>
      <th>03 !</th>
      <th>Total</th>
      <th>№ Winter</th>
      <th>01 !.1</th>
      <th>02 !.1</th>
      <th>03 !.1</th>
      <th>Total.1</th>
      <th>№ Games</th>
      <th>01 !.2</th>
      <th>02 !.2</th>
      <th>03 !.2</th>
      <th>Combined total</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Afghanistan (AFG)</th>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>Algeria (ALG)</th>
      <td>12</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
      <td>3</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>15</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
    </tr>
    <tr>
      <th>Argentina (ARG)</th>
      <td>23</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
      <td>18</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>41</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
    </tr>
    <tr>
      <th>Armenia (ARM)</th>
      <td>5</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
      <td>6</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>11</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
    </tr>
    <tr>
      <th>Australasia (ANZ) [ANZ]</th>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>12</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>12</td>
    </tr>
  </tbody>
</table>
</div>

<br>

#### *__칼럼 데이터들 수정하기__*
```python
df.columns
```




    Index(['№ Summer', '01 !', '02 !', '03 !', 'Total', '№ Winter', '01 !.1',
           '02 !.1', '03 !.1', 'Total.1', '№ Games', '01 !.2', '02 !.2', '03 !.2',
           'Combined total'],
          dtype='object')




```python
for col in df.columns:
    if col[:2] == '01':
        df.rename(columns={col: 'Gold' + col[4:]}, inplace=True)
    if col[:2] == '02':
        df.rename(columns={col: 'Sivler' + col[4:]}, inplace=True)
    if col[:2] == '03':
        df.rename(columns={col: 'Bronze' + col[4:]}, inplace=True)
    if col[:1] == '№':
        df.rename(columns={col: '#' + col[2:]}, inplace=True)
```
위 코드는 칼럼들 데이터 중에 '01', '02', '03', '№'를 각각 'Gold', 'Sivler', 'Bronze', '#'으로
수정하는 내용이다.


<br>

#### *__Dataframe 데이터 조회하기__*

아래 코드들은 `df`라는 Dataframe 객체에 담긴 테이블형 데이터를 수정하고 조회하는 내용들이다.
아래와 같은 코드들을 통해 좀더 쉽게 데이터를 다룰 수 있게 된다.
```python
df.count()
```




    #Summer           147
    Gold              147
    Sivler            147
    Bronze            147
    Total             147
    #Winter           147
    Gold.1            147
    Sivler.1          147
    Bronze.1          147
    Total.1           147
    #Games            147
    Gold.2            147
    Sivler.2          147
    Bronze.2          147
    Combined total    147
    dtype: int64




```python
df.head()
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
      <th>№ Summer</th>
      <th>01 !</th>
      <th>02 !</th>
      <th>03 !</th>
      <th>Total</th>
      <th>№ Winter</th>
      <th>01 !.1</th>
      <th>02 !.1</th>
      <th>03 !.1</th>
      <th>Total.1</th>
      <th>№ Games</th>
      <th>01 !.2</th>
      <th>02 !.2</th>
      <th>03 !.2</th>
      <th>Combined total</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Afghanistan (AFG)</th>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>Algeria (ALG)</th>
      <td>12</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
      <td>3</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>15</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
    </tr>
    <tr>
      <th>Argentina (ARG)</th>
      <td>23</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
      <td>18</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>41</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
    </tr>
    <tr>
      <th>Armenia (ARM)</th>
      <td>5</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
      <td>6</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>11</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
    </tr>
    <tr>
      <th>Australasia (ANZ) [ANZ]</th>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>12</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>12</td>
    </tr>
  </tbody>
</table>
</div>




```python
df = df.drop('Total', axis=1)
```


```python
df.head()
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
      <th>№ Summer</th>
      <th>01 !</th>
      <th>02 !</th>
      <th>03 !</th>
      <th>№ Winter</th>
      <th>01 !.1</th>
      <th>02 !.1</th>
      <th>03 !.1</th>
      <th>Total.1</th>
      <th>№ Games</th>
      <th>01 !.2</th>
      <th>02 !.2</th>
      <th>03 !.2</th>
      <th>Combined total</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Afghanistan (AFG)</th>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>13</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th>Algeria (ALG)</th>
      <td>12</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>3</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>15</td>
      <td>5</td>
      <td>2</td>
      <td>8</td>
      <td>15</td>
    </tr>
    <tr>
      <th>Argentina (ARG)</th>
      <td>23</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>18</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>41</td>
      <td>18</td>
      <td>24</td>
      <td>28</td>
      <td>70</td>
    </tr>
    <tr>
      <th>Armenia (ARM)</th>
      <td>5</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>6</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>11</td>
      <td>1</td>
      <td>2</td>
      <td>9</td>
      <td>12</td>
    </tr>
    <tr>
      <th>Australasia (ANZ) [ANZ]</th>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>0</td>
      <td>2</td>
      <td>3</td>
      <td>4</td>
      <td>5</td>
      <td>12</td>
    </tr>
  </tbody>
</table>
</div>




```python
df.iloc[0]
```




    #Summer           13
    Gold               0
    Sivler             0
    Bronze             2
    Total              2
    #Winter            0
    Gold.1             0
    Sivler.1           0
    Bronze.1           0
    Total.1            0
    #Games            13
    Gold.2             0
    Sivler.2           0
    Bronze.2           2
    Combined total     2
    Name: Afghanistan (AFG), dtype: int64




```python
df['Gold']
```




    Afghanistan (AFG)                                  0
    Algeria (ALG)                                      5
    Argentina (ARG)                                   18
    Armenia (ARM)                                      1
    Australasia (ANZ) [ANZ]                            3
    Australia (AUS) [AUS] [Z]                        139
    Austria (AUT)                                     18
    Azerbaijan (AZE)                                   6
    Bahamas (BAH)                                      5
    Bahrain (BRN)                                      0
    Barbados (BAR) [BAR]                               0
    Belarus (BLR)                                     12
    Belgium (BEL)                                     37
    Bermuda (BER)                                      0
    Bohemia (BOH) [BOH] [Z]                            0
    Botswana (BOT)                                     0
    Brazil (BRA)                                      23
    British West Indies (BWI) [BWI]                    0
    Bulgaria (BUL) [H]                                51
    Burundi (BDI)                                      1
    Cameroon (CMR)                                     3
    Canada (CAN)                                      59
    Chile (CHI) [I]                                    2
    China (CHN) [CHN]                                201
    Colombia (COL)                                     2
    Costa Rica (CRC)                                   1
    Ivory Coast (CIV) [CIV]                            0
    Croatia (CRO)                                      6
    Cuba (CUB) [Z]                                    72
    Cyprus (CYP)                                       0
                                                    ...
    Sri Lanka (SRI) [SRI]                              0
    Sudan (SUD)                                        0
    Suriname (SUR) [E]                                 1
    Sweden (SWE) [Z]                                 143
    Switzerland (SUI)                                 47
    Syria (SYR)                                        1
    Chinese Taipei (TPE) [TPE] [TPE2]                  2
    Tajikistan (TJK)                                   0
    Tanzania (TAN) [TAN]                               0
    Thailand (THA)                                     7
    Togo (TOG)                                         0
    Tonga (TGA)                                        0
    Trinidad and Tobago (TRI) [TRI]                    2
    Tunisia (TUN)                                      3
    Turkey (TUR)                                      39
    Uganda (UGA)                                       2
    Ukraine (UKR)                                     33
    United Arab Emirates (UAE)                         1
    United States (USA) [P] [Q] [R] [Z]              976
    Uruguay (URU)                                      2
    Uzbekistan (UZB)                                   5
    Venezuela (VEN)                                    2
    Vietnam (VIE)                                      0
    Virgin Islands (ISV)                               0
    Yugoslavia (YUG) [YUG]                            26
    Independent Olympic Participants (IOP) [IOP]       0
    Zambia (ZAM) [ZAM]                                 0
    Zimbabwe (ZIM) [ZIM]                               3
    Mixed team (ZZX) [ZZX]                             8
    Totals                                          4809
    Name: Gold, Length: 147, dtype: int64




```python
df['Gold']>0
```




    Afghanistan (AFG)                               False
    Algeria (ALG)                                    True
    Argentina (ARG)                                  True
    Armenia (ARM)                                    True
    Australasia (ANZ) [ANZ]                          True
    Australia (AUS) [AUS] [Z]                        True
    Austria (AUT)                                    True
    Azerbaijan (AZE)                                 True
    Bahamas (BAH)                                    True
    Bahrain (BRN)                                   False
    Barbados (BAR) [BAR]                            False
    Belarus (BLR)                                    True
    Belgium (BEL)                                    True
    Bermuda (BER)                                   False
    Bohemia (BOH) [BOH] [Z]                         False
    Botswana (BOT)                                  False
    Brazil (BRA)                                     True
    British West Indies (BWI) [BWI]                 False
    Bulgaria (BUL) [H]                               True
    Burundi (BDI)                                    True
    Cameroon (CMR)                                   True
    Canada (CAN)                                     True
    Chile (CHI) [I]                                  True
    China (CHN) [CHN]                                True
    Colombia (COL)                                   True
    Costa Rica (CRC)                                 True
    Ivory Coast (CIV) [CIV]                         False
    Croatia (CRO)                                    True
    Cuba (CUB) [Z]                                   True
    Cyprus (CYP)                                    False
                                                    ...  
    Sri Lanka (SRI) [SRI]                           False
    Sudan (SUD)                                     False
    Suriname (SUR) [E]                               True
    Sweden (SWE) [Z]                                 True
    Switzerland (SUI)                                True
    Syria (SYR)                                      True
    Chinese Taipei (TPE) [TPE] [TPE2]                True
    Tajikistan (TJK)                                False
    Tanzania (TAN) [TAN]                            False
    Thailand (THA)                                   True
    Togo (TOG)                                      False
    Tonga (TGA)                                     False
    Trinidad and Tobago (TRI) [TRI]                  True
    Tunisia (TUN)                                    True
    Turkey (TUR)                                     True
    Uganda (UGA)                                     True
    Ukraine (UKR)                                    True
    United Arab Emirates (UAE)                       True
    United States (USA) [P] [Q] [R] [Z]              True
    Uruguay (URU)                                    True
    Uzbekistan (UZB)                                 True
    Venezuela (VEN)                                  True
    Vietnam (VIE)                                   False
    Virgin Islands (ISV)                            False
    Yugoslavia (YUG) [YUG]                           True
    Independent Olympic Participants (IOP) [IOP]    False
    Zambia (ZAM) [ZAM]                              False
    Zimbabwe (ZIM) [ZIM]                             True
    Mixed team (ZZX) [ZZX]                           True
    Totals                                           True
    Name: Gold, Length: 147, dtype: bool




```python
df['Gold']+df['Sivler']+df['Bronze']
```




    Afghanistan (AFG)                                   2
    Algeria (ALG)                                      15
    Argentina (ARG)                                    70
    Armenia (ARM)                                      12
    Australasia (ANZ) [ANZ]                            12
    Australia (AUS) [AUS] [Z]                         468
    Austria (AUT)                                      86
    Azerbaijan (AZE)                                   26
    Bahamas (BAH)                                      12
    Bahrain (BRN)                                       1
    Barbados (BAR) [BAR]                                1
    Belarus (BLR)                                      75
    Belgium (BEL)                                     142
    Bermuda (BER)                                       1
    Bohemia (BOH) [BOH] [Z]                             4
    Botswana (BOT)                                      1
    Brazil (BRA)                                      108
    British West Indies (BWI) [BWI]                     2
    Bulgaria (BUL) [H]                                214
    Burundi (BDI)                                       1
    Cameroon (CMR)                                      5
    Canada (CAN)                                      279
    Chile (CHI) [I]                                    13
    China (CHN) [CHN]                                 473
    Colombia (COL)                                     19
    Costa Rica (CRC)                                    4
    Ivory Coast (CIV) [CIV]                             1
    Croatia (CRO)                                      23
    Cuba (CUB) [Z]                                    209
    Cyprus (CYP)                                        1
                                                    ...  
    Sri Lanka (SRI) [SRI]                               2
    Sudan (SUD)                                         1
    Suriname (SUR) [E]                                  2
    Sweden (SWE) [Z]                                  483
    Switzerland (SUI)                                 185
    Syria (SYR)                                         3
    Chinese Taipei (TPE) [TPE] [TPE2]                  21
    Tajikistan (TJK)                                    3
    Tanzania (TAN) [TAN]                                2
    Thailand (THA)                                     24
    Togo (TOG)                                          1
    Tonga (TGA)                                         1
    Trinidad and Tobago (TRI) [TRI]                    18
    Tunisia (TUN)                                      10
    Turkey (TUR)                                       88
    Uganda (UGA)                                        7
    Ukraine (UKR)                                     115
    United Arab Emirates (UAE)                          1
    United States (USA) [P] [Q] [R] [Z]              2399
    Uruguay (URU)                                      10
    Uzbekistan (UZB)                                   20
    Venezuela (VEN)                                    12
    Vietnam (VIE)                                       2
    Virgin Islands (ISV)                                1
    Yugoslavia (YUG) [YUG]                             83
    Independent Olympic Participants (IOP) [IOP]        3
    Zambia (ZAM) [ZAM]                                  2
    Zimbabwe (ZIM) [ZIM]                                8
    Mixed team (ZZX) [ZZX]                             17
    Totals                                          14714
    Length: 147, dtype: int64

<br>

#### *__etc...__*
그 외에도 아래와 같은 코드가 있다.
간단히 설명하면 sdf에 df의 칼럼중 'Gold'인 칼럼 데이터가 15인 df의 칼럼을 할당한다.
그리고 sdf의 최대값과 갯수 등을 조회하는 코드이다.
```python
sdf = df[(df['Gold'] == 15)]
sdf.max()
sdf.count()
sdf.argmax()
```
