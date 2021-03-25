(ns rosalind.problems.hamm
  "http://rosalind.info/problems/hamm/"
  (:require
   [clojure.spec.alpha :as s]
   [rosalind.dna :as dna]
   [rosalind.problems :as problems]))

(s/fdef hamming-distance
  :args (s/cat :string-1 ::dna/string
               :string-2 ::dna/string)
  :ret  number?)

(defn hamming-distance
  "The minimum number of symbol substitutions required to transform
  one string into the other."
  [string-1 string-2]
  (->> (interleave string-1 string-2)
       (partition 2)
       (keep (fn [[a b]] (when (not= a b) :mismatch)))
       count))

(defmethod problems/solve :hamm [_ reader]
  (let [input    (line-seq reader)
        string-1 (dna/string (first input))
        string-2 (dna/string (second input))]
    (hamming-distance string-1 string-2)))
