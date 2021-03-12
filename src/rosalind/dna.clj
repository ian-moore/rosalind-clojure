(ns rosalind.dna
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::symbol #{\A \C \G \T})

(s/def ::string (s/every ::symbol))

(defn string
  "Coerce to a lazy DNA string."
  [input]
  (lazy-seq input))
