(ns rosalind-clojure.gc
  "http://rosalind.info/problems/gc/"
  (:require [clojure.string :as string]))

(def fasta->id #(string/replace % #">" ""))

(defn n-frequencies
  [s]
  (-> (string/split s #"")
      frequencies
      clojure.walk/keywordize-keys))

(defn gc-content
  [s]
  (let [freqs (n-frequencies s)]
    (/ (+ (:G freqs) (:C freqs))
       (apply + (vals freqs)))))

(defn max-gc-content
  [lines]
  (reduce (fn [state next-line]
            (if (string/starts-with? next-line ">")
              (assoc state :next-id (fasta->id next-line))
              (let [next-gc (gc-content next-line)]
                (if (< next-gc (:max-gc state))
                  state
                  {:max-gc next-gc
                   :max-id (:next-id state)}))))
          {:max-gc 0
           :max-id nil}
          lines))

(defn run
  [input-string]
  (let [lines (string/split-lines input-string)
        max (max-gc-content lines)]
    (str (:max-id max) \newline 
         (* 100 (float (:max-gc max))))))