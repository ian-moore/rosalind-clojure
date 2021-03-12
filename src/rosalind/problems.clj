(ns rosalind.problems
  "Generic code for all problems."
  (:require
   [clojure.string :as string]))

(defmulti solve 
  "Extension point for all problem solutions. Coerces the problem name to a
  lowercase keyword for dipatch."
  (fn [problem reader]
    (-> problem
        string/lower-case
        keyword)))
