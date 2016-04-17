# magic-squares

[![wercker status](https://app.wercker.com/status/75c83e41f0f856b26835e24d08a2cb4f/s "wercker status")](https://app.wercker.com/project/bykey/75c83e41f0f856b26835e24d08a2cb4f)

The fourth Shokunin challenge:

> With a positive integer x as input, build and print the magic square of order x
> (hence all the numbers from 1 to x² in a grid with equal sums horizontally, vertically and diagonally).

There's more information about magic squares on [Wikipedia](https://en.wikipedia.org/wiki/Magic_square).

## Prerequisites

* JVM (I use version 1.8, older versions might work.)

All other prerequisites (Gradle, Scala, libraries etc.) will be downloaded automatically when needed.

## Testing

Run `./go.sh build` to build the application

## Running

Run `./go.sh run <number>` to build and run the application

## Testing

Run `./go.sh test` to build and run the tests

## References

* [http://www.mathworks.com/moler/exm/chapters/magic.pdf](http://www.mathworks.com/moler/exm/chapters/magic.pdf),
  an extract from a book showing how MATLAB implements its `magic` function to generate magic squares.
* [http://www.1728.org/magicsq3.htm](http://www.1728.org/magicsq3.htm) for the singly-even magic square generation
  algorithm.
