(ns rosalind.problems.fib
  "http://rosalind.info/problems/fib/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.problems :as problems]))

;; "Key Observation": F(n) = F(n-1) + ( F(n-2) * k )
;;  F(5) = 19 = 7 + ( 4 * 3 )
;;  (1 1 4 7 19)

(s/fdef rabbit-recurrence
  :args (s/cat :new-pairs number?
               :last-gens vector?)
  :ret  (s/cat :last-gen number?
               :next-gen number?))

(defn rabbit-recurrence 
  "Compute the next generation of rabbits that produce new-pairs based on
  the previous two generations."
  [new-pairs [two-gens-ago last-gen]]
  [last-gen
   (+ last-gen (* two-gens-ago new-pairs))])

(s/fdef rabbit-sequence
  :args (s/cat :n number?
               :k number?)
  :ret  number?)

(defn rabbit-sequence 
  "n = number of months
  k = pairs of rabbits produced per pair"
  [n k]
  (let [k-rabbits       (partial rabbit-recurrence k)
        monthly-rabbits (map second (iterate k-rabbits [0 1]))]
    (take n monthly-rabbits))) 

(def parse-int
  (comp
   #(Integer/parseInt %)
   string/trim))

(defn parse-input
  "Return 2 integers from a string."
  [input]
  (->> (string/split input #" ")
       (map parse-int)))

(defmethod problems/solve :fib [_ reader]
  (let [[n k] (parse-input (slurp reader))]
    (last (rabbit-sequence n k))))
