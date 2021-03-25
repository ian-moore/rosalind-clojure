(ns rosalind.problems.prot
  "http://rosalind.info/problems/prot/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.problems :as problems]
   [rosalind.rna :as rna]))

(s/def ::amino-acid-symbol #{\A \C \D \E \F \G \H \I \K \L
                             \M \N \P \Q \R \S \T \V \W \Y})

(s/def ::protein-string (s/every ::amino-acid-symbol))

(def rna-codon-table
  {"UUU" \F      "CUU" \L      "AUU" \I      "GUU" \V
   "UUC" \F      "CUC" \L      "AUC" \I      "GUC" \V
   "UUA" \L      "CUA" \L      "AUA" \I      "GUA" \V
   "UUG" \L      "CUG" \L      "AUG" \M      "GUG" \V
   "UCU" \S      "CCU" \P      "ACU" \T      "GCU" \A
   "UCC" \S      "CCC" \P      "ACC" \T      "GCC" \A
   "UCA" \S      "CCA" \P      "ACA" \T      "GCA" \A
   "UCG" \S      "CCG" \P      "ACG" \T      "GCG" \A
   "UAU" \Y      "CAU" \H      "AAU" \N      "GAU" \D
   "UAC" \Y      "CAC" \H      "AAC" \N      "GAC" \D
   "UAA" :stop   "CAA" \Q      "AAA" \K      "GAA" \E
   "UAG" :stop   "CAG" \Q      "AAG" \K      "GAG" \E
   "UGU" \C      "CGU" \R      "AGU" \S      "GGU" \G
   "UGC" \C      "CGC" \R      "AGC" \S      "GGC" \G
   "UGA" :stop   "CGA" \R      "AGA" \R      "GGA" \G
   "UGG" \W      "CGG" \R      "AGG" \R      "GGG" \G}) 

(defn encode-codon
  [[a b c]]
  (get rna-codon-table (str a b c)))

(s/fdef protein-string
  :args (s/cat :rna-string ::rna/string)
  :ret  ::protein-string)

(defn protein-string
  "Encode a RNA string to a protein string."
  [rna-string]
  (->> (partition 3 rna-string)
       (map encode-codon)
       (take-while (partial not= :stop))))

(defmethod problems/solve :prot [_ reader]
  (let [input-rna (rna/string (slurp reader))]
    (string/join (protein-string input-rna))))
