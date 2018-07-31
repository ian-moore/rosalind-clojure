(ns rosalind-clojure.dna
  "http://rosalind.info/problems/dna/"
  (:require [clojure.string :as string]))

(defn symbol-frequencies
  "Given a DNA string, return the frequences of DNA symbols."
  [dna-string]
  (->> (string/split dna-string #"")
       (map keyword)
       (frequencies)))

(comment
  "Run this in the REPL."
  (require '[clojure.string :as string]
           '[rosalind-clojure.dna :as dna])
  (->> (slurp "data/rosalind_dna.txt")
       (dna/symbol-frequencies)
       (into (sorted-map))
       (vals)
       (string/join " ")))