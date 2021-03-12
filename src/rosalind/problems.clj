(ns rosalind.problems
  "Namespace docs"
  (:require
   [clojure.string :as string]))

(defmulti solve 
  "Docstring"
  (fn [problem reader]
    (-> problem
        string/lower-case
        keyword)))
