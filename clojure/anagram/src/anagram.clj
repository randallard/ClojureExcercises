(ns anagram)
(defn index-of [e coll] (first (keep-indexed #(if (= e %2) %1) coll)))
(defn remove-idx [i items] (keep-indexed #(when-not (= i %1) %2) items))
(defn remove-letter
  "returns a copy of the list without the matched letter - returns equal list if that letter is not in the list"
  [the-letter the-word]
  (remove-idx (index-of the-letter the-word) the-word)
  )

(defn test-anagram
  "returns true if anagram, false if not"
  [[this-letter & rest-of-this-word] anagram-of-word-to-test]
  (println (str "this-letter: [" this-letter "] in this string: [" (apply str anagram-of-word-to-test) "] with rest-of-this-word: [" rest-of-this-word "]"))
  (if (or (nil? (index-of this-letter anagram-of-word-to-test)) (nil? this-letter))
    (= (apply str [this-letter rest-of-this-word]) (apply str anagram-of-word-to-test))
    (do
      (println rest-of-this-word)
      (recur rest-of-this-word (remove-letter this-letter anagram-of-word-to-test))))
  )
(defn anagrams-for [word prospect-list]
  (filter #(if (= (clojure.string/lower-case word) (clojure.string/lower-case %))
             false
             (test-anagram (clojure.string/lower-case word) (clojure.string/lower-case %))) prospect-list)
)
(index-of \n "")
(= ["at"] (filter #(test-anagram "tan" %) ["till" "at" "won"]))

(def try-word "mom")
(def try-list "mom")
(def the-letter \z)
(def new-list 
  (remove-idx (index-of the-letter try-word) try-word)
  )
new-list
(= (list \m \o) (list \o \m))
(def of-word "ant")
(test-anagram "ant" "an")
(let [[this-letter & rotw] ""]
(println this-letter)
(println (remove-letter this-letter of-word)))
(empty? try-word)
(empty? "")
(index-of \z try-word)
(anagrams-for "sti" ["an" "it" "is" "sit" "SIT"])