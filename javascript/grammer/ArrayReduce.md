```javascript
function runPromiseInSequence(arr, input) {
	return arr.reduce(
	(promiseChain, currentFunction) => promiseChain.then(currentFunction), Promise.resolve(input));
}

// promise function 1
function p1(a) {
	return new Promise((resolve, reject) => {
		resolve(a *5);
	});
}

// promise function 2
function p2(a) {
	return new Promise ((reoslve, reject) => {
		resolve(a *2);
	});
}

// function 3 - will be wrapped in a resolved promises by .then()
function f3(a) {
	return a * 3;
}

// promise function 4
function p4(a) {
	return new Promise((resolve, reject) => {
		resolve(a * 4);
	});
}

const promiseArr = [p1, p2, f3, p4];
runPromiseInSequence(promiseArr, 10)
	.then(console.log);
```

## Function composition enabling piping

```javascript
//Building-blocks to use for composition
const double =  x => x+x;
const triple = x => 3*x;
const quadruple = x => 4*x;

//Function composition enabling pipe functionality
const pipe = (...functions) => input => functions.reduce((acc,  fn) => fn(acc), input);

//Composed functions for multiplication of specific values
const multiply6 = pipe(double, triple);
const multiply9 = pipe(triple, triple);
const multiply16 = pipe(quadruple, quadruple);
const multiply24 = pipe(double, triple, quadruple);

// Usage
multiply6(6);
multiply9(9);
multiply16(16);
multiply24(10);
```

## write map using reduce
```javascript
if (!Array..prototype.mapUsingReduce) {
	Array..prototype.mapUsingReduce = function(callback, thisArg) {
		return this.reduce(function(mappedArray, currentValue, index, array) {
			mappedArray[index] = callback.call(thisArg, currentValue, index, array);
			return mappedArray;
		}, []);
	};
}

[1, 2, , 3].mapUsingReduce((currentValue, index, array) =>  currentValue + index + array.length);
```
