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

(defmethod solve :default [problem-name _]
  (str "No implementation for problem \"" problem-name "\""))
