(ns rosalind-clojure.rna
  "http://rosalind.info/problems/rna/"
  (:require [clojure.string :as string]))

(defn transcribe-dna
  "Transcribe a DNA string to an RNA string."
  [dna-string]
  (string/replace dna-string #"T" "U"))

(comment
  "Run this in the REPL."
  (require '[clojure.string :as string]
           '[rosalind-clojure.rna :as rna])
  (-> (slurp "data/rosalind_rna.txt")
      (rna/transcribe-dna)))