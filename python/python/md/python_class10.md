

```python
import urllib.request
from bs4 import BeautifulSoup
import pandas as pd
import datetime
```


```python
def get_request_url(url, enc='utf-8'):
    req = urllib.request.Request(url)

    try:
        response = urllib.request.urlopen(req)
        if response.getcode() == 200:
            try:
                rcv = response.read()
                ret = rcv.decode(enc)
            except UnicodeDecodeError:
                ret = rcv.decode(enc, 'replace')

            return ret

    except Exception as e:
        print(e)
        print("[%s] Error for URL : %s" % (datetime.datetime.now().url))
        return None    
```


```python
def getBBQAddress(result):
    BBQ_URL = 'https://www.bbq.co.kr/shop/shop_ajax.asp?page=1&pagesize=2000&gu=&si='
    print(BBQ_URL)

    rcv_data = get_request_url(BBQ_URL)
    soupData = BeautifulSoup(rcv_data, 'html.parser')

    tbody = soupData.find('tbody')

    tr_tag = []

    for store_tr in tbody.findAll('tr'):
        tr_tag =list(store_tr.strings)
        store_name = tr_tag[1]
        store_address = tr_tag[3]
        store_sido = store_address.split()[0]
        store_gu = store_address.split()[1]
        result.append([store_name] + [store_sido] + [store_gu] + [store_address])
    return
```


```python
def main():
    result = []
    getBBQAddress(result)
    bbq_table = pd.DataFrame(result, columns=('store', 'sido', 'gungu', 'store_address'))
    bbq_table.to_csv("bbq.csv", encoding="cp949", mode='w', index=True)
```


```python
if __name__ == '__main__':
    main()
```

    https://www.bbq.co.kr/shop/shop_ajax.asp?page=1&pagesize=2000&gu=&si=

