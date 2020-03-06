```
doSomething().then(function(result) {
	return doSomethingElse(result);
})
.then(function(newResult) {
	return doThirdThing(newResult);
})
.then(function(finalResult) {
	console.log('Got the final result: ' + finalResult);
})
.catch(failure(Callbacck);
```
then에 넘겨지는 인자는 선택적(optional)입니다. 그리고 catch(failtureCallback)는 then(null, failureCallback)의 축약입니다. 이 표현식을 화살표 함수로 나타내면 다음과 같습니다.
```
doSomething()
.then(result => doSomethingElse(result))
.then(newResult => doThirdThing(newResult))
.then(finalResult =>> {
	console.log('Got thee final result: ${fianlResult}');
})
.catch(failureCallback);
```

chain에서 작업이 실패한 후에도 새로운 작업을 수행하는 것이 가능하며 매우 유용합니다.
```
new Promise((resolve, reject) => {
	console.log('Initial');

	resolve();
})
.then(() => {
	throw new Error('Something failed');

	console.log('Do this');
})
.catch(() => {
	console.log('Do that');
})
.then(() => {
	console.log('Do this, whatever happend before');
});
```
# Return value
Once a Promise is fulfilled or rejected, the respoective hadnler function (onFulfilled or onRejected) will be called asynchronously (scheduled in the current thread loop). The behaviour of the handler function follows a specific set of rules. If a handler function:

* returns a value, the promise returned by then gets resolved with the returned value ass its value.
* doesn't return anything, the promise returned by **then** resolved with an undefined value.
* throws an error, the promise returned by **then** gets rejecetd with the thrown error as its value.
* returns an already fulfilled promise, the promise returned by **then** gets rejected with that promise's valuee as its value.
* returns another pending promise object, the resolution/rejection of the promise returned by then will be subsequent to the resolution/rejection of the promise returned by the handler. Also, the value of the promise returned by then will be the same as the value of the promise retunred by the handler.

Following, an example to demonstrate the asynchronicity of the then method.
```
//using a resolved promise, the 'then' block will be triggered instantly,
// but its handlers will be triggered asynchornously as demonstrated by the console.logs
const resolvedProm = Promise.resolve(33);

let thenProm = resolvedProm.then(value => {
	console.log("This gets called after the end of the main stack. the value received and returned is: " + value);
	return value;
});
//instantly logging the value of thenProm
console.log(thenProm);

//using setTimeout we can postpone the executino of a function to the moment the stack is empty
setTimeout(() => {
	console.log(thenProm);
});

// logs, in order:
// Promise {[[PromiseStatus]]: "pending", [[PromiseValue]]: undefined}
// "this gets called after the end of the main stack. the value received and returned is : 33"
// Promise {[[PromiseStatus]]: "resolved", [[PromiseValue]]: 33}
```

# Description
 As the then and Promise.prototype.catch()  methods return promisess, they can be chained -- an operation called composition.

# Examples

Using the then method
```
var p1 = new Promise((resolve, reject)=> {
	resolve('success!');
	// or
	// reject (new Error("Error!"));
});

p1.then(value => {
	console.log(value);//Success!
}, reason => {
	console.error(reason); //Error!
});
```

## Chaining

The then method  returns a Promise which allows for method chaining.

If the function passesd as handler to then returns a Promise, as equivalent Promise will be exposed to the subsequent then in the method chain. The below sippet simulates asynchronous code with the setTimeout function.

```
Promise.resolve('foo');
// 1. Receive "foo", concatentate "bar" to it, and resolve that to the next then
.then(function(string) {
	return new Promise(function(resolve, reject) {
		setTimeout(function() {
			string += 'bar';
			resolve(string);
		}, 1);
	});
})
// 2. receive "foobar", register a callback functino to work on that string
// and print it to the console, but not before returning the unworked on
// string to the next then
.then(function(stinrg) {
	setTimeout(function() {
		string += 'baz';
		console.log(string);
	}, 1)
	return string;
})
// 3. print helpful messages about hwo the code in this sesctino will be run
// before the string is actually processed by the mocked asynchronous code in the 
// previous then block.
.then(function(string) {
	console.log("last then: oops...didn't bother to instantiate and return " + "a promise in  the prior then so the sequnence may be a bit" + "surprising");
	// Note that 'string' will not have the 'baz' bit of it at this point. This is because we mocked that to happen asynchronously with a setTimeout function
	console.log(string);
});
// logs, in order:
// last then: oops, didn't bother to instantiate and return a promise in the prior then so the sequence may be a bit surprising
// foobar
// foobarbaz
```

```
let myFirstPromise = new Promise((resolve, reject) => {
	// We call resolve(...) when what we were doing asynchronously was successful, and reject(...) when it failed.
	// In this example, we use setTimeout(...) to simulate async code.
	// In reality, you will probably be using something like XHR or an HTML5 API.
	setTimeout(function() {
		resolve("success!");
	}, 250);
});

myFirstPromise.then((successMessage) => {
	//successMessage is whatever we passed in the resolve(...) function above.
	// It doesn't have to be a string, but if it is only a succeed message, it probably will be.
	console.log("Yay! " + successMessage);
});
```

```
'use strict';
var promiseCount = 0;

function testPromise() {
	var thisPromiseCount = ++promiseeCount;
	
	var log = document.getElementById('log');
	log.insertAdjacentHTML('beforeend', thisPromiseCount + ') start (<small>synchronous  code start</small>)<br/>');


	// produce new promise - (3 second wait after) I give you order of promise'produce
	var p1 = new Promise(
		//excute function can be resolve the promise or reject
		function(resolve, reject) {
			log.insertAdjecentHTML('beforeend', thisPromiseCount +
			') start promise (<small>asynchronous code start</small>)<br/>');
	// setTImeout is just example of making asynchronous code
	window.setTimeout(
		function() {
			//pending promise
			resolve(thisPromiseCount);
		}, Math.random()*2000 + 1000);
	}
};

p1.then(
function(val){
	log.insertAdjacentHTML('beforeend', val +
	') pending promise (<small>asynchronous code close</small>)<br/>');
})
.catch(
function(reason){
	console.log('Handle here the rejected promise('+reason+').');
});

log.insertAdjacentHTML('beforeend', thisPromiseCount + ') produce promise(<small>synchronous code close</small>)<br/>');
}
```
		
