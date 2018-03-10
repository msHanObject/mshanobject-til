Python class *__2__*
===

### *__Pandas__*

이번에는 데이터 구조를 구성하고 데이터 조작과 분석을 해보는 시간을 갖도록 하겠습니다.
이를 위해 필요한 작업으로 `pandas`를 `import`해야 합니다.

```python
import pandas as pd
```
위 코드는 단순히 import 하는 것이 아니라 `pd`라는 변수로서 `pandas`를 import하여 사용하겠다는 의미입니다.
<br>

### *__Series & DataFrame__*
Pandas에서는 데이터를 효과적으로 다루기 위해 Series와 DataFrame을 제공합니다. Pandas의 Series는 1차원 데이터를 다루는 데 효과적인 자료구조이며, DataFrame은 행과 열로 구성된 2차원 데이터를 다루는 데 효과적인 자료구조입니다.
<br>


#### *__Series__*
아래 코드와 같이 `Series`를 통해 1차원 데이터를 선언하여 사용할 수 있습니다.
```python
purchase_1 = pd.Series({'Name':'Chris','Item':'DogFood','Cost':22.50})
```

```python
purchase_2 = pd.Series({'Name':'Michel','Item':'CatMilk','Cost':15.75})
```

```python
purchase_3 = pd.Series({'Name':'Tim','Item':'Bread','Cost':8.65})
```
<br>

#### *__DataFrame__*
그리고 아래와 같이 `DataFrame`을 사용하여 2차원 데이터를 선언하여 사용할 수 있게 됩니다.

```python
df=pd.DataFrame([purchase_1,purchase_2,purchase_3],
   index=['Store1','Store2','Store3'])
print(df)
```


<div>
<style>
    .dataframe thead tr:only-child th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>15.75</td>
      <td>CatMilk</td>
      <td>Michel</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>


<br>

#### *__Pandas.DataFrame.head()__*
그리고 DataFrame에는 `head()` 라는 함수가 있는데 이 함수는 방대한 자료 중에 앞부분에 위치한 일부만을 보여주는 기능을 합니다. 아래에서는 DataFrame에 들어 있는 자료가 크지 않아서 그냥 출력한 것과 동일한 결과를 얻게 되었습니다.

```python
df.head()
```

<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>15.75</td>
      <td>CatMilk</td>
      <td>Michel</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>

<br>

#### *__Pandas.DataFrame.loc()__*
DataFrame의 `loc()` 이라는 함수는 특정 location의 데이터들을 발췌하여 리턴해주는 기능을 합니다.

```python
df.loc['Store2']
```




    Cost      15.75
    Item    CatMilk
    Name     Michel
    Name: Store2, dtype: object




```python
df.loc['Store1']
```




    Cost       22.5
    Item    DogFood
    Name      Chris
    Name: Store1, dtype: object




```python
df.loc['Store1','Cost']
```

    22.5


<br>

#### *__Pandas.DataFrame.T__*
그리고 DataFrame의 편리한 기능 중 하나를 소개하겠습니다.
2차원 자료구조를 다루다 보면 index와 columns들을 서로 바꿔서 자료구조를 만들고 싶을 때가 간혹 있습니다.
이럴때 아주 쉽고 편하게 바꾸어 주는 함수가 있습니다. 바로 ... `pandas.DataFrame.T`입니다.
사용법은 아래 코드와 같이 이미 선언되어 있는 DataFrame에 `.T`만 붙여주면 끝! 입니다.

```python
df.T
```


<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Store1</th>
      <th>Store2</th>
      <th>Store3</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Cost</th>
      <td>22.5</td>
      <td>15.75</td>
      <td>8.65</td>
    </tr>
    <tr>
      <th>Item</th>
      <td>DogFood</td>
      <td>CatMilk</td>
      <td>Bread</td>
    </tr>
    <tr>
      <th>Name</th>
      <td>Chris</td>
      <td>Michel</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>

<br>

#### *__혼자해보기__*
이제 잠시 여태 배운 내용들을 가지고 2차원 자료구조를 분석해도록 하겠습니다.

```python
df['Cost']
```




    Store1    22.50
    Store2    15.75
    Store3     8.65
    Name: Cost, dtype: float64




```python
df.loc['Store1']['Cost']
```




    22.5




```python
df.loc[:,['Name','Cost']]
```




<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Name</th>
      <th>Cost</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>Chris</td>
      <td>22.50</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>Michel</td>
      <td>15.75</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>Tim</td>
      <td>8.65</td>
    </tr>
  </tbody>
</table>
</div>


<br>

#### *__Pandas.DataFrame.drop()__*

이제는 자료구조 자체를 수정하는 방법들을 배워보도록하겠습니다.

자료구조를 다루다 보면 불필요한 인덱스들을 없애고 싶을 때가 있을 겁니다.
이때 사용하면 되는 함수가 drop('인덱스이름')입니다.
```python
df.drop('Store2')
```




<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>

<br>

#### *__Pandas.DataFrame.copy()__*
DataFrame을 복사하고 싶을 때에는 `copy()`라는 함수를 drop과 마찬가지로 사용하면 됩니다.

```python
copy_df=df.copy()
print(copy_df)
```


<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>15.75</td>
      <td>CatMilk</td>
      <td>Michel</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>

<br>

#### *__컬럼 추가하기__*
DataFrame에 columns을 추가 하고 싶을 때는 아래와 같이 그냥 원래 있던것 처럼 선언하면 됩니다.

```python
df['Location']=None
print(df)
```

<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
      <th>Location</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
      <td>None</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>15.75</td>
      <td>CatMilk</td>
      <td>Michel</td>
      <td>None</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
      <td>None</td>
    </tr>
  </tbody>
</table>
</div>


<br>

#### *__컬럼 삭제하기__*
방금 추가한 컬럼을 삭제하는 방법은 아래 코드와 같이 삭제할 컬럼을 명시하고 앞에 `del`을 선언해주면 됩니다.
```python
del df['Location']
print(df)
```

<div>
<style>
.dataframe thead tr:only-child th {
    text-align: right;
}
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>Cost</th>
      <th>Item</th>
      <th>Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Store1</th>
      <td>22.50</td>
      <td>DogFood</td>
      <td>Chris</td>
    </tr>
    <tr>
      <th>Store2</th>
      <td>15.75</td>
      <td>CatMilk</td>
      <td>Michel</td>
    </tr>
    <tr>
      <th>Store3</th>
      <td>8.65</td>
      <td>Bread</td>
      <td>Tim</td>
    </tr>
  </tbody>
</table>
</div>



<br>

#### *__컬럼,인덱스 단위로 데이터 수정하기__*
데이터를 수정할 때 개별로 수정할 때도 있지만 컬럼이나 인덱스 단위로 한번에 수정을 하고 싶을때가 있습니다.
그럴 때는 수정할 컬럼이나 인덱스를 선언한뒤 수정할 내용을 덧붙이기만 하면 됩니다.
아래 코드는 Integer 타입의 Cost라는 컬럼을 한번에 +2 해주는 코드입니다.
```python
df['Cost']+2
```




    Store1    24.50
    Store2    17.75
    Store3    10.65
    Name: Cost, dtype: float64
