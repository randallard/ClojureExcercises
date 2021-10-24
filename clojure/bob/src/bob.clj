(ns bob)
(defn question? 
  [s]
  (= \? (last s)))
(defn yelled?
  [s]
  (and
   (some #(Character/isLetter %) s)
   (every? #(Character/isUpperCase %) (filter #(Character/isLetter %) s))))
(defn silence?
  [s]
  (empty? s))
(defn response-for [s] 
  (let [s (clojure.string/trim s)]
    (cond
      (silence? s) "Fine. Be that way!"
      (and (question? s) (yelled? s)) "Calm down, I know what I'm doing!"
      (question? s) "Sure."
      (yelled? s) "Whoa, chill out!"
      :else "Whatever.")))

