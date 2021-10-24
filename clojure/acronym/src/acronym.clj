(ns acronym
  (:require [clojure.string :as str]))

(defn get-next-letter
  [[first-letter & phrase-characters]]
  first-letter
  )

(defn cons-first-letters
  [the-acronym [first-letter & phrase-characters]]
  (cond
    (nil? phrase-characters) the-acronym
    (= '() the-acronym) (cons-first-letters [first-letter] phrase-characters)
    (= \:     first-letter) the-acronym
    (or (= \space first-letter) (= \- first-letter))
    (let [[this-first-letter & this-phrase-characters] phrase-characters]
      (cons-first-letters
       (conj the-acronym (get-next-letter phrase-characters))
       this-phrase-characters))
    (Character/isUpperCase first-letter) (cons-first-letters (conj the-acronym first-letter) phrase-characters)
    :else (cons-first-letters the-acronym phrase-characters)))

(defn return-first-and-capital-letters
  "takes a word and returns the capitalized letters"
  [[first-char & other-chars]]
  (if (some #(Character/isUpperCase %) other-chars)
    (apply str (cons first-char (re-seq #"[A-Z]" (str other-chars))))
    first-char))

(defn acronym [phrase]
  (cond
    (= "" phrase) ""
    (not (nil? (re-find #":" phrase))) (last (first (re-seq #"(\S+): \S+" phrase)))
    :else (->> (clojure.string/replace phrase #"-" " ")
     (re-seq #"\w+")
     (map return-first-and-capital-letters)
     (map clojure.string/upper-case)
     (apply str))))

;; (def phrase "HyperText Markup Language metal-oxide semiconductor")
;; (def phrase "Portable Network Graphics")
;; (def phrase "PHP: Something Something")

(map #(clojure.string/upper-case (first %)) 
     (re-seq #"\w+" (clojure.string/replace phrase #"-" " "))
     )

(->> (clojure.string/replace phrase #"-" " ")
     (re-seq #"\w+")
     (map return-first-and-capital-letters)
     (map clojure.string/upper-case)
     (apply str)
     )

(some #(Character/isUpperCase %)
       (vec "yperText")
       )     
(apply str (return-first-and-capital-letters "HyperText"))
    
(apply str (re-seq #"[A-Z]+" phrase))
(map #(first %) (re-seq #":" phrase))
(last (first (re-seq #"(\S+): \S+" phrase))) 
(acronym phrase)
; should accronym to "HTML" (acronym/acronym "HyperText Markup Language")))
; should accronym to "FIFO" (acronym/acronym "First In, First Out")))
; should accronym to "PHP" (acronym/acronym "PHP: Hypertext Preprocessor"))))
; should accronym to "CMOS" (acronym/acronym "Complementary metal-oxide semiconductor")))
(def input "PHP: Hypertext Preprocessor")
(str/split input #"(\-)|(\s)|(\w(?=[A-Z]))")

(defn acronym-2
  [input]
  (->> (str/split input #"(\-)|(\s)|(\w(?=[A-Z]))") ; now with the right RegEx
    (map str/upper-case)
    (map first)
    (apply str))
)
