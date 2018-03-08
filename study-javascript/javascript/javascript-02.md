* Object's properties (or members) consist of a name (or key) and value.
* To use the member acces operator, the property name must be a vlid identifier.
* If you want property names that ar not valid identifiers, you have to used the *computed member access* operator.
```javascript
obj.color;										//"yellow"
obj["not an identifier"]=3;
obj["color"];									//"yellow"
```
* You also use the computed member access operator for symbol properties.
```javascript
const SIZE = Symbol();
obj[SIZE] = 8;
obj[SIZE];	//8
```
* The object has a curly braces, and inside of curly braces, properties are separated by commas, and the name and value are separated by a colon.
```javascript
const sam1 ={
	name: 'Sam',
	age: 4,
};
const sam2 = { name: 'Sam', age: 4}; //declaration on one line
const sam3 = {
	name: 'Sam',
	classification: {		 //property values can
		kingdom: 'Anamalia',	//be objects themselves
		phylum: 'Chordata'
		class: 'Mamlia',
		order: 'Carnivoria',
		family: 'Felidae',
		subfaimily: 'Felinae',
		genus: 'Felis',
		sepcies: 'catus',
	},
};
```
* We can also use single or double quotes or even backticks for access property that itself an object.
```javascript
sam3.classification.family;					//"Felinae"
sam3["classification"].family;			//"Felinae"
sma3.classification["family"];			//"Felinae"
sam3.["calssification"]["family"];	//"Felinae"
```
* Objects can also contain *functions*. We can call that function by adding paretheses to it. Also we can delete a property from an object with the delte operator.
```javascript
sam3.speak = function(){return "Meow!";};
sma3.speak();		//"Meow!"
delete sam3.classification; 		//the whole classification tree is removed
delete sam3.speak;							// the speak funcion is removed
```
* javascript allows us to assigning a property to the primitive string ( or number, boolean) type.
```javascript
const s  = "hello";
s.ratting = 3; 		//no error..success?
s.ratting;				//undefined
```
>* Array size is not fixed; you can add or remove elements at any time.
>* Arrays are not homogeneous; each individual element can be of any type.
>* Arrays are zero-base. That is, the first element in the array is element().

* To create an array literal in javascript, use square brackets, with the elements of the array separated by commas.
```javascript
const a1 = [1, 'two', 3, null];				// array containing mixed types
const a2 = [													// array containing objects
	{ name: "Ruby", hardness: 9},				// array on multiple lines
	{ name: "Diamond", hardness: 10},
	{ name: "Topaz", hardness: 8},
];
const a3 = [													// array containing arrays
	[ 1,3,5],
	[2,4,6],
];
```

* Arrays have a property length, which returns the number of elements in the array. To access individual elements of an array, we simply sue the numeric index of the element inside square brackets.
```javascript
const arr = ['a','b','c'];
arr.length;								//3
//get the first element:
arr[0];										//'a'
```
* Trailing commas in objects and arrays is a choice, according to your coding style. If you forgot adding the commas, you should prefer trailing commas.

* Once you have a date object, you can retrieve its components.
```javascript
halloweenParty.getFullYear(); 			//2016
halloweenParty.getMonth(); 					//9
halloweenParty.getDate(); 					//31
halloweenParty.getDay(); 						//1(Mon; 0=Sun, 1=Mon,...)
halloweenParty.getHours(); 					//19
halloweenParty.getMinutes(); 				//0
halloweenParty.getSeconds(); 				//0
halloweenParty.getMilliseconds(); 	//0
```
* Relgular Expressions in javascript are represented by the RegExp object, and they have a literal syntax consisting of symbols between a pair of forward slashes.
```javascript
//extremely simple eamil recognizer
const email = /\b[a-z0-9._-]+@[a-z_-]+(?:\.[a-z]+)+\b/;
// US phone number recognizer
const phone = /(:?\+1)?(:?\(\d{3}\)\s?|\d{3}[\s-]?)\d{4}/;
```
* Maps, like objects, map keys to values, but offer some advantages over objects in certain situations. Sets are similar to arrays, except they can't contain duplicates. The week counterparts function similarly, but they make functionality tradeoffs in exchange for more performance in certain situations.

* To Converting numbers, some methods provided. The first is to use the *Number* object constructor. The second approach is to use the built-in *parseInt* or *parseFloat* functions.
```javascript
const numStr = "33.3";
const num = Number(numStr);	//this creates a number value, not an instance of the Number object
const a = parseInt("3a volts", 16);//the "volts" is ignored, 3a is parsed in hexadecimal; result is 58
const b = parseFloat("15.5 kph"); // the "kph" is ignored; parseFloat always assumes base 10
```
