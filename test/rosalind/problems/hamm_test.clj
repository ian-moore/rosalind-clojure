(ns rosalind.problems.hamm-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.dna :as dna]
   [rosalind.problems.hamm :as hamm]))

(deftest hamming-distance
  (testing "it returns the number of point mutations"
    (let [string-1 (dna/string "GAGCCTACTAACGGGAT")
          string-2 (dna/string "CATCGTAATGACGGCCT")
          result   (hamm/hamming-distance string-1 string-2)]
      (is (= 7 result)))))

