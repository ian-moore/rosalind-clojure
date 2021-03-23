(ns rosalind.problems.gc-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.dna :as dna]
   [rosalind.problems.gc :as gc]))

(deftest fasta-header
  (testing "it returns the string after the leader"
    (is (= "SEQUENCE_1" (gc/fasta-header ">SEQUENCE_1"))))
  (testing "it returns nil when the string is not a valid header"
    (is (nil? (gc/fasta-header "ACGATCGTATAAA")))
    (is (nil? (gc/fasta-header "\n")))))

(deftest fasta-symbol-content
  (let [result (gc/fasta-symbol-content [">FASTA_1" "ACGT" "CGTA"
                                         ">FASTA_2" "AAAA"])]
    (testing "it returns a map with each header as a key"
      (is (contains? result "FASTA_1"))
      (is (contains? result "FASTA_2")))
    (testing "it returns the total symbol count"
      (is (= 8 (get-in result ["FASTA_1" :total])))
      (is (= 4 (get-in result ["FASTA_2" :total]))))))

(deftest gc-content
  (testing "it returns the correct percentage"
    (let [test-string  (dna/string "AACCGGTTTT")
          test-symbols (dna/symbol-count test-string)]
      (is (zero? (compare 40 (gc/gc-content test-symbols)))))))

(deftest max-gc-content
  (let [input-counts (gc/fasta-symbol-content [">FASTA_1" "AACCGGTT"
                                               ">FASTA_2" "AACGTTTT"])
        result       (gc/max-gc-content input-counts)]
    (testing "it returns the highest GC content percentage"
      (is (zero? (compare 50 (:max-gc result)))))
    (testing "it returns the ID of the highest GC content dna string"
      (is (= "FASTA_1" (:max-id result))))))
