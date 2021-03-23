(ns rosalind.dna
  "DNA code."
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::symbol #{\A \C \G \T})

(s/def ::string (s/every ::symbol))

;; This spec has DNA symbol keys and can be extended with a keyword key
;; The values must be numeric
(s/def ::symbol-count (s/map-of (s/or :symbol ::symbol
                                      :extra  keyword?)
                                number?))

(defn string
  "Coerce to a DNA string."
  [input]
  (seq input))

(s/fdef symbol-count
  :args (s/cat :dna-string ::string)
  :ret  ::symbol-count)

(defn symbol-count
  "Given a DNA String, return the count of each symbol."
  [dna-string]
  (let [symbol-counts (frequencies dna-string)]
    (merge {\A 0 \C 0 \G 0 \T 0}
           symbol-counts
           {:total (reduce + (vals symbol-counts))})))
