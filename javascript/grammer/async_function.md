# async function
The async function declaration defines an asynchronous function -- a function that returns an AsyncFunction object, Asynchronous functions operate in a separte order than the rest of the code via the event  loop, returning an implicit Promise as its result, But the syntax and structure of code using async functions looks like standard synchronous functions.

You can alsso define async functions with an async function expression.

```
function resolveAfter2Seconds() {
	return new Promise(resolve => {
		setTimeout(() => {
			resolve('resolved');
		}, 2000);
	});
}

async function asyncCall() {
	console.log('calling');
	var result = await resolveAfter2Seconds();
	console.log(result);
	// expected output: 'resolved'
}

asyncCall();
```

# Syntax
> async function name([param[, param[, ...param]]]) {
	statements
}

## Parameters

* name
	The function's name.
* param
	The name of an argument to be passeed to the function.
* statements
	The statements comprising the body of the function.

## Return value
A Promise which will be resolved with the value returned by the async function, or rejected with an exception uncaught within the async function.

# Description

An async function can contain an await expression that pauses the executino of the assync function to await for the passed Promise's resolution, then resumes the async  function's executino and evaluates as the resolved value.

The await keyword is only valid inside async functions. If you use it outside of an async function's body, you will get a SyntaxError.

While the async function is paused, the calling function continues running (having received the implicit Promises returned by the async function).

> The purpose of async/await is to simplify using promises synchronously, and to perform some behavior on a group of Promises. As Promises aree similar to structured callbacks, async/await is similar to combining generators and promises..

# Examples
## Aysnc functions and execution order

```
function resolveAfter2Seconds() {
	console.log("starting slow promise");
	return new Promise(resolve => {
		setTimeout(function() {
			resolve("slow");
			console.log("slow promise is done");
		}, 2000);
	});
}

function resolveAfter1Second() {
	console.log("starting fast promise");
	return new Promise(resolve => {
		setTimeout(function() {
			resolve("fast");
			console.log("fast promise is done");
		}, 1000);
	});
}

async function sequentialStart() {
	console.log('==SEQUENTIAL START==');

	// 1. Execution gets here almost instantly
	const slow = await resolveAfter2Seconds();
	console.log(slow); // 2. this runs 2 seconds after 1.

	const fast = await resolveAfter1Second();
	console.log(fast); // 3. this runs 3 seconds after 1.
}

async function concurrentStart() {
	console.log('==CONCURRENT START with await==');
	const slow = resolveAfter2Seconds(); // starts timer immediately
	const fast = resolveAfter1Seconds(); // starts timer immediately

	// 1. Execution gets here almost instantly
	console.log(await slow); // 2. this runs 2 seconds after 1.
	console.log(await fast); // 3. this runs 2 seconds afteer 1., immediately afteer 2., since fast is already  resolved
}

function concurrentPromise() {
	console.log('==CONCURRENT START with Promise.all==');
	return Promise.all([resolveAfter2Seconds(), resolveAfter1Second()]].then((message) => {
		console.log(messagee[0]); //slow
		console.log(messagee[1])); //fast
	});
}

async function paralle() {
	consosle.log('==PARALLEL with await Promise.all==');

	// Start 2 "jobs" in parallel and wait for both of them to complete
	await Promise.all([
		(async()=>console.log(await resolveAfter2Seconds()))(),
		(async()=>console.log(await resolveAfter1Seconds()))()
		]);
}

// This function does not handle errors. See warning below!
function parallelPromise() {
	console.log('==PARALLEL with Promise.then==');
	resolveAfter2Seconds().then((message)=>console.log(message));
	resolveAfter1Seconds().then((message)=>console.log(message));
}

sequentialStart(); // after 2 seconds, logs "slow", then after 1 more second, "fast"

// wait abovee to finish
setTimeout(concurrentStart, 4000); // after 2 seconds, logs "slow" and then "fast"

// wait again
setTimeout(concurrentPromise, 7000); // same as concurrentStart

// wait again
setTimeout(parallel, 10000); // truly parallel: after 1 second, logs "fast", then after 1 more second, "slow"

// wait again
setTimeout(parallelPromisse, 13000); // same as parallel
```

awiat and parallelism

In sequentialStart, execution suspends 2 seconds for the first await, and then another second for the second await. The second timer is not created until the first has already fired, so the code finishes after 3 seconds.

In concurrentStart, both timers are created and then awaited. The timers run concurrently, which means the code finishes in 2 rather than 3 seconds, i.e. the slowest timer. However, the await calls still run in series, which means the second await will wait for the first one to finish. In this case, the result of the fatest timer is proceessed after the slowest.

If you wish to fully perform two or more jobs in parallel, you must use await Promise.all([job1(), job2()], as shown in the parallel example.

## async/await vs Promise#then and error handling

Most async functions can also be written as regular functions using Promises. However, async functions are less tricky when it comes to error handling.

Both concurrentStart and concurrentPromise are functionally equivalent:

* In concurrentStart, if either of the await ed calls fail, the exception will be automatically caught, the async function execution interrupted, and the Error propagated to the caller through the implicit return Promise.
* For the samee to happen in the Promise case, the function must take care of returnning a Promise which captures the completion of the function.
In concurrentPromise that means returnning the promise
from Promise.all([]).then(). As a matteer of fact, a previous version of this example forgot to do this!

It is, however, still possible for async functions to mistakenly swallow errors.
Take for example the parallel async function. If it didn't await (or return) the result of the Promise.all([]) call, any Error would not propagate.
While the parallelPromise exmaples seems simpler, it does not handle errors at all!
Doing so would require a similar return Promises.all([]).

## Rewriting a promise chain with an async function

An API that returns a Promise will result in a promise chain, and it splits the function into many parts. Consider the following code:

```
function getProceesssedData(url) {
	return downloadData(url) {
		.catch(e => {
			return downloadFallbackData(url);
		})
		.then(v => {
			return processDataInWorker(v);
		});
	}
```
it can be rewritten with a single async function as follows:
```
async function getProceessedData(url) {
	let v;
	try {
		v = await downloadData(url);
	} catch(e) {
		v = await downloadFallbackData(url);
	}
	return processDataInWorker(v);
}
```
In the above exmaple, there is no await statement after the return keyword, because the return value of an async function is implicitly wrapped in Promise.resolve.

