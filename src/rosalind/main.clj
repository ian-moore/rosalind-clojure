(ns rosalind.main
  "Namespace docs"
  (:require
   [clojure.java.io :as io]
   [clojure.spec.test.alpha :as stest]
   [clojure.tools.cli :as cli]
   [rosalind.problems :as problems]
   [rosalind.problems.rna]))

(def cli-opts
  [[nil  "--instrument" "Instrument spec'd functions."]
   ["-f" "--file FILE" "Input file path."]
   ["-h" "--help"]])
  
(defn -main 
  ""
  [& args]
  (let [{:keys [arguments options summary] :as config} (cli/parse-opts args cli-opts)
        problem (first arguments)]
    (when (:help options)
      (println summary)
      (System/exit 0))
    (when (:instrument options)
      (stest/instrument))
    (with-open [file (io/reader (:file options))]
      (println (problems/solve problem file)))))