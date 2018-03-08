# Learning Javascript Day 1
## Chpater 3

Study for Literal, Data Type, Variales, Constants.


* The *let* keyword is new in ES6;

* Prior to ES6, the only option was the *var* keyword.

* Variales constants.
```javascript
const c = 3.0e6;          // exponential (3.0 × 10^6 = 3,000,000)
const e = -1.6e-19;       // exponential (-1.6 × 10^-19 = 0.00000000000000000016)
const inf = Infinity;
const ninf = -Infinity;
const nan = NaN;          // "not a number"
```
```javascript
const small = Number.EPSILON;            // the smallest value that can be                                         // added to 1 to get a distinct number                                         // larger than 1, approx. 2.2e-16
const bigInt = Number.MAX_SAFE_INTEGER;  // the largest representable integer
const max = Number.MAX_VALUE;            // the largest representable number
const minInt = Number.MIN_SAFE_INTEGER;  // the smallest representable integer
const min = Number.MIN_VALUE;            // the smallest representable number
const nInf = Number.NEGATIVE_INFINITY;   // the same as -Infinity
const nan = Number.NaN;                  // the same as NaN
const inf = Number.POSITIVE_INFINITY;    // the same as Infinity
```

* The *const* also holds a value, but unlike a variale, can't be changed after initialization.
 ```javascript
const ROOM_TEMP_C = 21.5, MAX_TEMP_C = 30;
```
* It is conventional for constants that refer to a specific number or string to be named with all uppercase letters and underscore.

* No matter what literal format you use (decimal, hexadecimal, exponential, etc.), the number that gets created is stored in the same format: a double.

* we can escape quotation marks with a backslash (\), which is a signal to JavaScript that the string is not ending.
```javascript
const dialog1 = "He looked up and said \"don't do that!\" to Max.";
```
* when we want to use a backslash in our string. it can escape itself
```javascript
const s = "In JavaScript, use \\ as an escape character in strings.";
```

Table 3-1. Commonly used special characters

Code|Description|Example
---|---|---
\n|Newline|"Line1\nLine2"
\r|Carriage return|"Windows line1\r\nWindows line2"
\$|Dollar sign|'New in ES6: ${interpolation}'
\\\\|backslash|"Use \\\\\\\\ to represent \\\\!"
\uXXXX|Arbitrary Unicode code print|"De Morgan's law: \u2310(P \u22c0Q)"
\xXX|Latin-1 character|"\xc9p\xe9e is fun"

Table 3-2. Rarely used special characters.

Code|Description|Example
---|---|---
\0|The Nul character|"ASCII NUL: \0"
\v|Vertical tab|"Vertical tab: \v"
\b|Backspace|"Backspcae: \b"
\f|Form feed|"Form feed: \f"

* Template strings
```javascript
let currentTemp = 19.5;
//00b0 is the Unicode code point for the "degree" symbol
const message ="The cuurent template  is " + cuurentTemp + "\u00b0";
```

* String template use backticks instead of single or double quotes.
```javascript
let currentTemp = 19.5;
const messgae = 'The current temperature is ${currentTemp}\u00b0C';
```

* Multiline strings
```javascript
const multiline1 ="line1\
lin2";
const muliline2="line1\n\
line2";
const multiline3=`line1
line2`;
```
* javascript allows any value to be considered "truthly" or "falsy".(discussed in Chapter 5)

* Symbols, new in ES6 are : a new data type representing unique tokens. Symbols are created with the Symbol() constructor.
```javascript
const RED = Symbol();
const ORANGE = Symbol("The color of a sunset!");
RED === ORANGE // false: every symbol is unique
```
* javasciprt has two special types, *null* and *undefined*. Both *null* and *undefined* represent something that doesn't exist. 
