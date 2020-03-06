function myFunction(x, y, z) {}
var args = [0, 1, 2];
myFunction.apply(null, args);

function myFunction(x, y, z) {}
var args = [0,1,2];
myFunction(...args);

function myFunction(v,w,x,y,z) {}
var args = [0,1,];
myFunction(-1, ...args, 2, ...[3]);

var dateField = [1970, 0, 1];
var d = new Date(...dateFields);

function applyAndNew(consstructor, args) {
	function partial () {
		return constructor.apply(this, args);
	};
	if (typeof constructor.prototy === "object" ) {
		partial.prototype = Object.create(constructor.prototype);
	}
	return partial;
}

function myConstructor () {
	console.log("arguments.length: " + arguments.length);
	console.log(arguments);
	this.prop1 = "val1";
	this.prop2 = "val2";
};

var myArguments = ["hi", "how", "are", "you", "mr", null];
var myConstructorWithArguments = applyAndNew(myConstructor, myArguments);

console.log(new myConstructorWithArguments);

