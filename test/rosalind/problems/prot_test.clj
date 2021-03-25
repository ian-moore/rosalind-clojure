(ns rosalind.problems.prot-test
  (:require
   [clojure.test :refer [deftest testing is]]  
   [clojure.string :as string]
   [rosalind.problems.prot :as prot]
   [rosalind.rna :as rna]))
 
(deftest protein-string
  (testing "it produces the correct protein"
    (let [input-rna (rna/string "AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA")
          result    (prot/protein-string input-rna)]
      (is (= "MAMAPRTEINSTRING" (string/join result))))))
