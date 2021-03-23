(ns rosalind.problems.subs
  "http://rosalind.info/problems/subs/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.dna :as dna]
   [rosalind.problems :as problems]))

(s/fdef substring-locations
  :args (s/cat :dna-substring ::dna/string
               :dna-string    ::dna/string)
  :ret  (s/every number?))

(defn substring-locations
  "Find all positions of dna-substring within dna-string."
  [dna-string dna-substring]
  (let [substring-length (count dna-substring)]
    ;; Yield each position of the DNA string that starts the substring.
    (for [i     (range (count dna-string))
          :let  [rest-dna       (drop i dna-string)
                 next-substring (take substring-length rest-dna)]
          :when (= next-substring dna-substring)]
      ;; The first position is numbered 1. 
      (inc i))))

(defmethod problems/solve :subs [_ reader]
  (let [input         (line-seq reader)
        dna-string    (dna/string (first input))
        dna-substring (dna/string (second input))]
    (->> dna-substring
         (substring-locations dna-string)
         (string/join " "))))
    
