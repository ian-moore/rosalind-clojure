(ns rosalind.problems.rna
  "http://rosalind.info/problems/rna/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.dna :as dna]
   [rosalind.problems :as problems]))

(s/def ::rna-symbol #{\A \C \G \U})

(s/def ::rna-string (s/every ::rna-symbol))

(s/fdef transcribe-rna
  :args (s/cat :dna-string ::dna/string)
  :ret  ::rna-string)

(def dna-symbol->rna-symbol
  "Map DNA symbols to the equivalent RNA symbol."
  {\A \A
   \C \C
   \G \G
   \T \U})

(defn transcribe-rna
  "Transcribe a DNA string to an RNA string."
  [dna-string]
  (map dna-symbol->rna-symbol dna-string))

(defmethod problems/solve :rna [_ reader]
  (let [raw-data   (slurp reader)
        dna-string (dna/string raw-data)
        rna-string (transcribe-rna dna-string)]
    (string/join rna-string)))
