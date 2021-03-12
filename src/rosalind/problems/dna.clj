(ns rosalind.problems.dna
  "http://rosalind.info/problems/dna/"
  (:require
   [clojure.java.io :as io]
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as stest]
   [clojure.tools.cli :as cli]))

;; (s/def ::dna-symbol char?) -> this allows invalid symbols

(s/def ::dna-symbol #{\A \C \G \T})

(s/def ::dna-string (s/every ::dna-symbol))

(s/fdef symbol-count
  :args (s/cat :dna-string ::dna-string)
  :ret  (s/map-of ::dna-symbol int?))

(defn symbol-count
  "Given a DNA String, return the count of each symbol."
  [dna-string]
  (frequencies dna-string))

(def cli-opts
  [[nil "--instrument" "Instrument spec'd functions."]
   ["-i" "--input-file FILE" "Input file path."]
   ["-h" "--help"]])
  
(defn -main 
  "Given: A DNA string s of length at most 1000 nt.
  
  Return: Four integers (separated by spaces) counting the respective number of times that the symbols 'A', 'C', 'G', and 'T' occur in s."
  [& args]
  (let [config (cli/parse-opts args cli-opts)]
    (when (get-in config [:options :help])
      (println (:summary config))
      (System/exit 0))
    (when (get-in config [:options :instrument])
      (stest/instrument))
    (let [input-data (slurp (get-in config [:options :input-file]))
          dna-string (seq input-data)
          symbol-counts (symbol-count dna-string)]
      (println (str (symbol-counts \A) " "
                    (symbol-counts \C) " "
                    (symbol-counts \G) " "
                    (symbol-counts \T))))))
  

