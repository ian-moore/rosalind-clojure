(ns rosalind.dna
  "DNA code."
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::symbol #{\A \C \G \T})

(s/def ::string (s/every ::symbol))

(defn string
  "Coerce to a DNA string."
  [input]
  (seq input))
