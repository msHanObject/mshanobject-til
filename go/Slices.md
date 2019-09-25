# Slices

## Backed by array

## Creation styels

### Slice existing array or slice

### LIteral styel

### Via make function
* a := make([]int, 10) // create slice with capacity and length == 10
* a := make([]int, 10, 100) // slice with length == 10 and capacity == 100

## *len* function returns length of slice

## *cap* function returns length of underlying array

## *append* function to add elements to slice

### May cause expensive copy operation if underlying array is too small

## Copies refer to same underlying array
