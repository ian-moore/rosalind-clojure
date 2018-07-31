(ns rosalind-clojure.dna
  "http://rosalind.info/problems/dna/"
  (:require [clojure.string :as string]))

(defn symbol-frequencies
  "Given a string, return the frequences of DNA symbols."
  [s]
  (->> (string/split s #"")
       (map keyword)
       (frequencies)))

(comment
  "Run this in the REPL."
  (require '[clojure.string :as string]
           '[rosalind-clojure.dna :as dna])
  (->> (slurp "data/sample-dna.txt")
       (dna/symbol-frequencies)
       (into (sorted-map))
       (vals)
       (string/join " ")))