(ns rosalind.problems.iprb
  "http://rosalind.info/problems/iprb/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.problems :as problems]))

(s/def ::number number?)

(s/def ::number-or-nil (s/or :number ::number
                             :nil    nil?))

(def one-quarter (/ 1 4))

(def one-half (/ 1 2))

(s/fdef scenario
  :args (s/alt :same-pop  (s/cat :punnett-factor ::number
                                 :total-pop      ::number
                                 :first-pop      ::number)
               :diff-pops (s/cat :punnett-factor ::number
                                 :total-pop      ::number
                                 :first-pop      ::number
                                 :second-pop     ::number-or-nil))
  :return ::number)

(defn scenario
  "Find the probability of a single allele being inherited when two organisms
  mate. Multiplies the 'Punnett' factor for inheriting the allele by the probability
  of an organism from a sub population mating with an organism mating from another
  sub population. If a second sub population is not provided, will use the probability
  of mating within it's own sub population."
  ([punnett-factor total-population first-pop]
   (scenario punnett-factor total-population first-pop nil))
  ([punnett-factor total-population first-pop second-pop]
   (let [pop-minus-one      (dec total-population)
         first-probabilty   (/ first-pop total-population)
         second-probability (if (nil? second-pop)
                              (/ (dec first-pop) pop-minus-one)
                              (/ second-pop pop-minus-one))]
     (* punnett-factor first-probabilty second-probability))))

(s/fdef inheritance-probability
  :args (s/cat :homozygous-dominant  ::number
               :heterozygous         ::number
               :homozygous-recessive ::number)
  :ret  ::number)

(defn inheritance-probability
  "Find the probability that a dominant allele will be inherited when two organisms
  in a population mate. The population is described by three sub populations:
  one that is homozygous for the dominante allele, one that is homozygous for the
  recessive allele, an one that is heterozygous."
  [homozygous-dominant heterozygous homozygous-recessive]
  (let [total-population (+ homozygous-dominant
                            heterozygous
                            homozygous-recessive)
        pop-minus-one    (dec total-population)
        first-scenario   (scenario one-quarter total-population heterozygous) 
        second-scenario  (scenario one-half total-population heterozygous homozygous-recessive)
        third-scenario   (scenario 1 total-population homozygous-recessive)
        recessive-prob   (+ first-scenario (* 2 second-scenario) third-scenario)]
    (- 1 recessive-prob)))

(def parse-int
  (comp
   #(Integer/parseInt %)
   string/trim))

(s/fdef parse-input
  :args (s/cat :input string?)
  :ret  (s/cat :k ::number
               :m ::number
               :n ::number))
                
(defn parse-input
  "Return 3 integers from a string."
  [input]
  (->> (string/split input #" ")
       (map parse-int)))

(defmethod problems/solve :iprb [_ reader]
  (let [input   (slurp reader)
        [k m n] (parse-input input)]
    (println (str "Homozygous Dominant: " k))
    (println (str "Heterozygus: " m))
    (println (str "Homozygous Recessive: " n))
    (float (inheritance-probability k m n))))
