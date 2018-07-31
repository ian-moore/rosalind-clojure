(ns rosalind-clojure.revc
  "http://rosalind.info/problems/revc/"
  (:require [clojure.string :as string]))

(def compliments
  {:A "T"
   :C "G"
   :G "C"
   :T "A"})

(defn reverse-compliment
  "Return the reverse compliment of a DNA string."
  [dna-string]
  (->> (string/split dna-string #"")
       (reduce (fn [rc x] (cons ((keyword x) compliments) rc)) "")
       (string/join "")))

(comment
  "Run this in the REPL."
  (require '[clojure.string :as string]
           '[rosalind-clojure.revc :as revc])
  (-> (slurp "data/rosalind_revc.txt")
      (revc/reverse-compliment)))
