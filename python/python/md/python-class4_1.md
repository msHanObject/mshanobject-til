

```python
import pandas as pd
```


```python
df = pd.read_csv('/Users/nulgrace/workspace/python/res/subway201704.csv', encoding='euc-kr')
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
      <th>역명</th>
      <th>날짜</th>
      <th>구분</th>
      <th>05~06시</th>
      <th>06~07시</th>
      <th>07~08시</th>
      <th>08~09시</th>
      <th>09~10시</th>
      <th>10~11시</th>
      <th>11~12시</th>
      <th>...</th>
      <th>16~17시</th>
      <th>17~18시</th>
      <th>18~19시</th>
      <th>19~20시</th>
      <th>20~21시</th>
      <th>21~22시</th>
      <th>22~23시</th>
      <th>23~24시</th>
      <th>00~01시</th>
      <th>01~02시</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>서울역(150)</td>
      <td>2017-04-01</td>
      <td>승차</td>
      <td>493</td>
      <td>513</td>
      <td>654</td>
      <td>1,517</td>
      <td>2,104</td>
      <td>2,522</td>
      <td>3,440</td>
      <td>...</td>
      <td>4,835</td>
      <td>5,653</td>
      <td>4,178</td>
      <td>3,189</td>
      <td>2,640</td>
      <td>2,947</td>
      <td>2,441</td>
      <td>927</td>
      <td>43</td>
      <td>0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>서울역(150)</td>
      <td>2017-04-01</td>
      <td>하차</td>
      <td>385</td>
      <td>2,074</td>
      <td>1,923</td>
      <td>2,511</td>
      <td>3,377</td>
      <td>3,021</td>
      <td>3,006</td>
      <td>...</td>
      <td>3,813</td>
      <td>3,665</td>
      <td>3,571</td>
      <td>3,006</td>
      <td>2,137</td>
      <td>2,029</td>
      <td>1,473</td>
      <td>838</td>
      <td>278</td>
      <td>0</td>
    </tr>
    <tr>
      <th>2</th>
      <td>서울역(150)</td>
      <td>2017-04-02</td>
      <td>승차</td>
      <td>342</td>
      <td>340</td>
      <td>413</td>
      <td>944</td>
      <td>1,533</td>
      <td>1,734</td>
      <td>2,553</td>
      <td>...</td>
      <td>3,525</td>
      <td>3,668</td>
      <td>3,154</td>
      <td>2,879</td>
      <td>2,572</td>
      <td>3,038</td>
      <td>2,665</td>
      <td>965</td>
      <td>86</td>
      <td>0</td>
    </tr>
    <tr>
      <th>3</th>
      <td>서울역(150)</td>
      <td>2017-04-02</td>
      <td>하차</td>
      <td>197</td>
      <td>1,229</td>
      <td>1,119</td>
      <td>1,369</td>
      <td>1,958</td>
      <td>1,996</td>
      <td>1,926</td>
      <td>...</td>
      <td>3,068</td>
      <td>3,190</td>
      <td>2,721</td>
      <td>2,713</td>
      <td>1,994</td>
      <td>1,629</td>
      <td>1,397</td>
      <td>640</td>
      <td>138</td>
      <td>0</td>
    </tr>
    <tr>
      <th>4</th>
      <td>서울역(150)</td>
      <td>2017-04-03</td>
      <td>승차</td>
      <td>467</td>
      <td>603</td>
      <td>2,137</td>
      <td>3,296</td>
      <td>2,121</td>
      <td>2,094</td>
      <td>2,626</td>
      <td>...</td>
      <td>3,225</td>
      <td>4,466</td>
      <td>8,952</td>
      <td>5,342</td>
      <td>3,376</td>
      <td>3,402</td>
      <td>2,235</td>
      <td>1,174</td>
      <td>148</td>
      <td>0</td>
    </tr>
  </tbody>
</table>
<p>5 rows × 24 columns</p>
</div>


