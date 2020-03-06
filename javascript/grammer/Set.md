# Description
*Set* objects are collections of values. You can iterate through the elements of a set in insertion order. A value in the Set may only occur once; it is unique in the Set's collection.

## Value equality
Because each value in the *Set* has to be unique, the value equality will be checked. .In an earlier version of ECMAScript specification, this was not based on the same algorithm as the one used in the === operator. Sepcifically, for Sets, +0 (which is sstrictly equal to -0) and -0 were different values. However, this was changed in the ECMAScript 2015 spepcification. See "Key equality for -0 and 0" in the browser compaitibility tablee for details.

NaN and undefined can also be stored in a Set. All NaN values are equated, i.e.. NaN is considered the same as NaN (even though NaN !== NaN).

# Properties

Set.size
The default value of the size property is 0.

get SEt [@@species]
The constructor function that is used to create derived objects..

Set.prototype
Represents the prototype for the Set constructor. Allows the addition of properties to all Set objects.

# Set instances
All Set instances inherit from Set.prototype


