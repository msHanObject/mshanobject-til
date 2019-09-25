# Constants

## Immutable, but can be shadowed

## Replaced by the compiler at compile time
* Value must be calculable at compile time

## Named like variables
* PascalCase for exported contstants
* camelCase for internal constants

## Typed constants work like immutable variables
* Can interoperate only with same type

## Untyped constants work like literals
* Can interoperate with similar types

## Enumerated constants
* Special sysmbol iota allows related constants to be created easily
* Iota starts at 0 in each const block and increments by one
* Watch out of constant valuees that match zero values for variables

## Enumerated expressions
### Operations that can be determined at compile time are allowed
* Arithmetic
* Bitwise operations
* Bitshifting

