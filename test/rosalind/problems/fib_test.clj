(ns rosalind.problems.fib-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.problems.fib :as fib]))

(deftest rabbit-recurrence
  (testing "it returns the last generation and the correct next generation" 
    (let [result (fib/rabbit-recurrence 3 [1 4])]
      (is (= [4 7] result)))))

(deftest rabbit-sequence
  (testing "it returns the correct sequence"
    (let [result (fib/rabbit-sequence 5 3)]
      (is (= [1 1 4 7 19] result)))))

(deftest parse-input
  (testing "it parses 2 numbers"
    (let [result (fib/parse-input "2 2")]
        (is (= 2 (count result)))
        (is (every? number? result))))
  (testing "it handles trailing newline"
    (let [result (fib/parse-input "2 4\n")]
        (is (every? number? result)))))

