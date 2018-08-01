(ns rosalind-clojure.subs
  "http://rosalind.info/problems/subs/"
  (:require [clojure.string :as string]))

(defn substring-locations
  "Given two DNA strings s and t, 
  return all locations of t as a substring of s."
  [s t]
  (let [sublist (string/split t #"")
        sublist-length (count sublist)
        dna (string/split s #"")]
    (for [i (range (count dna))
          :let [rest-dna (nthrest dna i)
                next-sublist (take sublist-length rest-dna)]
          :when (= next-sublist sublist)]
      (+ i 1))))

(comment
  "Run this in the REPL."
  (require '[clojure.string :as string]
           '[rosalind-clojure.subs :as subs])
  (->> (slurp "data/rosalind_subs.txt")
       (string/split-lines)
       (apply subs/substring-locations)))