(ns rosalind.problems.revc
  "http://rosalind.info/problems/revc/"
  (:require
   [clojure.string :as string]
   [clojure.spec.alpha :as s]
   [rosalind.dna :as dna]
   [rosalind.problems :as problems]))

(def compliment
  "Map each DNA symbol to its compliment."
  {\A \T
   \C \G
   \G \C
   \T \A})

(s/fdef reverse-compliment
  :args (s/cat :dna-string ::dna/string)
  :ret  ::dna/string)

(defn reverse-compliment
  "Return the complement for a DNA string."
  [dna-string]
  (reduce
   (fn [compliment-string dna-symbol]
     (-> (get compliment dna-symbol)
         (cons compliment-string)))
   []
   dna-string))

(defmethod problems/solve :revc [_ reader]
  (let [raw-data   (slurp reader)
        dna-string (dna/string raw-data)]
    (string/join (reverse-compliment dna-string))))
    
