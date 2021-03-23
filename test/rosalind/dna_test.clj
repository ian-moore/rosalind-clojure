(ns rosalind.dna-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.dna :as dna]))

(deftest symbol-count
  (testing "it returns the total symbol count"
    (let [{:keys [total]} (dna/symbol-count (dna/string "AACCCGGTTT"))]
      (is (= total 10))))
  (testing "it returns the A content count"
    (let [symbols (dna/symbol-count (dna/string "AATCCCCCGGG"))]
      (is (= (symbols \A) 2))))
  (testing "it returns the C content count"
    (let [symbols (dna/symbol-count (dna/string "AATCCCCCGGG"))]
      (is (= (symbols \C) 5))))
  (testing "it returns the G content count"
    (let [symbols (dna/symbol-count (dna/string "AATCCCCCGGG"))]
      (is (= (symbols \G) 3))))
  (testing "it returns the T content count"
    (let [symbols (dna/symbol-count (dna/string "AATCCCCCGGG"))]
      (is (= (symbols \T) 1)))))
