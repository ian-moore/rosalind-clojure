# Project Rosalind in Clojure

Attempting to solve [Project Rosalind](http://rosalind.info/about/) problems with [Clojure](https://clojure.org/)

## Goals

1. Solve the first 10 Rosalind problems
1. Follow Clojure best-practices:
  * Use [spec](https://clojure.org/guides/spec) to describe data
  * Adhere to the [Clojure style guide](https://github.com/bbatsov/clojure-style-guide)
  * Choose [good names](https://stuartsierra.com/2016/01/09/how-to-name-clojure-functions) for functions 

## Non Goals

1. ClojureScript support
1. Optimizing for performance unless necessary

## Running

`clojure -m rosalind.problems.dna --input-file test.txt`

`clojure -M:solve rna -f rosalind_rna.txt`
