# magic-squares

The fourth Shokunin challenge:

> With a positive integer x as input, build and print the magic square of order x
> (hence all the numbers from 1 to x² in a grid with equal sums horizontally, vertically and diagonally).

There's more information about magic squares on [Wikipedia](https://en.wikipedia.org/wiki/Magic_square).

## Prerequisites

Java (I use version 1.8, older versions might work.)

All other prerequisites will be downloaded automatically when needed.

## Running

Run `./go.sh run <number>` to run the application

## Testing

Run `./go.sh test` to run the tests

## References

* [http://www.mathworks.com/moler/exm/chapters/magic.pdf](http://www.mathworks.com/moler/exm/chapters/magic.pdf),
  an extract from a book showing how MATLAB implements its `magic` function to generate magic squares.
* [http://www.1728.org/magicsq3.htm](http://www.1728.org/magicsq3.htm) for the singly-double magic square generation
  algorithm.
