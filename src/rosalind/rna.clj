(ns rosalind.rna
  "RNA code."
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::symbol #{\A \C \G \U})

(s/def ::string (s/every ::symbol))

(defn string
  "Coerce to a RNA string."
  [input]
  (seq input))
