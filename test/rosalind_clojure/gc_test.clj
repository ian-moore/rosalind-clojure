(ns rosalind-clojure.gc-test
  (:require [clojure.test :refer :all]
            [rosalind-clojure.gc :refer :all]))

(def sample-input
  ">Rosalind_6404
CCTGCGGAAGATCGGCACTAGAATAGCCAGAACCGTTTCTCTGAGGCTTCCGGCCTTCCCTCCCACTAATAATTCTGAGG
>Rosalind_5959
CCATCGGTAGCGCATCCTTAGTCCAATTAAGTCCCTATCCAGGCGCTCCGCCGAAGGTCTATATCCATTTGTCAGCAGACACGC
>Rosalind_0808
CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGACTGGGAACCTGCGGGCAGTAGGTGGAAT")

(def sample-output
  "Rosalind_0808
60.919540")

(deftest gc-tests
  (testing "sample input returns expected result"
    (let [actual-output (run sample-input)]
      (println actual-output)
      (is (= actual-output sample-output)))))