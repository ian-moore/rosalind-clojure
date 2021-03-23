(ns rosalind.problems.subs-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [rosalind.dna :as dna]
   [rosalind.problems.subs :as subs]))

(deftest substring-locations
  (testing "it finds substring locations"
    (let [dna-string    (dna/string "GATATATGCATATACTT")
          dna-substring (dna/string "ATAT")
          result        (subs/substring-locations dna-string dna-substring)]
      (is (= [2 4 10] result)))))

