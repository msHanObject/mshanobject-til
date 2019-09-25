## Arrays

An array in PHP is actually an ordered map. A map is a type that associates values to *keys*. This type is optimized for several different uses; it can be treated as an array, list (vector), hash table (an implementation of a map), dictionary, collection, stack, queue, and probably more. As array values can be other arrays, trees and multidimensional arrays are also possible.

Explanation of those data stucutres is beyond the scope of this manual, but at least one example is provided for each of them. For more information, look towards the considerable literature that exists about this broad topic.


### Syntax

#### Specifying with array()

An array can be created using the array() lanuage construct. It takes any number of comma-spearted *key* => value pairs as arguments.

*array (
	key => value,
	key2 => value2,
	key3 => value3,
	...
)*

The comma after the last array element is optional and can be omitted. This is usually done for single-line arrays, i.e. *array(1,2)* is preferred over *array(1,2,).* For multi-line arrays on the other hand the trailing comma is commonly used, as it allows easier additino of new elements at the end.

As of PHP 5.4 you can also use the short array syntax, which replaces array() with [].

### **Example #1 A simple array**

```php
<?php
$array = array(
	"foo" => "bar",
	"bar" => "foo",
);

// as of PHP 5.4
$array = [
	"foo" => "bar",
	"bar" => "foo",
];
?>
```
The key can either be an [integer](https://www.php.net/manual/en/language.types.integer.php) or a [string](https://www.php.net/manual/en/language.types.string.php). The value can be of any type.

Additionally the following key casts will occur:

* Strings containing valid decimal integers, unless the number is preceded by a +sign, will be cast to the integer type. E.g. the key "8" will actually be stored under 8. On the other hand "08" will not be cast, as it isn't a vaild decimal integer.

* Floats are also cast to integers, which means that the fractional part will be truncated. E.g. the key 8.7 will actually be stored under 8.

* Bools are cast to integers, too, i.e. the key true will actually be stored under 1 and the key false under 0.

* Null will be cast to the empty string, i.e. the key null will actually be stored under "".

* Arrays and objects can not be used as keys. Doing so will result in a warning: illegal offset type.

If multiple elements in the array declaration use the same key, only the last one will be used as all others are overwritten.

### **Example #2 Type Casting and Overwriting example**

```
<?php
$array = array(
	1 => "a",
	"1" => "b",
	1.5 => "c",
	true => "d",
);
var_dump($array);
?>
```

The above exmaple will output:

```
array(1) {
	[1] =>
	string(1) "d"
}
```
As all the keys in the above example  are cast to 1, the valuee will be overwritten on every new element and the last assigned value "d" is the only one left over.

PHP arrays can contain integer and string keys at the same time as PHP does not distinguish between indexed and associative arrays.

### **Example #3 Mixed integer and string keys**

```php
<?php
$array = array(
	"foo" => "bar",
	"bar" => "foo",
	100 => -100,
	-100 => 100,
);
var_dump($array);
?>
```

The above example will output:

```
array(4) {
	["foo"] =>
	string(3) "bar"
	["bar"] =>
	string(3) "foo"
	[100] =>
	int(-100)
	[-100] =>
	int(100)
}
```
The key is optional. If it is not specified, PHP will use the increment of the largest previously used integer key.

### **Example #4 Indexed arrays without key**

```php
<?php
$array = array("foo", "bar", "hello", "world");
var_dump($array);
?>
```

The above example will output:

```
array(4) {
	[0] =>
	string(3) "foo"
	[1] =>
	string(3) "bar"
	[2] =>
	string(5) "hello"
	[3] =>
	string(5) "world"
}
```

It is possible to specify the key only for some elements and leave it out for others:

### **Example #5 Keys not on all elements**

```php
<?php
$array = array(
		"a",
		"b",
	6 => "c",
		"d",
);
var_dump($array);
?>
```

The above example will output:

```
array(4) {
	[0] =>
	string(1) "a"
	[1] =>
	string(1) "b"
	[6] =>
	string(1) "c"
	[7] =>
	string(1) "d"
}
```

As you can see the last value "d" was assigned the key 7. This is because the largest integer key before that was 6.

**Accessing array elements with square bracket syntax**

Array elements can be accessed using the array[key] syntax.

**### Example #6 Accesing array elements**

```php
<?php
$array = array(
	"foo" => "bar",
	42 => 24,
	"multi" => array(
		"dimensional" => array(
			"array" => "foo"
		)
	)
);

var_dump($array["foo"]);
var_dump($array[42]);
var_dump($array["multi"]["dimensional"]["array"]);
?>
```

The above exampel will output:

```
string(3) "bar"
int(24)
string(3) "foo"
```

**Note:**
Both square brackets and curly braces can be used interchangeably for accessing array elements (e.g. $array[42] and $array{42]} will both do the same thing in the example above).

As of PHP 5.4 it is possible to array dereference the result of a function or method call directly. Before it was only possible using a temporary variable.

As of PHP 5.5. it is possible to array dereference an array literal.

### **Example #7 Array dereferencing**

```php
<?php
function getArray() {
	return array(1, 2, 3);
}

// on PHP 5.4
$secondElement = getArray() [1];

// previously
$tmp = getArray();
$secondElement = $tmp[1];

// or
list(, $secondElement) = getArray();
?>
```

To change a certain value, assign a new value to that element using its key. To remove a key/value to that element using its key. To remove a key/value pair, call the unset() function on it.

```php
<?php
$arr = array(5 => 1, 12 => 2);

$arr[] = 56; // This is the same as $arr[13] = 56; at this point of the script

$arr["x"] = 42; // This adds a new element to the array with key "x"

unset($arr[5]); // This removes the element from the array

unset($arr); // This deletes the whole array
?>
```

**Notee:**
As mentioned above, if no key is specified, the maximum of hte existing integer indices is taken, and the new key will be that maximum value plus 1(but at least 0). If no integer indices exist yet, the key will be 0 (zero).

Note that the maximum integer key used for this need not currently exist in the array. It need only have existed in the array at some time since the last time the array was re-indexed. The following example illustrates:

```php
<?php
// Create a simple array.
$array = array(1,2,3,4,5);
print_r($array);

// Now delete every item, but leave the array itself intact:
foreachh ($array as $i => $value) {
	unset($array[$i]);
}
print_r($array);

// Append an item (note that the new key is 5, instead of 0).
$array[] = 6;
print_r($array);

// Re-index:
$array = array_values($array);
$array[] = 7;
print_r($array);
?>
```

The above example will output:

```
Array
(
	[0] => 1
	[1] => 2
	[2] => 3
	[3] => 4
	[4] => 5
)

Array
(
)

Array
(
	[5] => 6
)

Array
(
	[0] => 6
	[1] =>7
)
```

**Useful functions**

There are quite a few useful functions for working with arrays. See the [array functions](https://www.php.net/manual/en/ref.array.php) section.

**Note:**
The unset() function allows removing keys from an array. Be aware that the array will not be reindexed. If a true "remove and shift" behavior is desired, the array can be reindexed using the array_values() function.

```php
<?php
$a = array(1 => 'one', 2 => 'two', 3 => 'three');
unset($a[2]);
/* will produce an array that would have been defined as
	$a = array(1 => 'one', 3 => 'three');
	and NOT
	$a = array(1 => 'one', 2 => 'three');
*/

$b = array_values($a);
// Now $b is array(0 => 'one', 1 => 'three')
?>
```

The foreach control structure exists specifically for arrays. It provides an easy way to traverse an array.

**Array do's and dont's**

Why is $foo[bar] wrong?

Alwasy use quotes around a string literal array index. For example, $foo['bar'] is correct, while $foo[bar] is not. But why? It is common to encounter this kind of syntax in old scripts:

```php
<?php
$foo[bar] = 'enemy';
echo $foo[bar];
//etc
?>
```

This is wrong, but it wroks. The reason is that this code has an undefined constant (bar) rather than a string ('bar' -notice the quotes). It works because PHP automatically converts a bare string (an unquoted string which does not correspond to any known symbol) into a string which contains the bare string. For instance, if there is no defined constant named *bar*, then PHP will substitute in the string 'bar' and use that.

**Warning** The fallback to treat an undefined constant as bare string is deprecated as of PHP 7.2.0, and issues an error of level **E_WARNING**. Formerly, an error of level E_NOTICE has been issued.

**Note:** This does not mean to always quote the key. Do not quote keys which are constants or variables, as this will prevent PHP from interpreting them.

```php
<?php
error_reporting(E_ALL);
ini_set('display_erors', true);
ini_set('html_errors', false);
//Simple array:
$array = array(1, 2);
$count = count($array);
for ($i = 0; $i < $count; $i++) {
	echo "\nChecking $i: \n";
	echo "Bad: ".$array['$i']."\n";
	echo "Good: ".$array[$i]."\n";
	echo "Bad: {$array['$i']}\n";
	echo "Good: {$array[$i]}\n";
}
?>
```

The above example will output:

```
Checking 0:
Notice: Undefined index: $i in /path/to/script.html on line 9
Bad:
Good: 1
Notice: Undefined index: $i in/path/to/script.html on line 11
Bad:
Good: 1





















