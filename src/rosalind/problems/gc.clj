(ns rosalind.problems.gc
  "http://rosalind.info/problems/gc/"
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as string]
   [rosalind.dna :as dna]
   [rosalind.problems :as problems]))

(s/fdef fasta-header
  :args (s/cat :fasta-line string?)
  :ret  (s/or :fasta-header string?
              :invalid      nil?))

(defn fasta-header
  "Return the text if the string is a valid FASTA header line. Otherwise, nil."
  [fasta-line]
  (when (string/starts-with? fasta-line ">")
    (string/replace-first fasta-line \> "")))

(def merge-numbers 
  "Merge maps by adding their values together."
  (partial merge-with +))

(defn fasta-symbol-content
  "Read a FASTA file and accumulate the symbol counts for each DNA string."
  [file-lines]
  (-> (reduce
       (fn [file-contents next-line]
         (let [current-id (:last-read-id file-contents)]
           (if-let [new-id (fasta-header next-line)]
             (assoc file-contents :last-read-id new-id)
             (->> (dna/string next-line)
                  dna/symbol-count
                  (update file-contents current-id merge-numbers)))))
       {}
       file-lines)
      (dissoc :last-read-id)))

(s/fdef gc-content
  :args (s/cat :symbols ::dna/symbol-count)
  :ret  number?)

(defn gc-content
  "Percentage of C or G symbols in a symbol count."
  [symbols]
  (let [gc (+ (get symbols \C) (get symbols \G))]
    (float (* 100 (/ gc (:total symbols))))))

(defn max-gc-content
  "Return the highest GC-content DNA string from a FASTA file."
  [fasta-symbols]
  (reduce-kv
   (fn [{:keys [max-gc max-id] :as state} string-id string-symbols]
     (let [gc (gc-content string-symbols)]
       (if (< gc max-gc)
         state
         {:max-gc gc
          :max-id string-id})))
   {:max-gc 0}
   fasta-symbols))
          
(defmethod problems/solve :gc [_ reader]
  (let [fasta-symbols           (fasta-symbol-content (line-seq reader))
        {:keys [max-gc max-id]} (max-gc-content fasta-symbols)]
    (str max-id "\n" max-gc)))
             
             
           
