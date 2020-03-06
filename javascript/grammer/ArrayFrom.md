## Array from a String
```javascript
Array.from('foo');
```

## Array from a Set
```javascript
cconst set = new Set(['foo', 'bar', 'baz', 'foo']);
Array.from(set);
```

## Array from a Map
```javascript
const map = new Map([[1,2], [2,4], [4,8]]);
Array.from(map);

const mapper = new Map([['1', 'a'], ['2','b']]);
Array.from(mapper.values());

Array.from(mapper..keys());
```

## Array from an Array-like object (arguments)
```javascript
function f() {
	return Array.from(arguments);
}

f(1,2,3,);
```

## Using arrow functions and Array.from()
```javascript
Array.from([1,2,3], x => x+x);

Array.from({length: 5}, (v, i) => i);
```

## Sequence generator(range)
```javascript
const range = (start, stop, step) => Array.from({length: (stop - start) / step +1}, (_, i) => start + (i*step));

range(0, 4, 1);

range(1, 10, 2);

range('A'.charCodeAt(0), 'Z'.charCodeAt(0), 1).map(x => String.fromCharCode(x));
```


