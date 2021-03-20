(ns rosalind.problems.iprb-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.problems.iprb :as iprb]))

(deftest parse-input
  (testing "it parses 3 numbers"
    (let [result (iprb/parse-input "2 2 2")]
        (is (= 3 (count result)))
        (is (every? number? result))))
  (testing "it handles trailing newline"
    (let [result (iprb/parse-input "2 4 6\n")]
        (is (every? number? result)))))

