(ns anagram-test
  (:require [clojure.test :refer [deftest is]]
            anagram))
(let [the-list (list \m \o \m)]
  (deftest returns-same-list-if-letter-not-in-seq
    (is (= the-list (anagram/remove-letter \a the-list))))

  (deftest returns-copied-list-without-that-letter
    (is (= (list \m \m) (anagram/remove-letter \o the-list))))

  (deftest returns-copied-list-leaving-other-instances-of-same-letter
    (is (= (list \o \m) (anagram/remove-letter \m the-list)))))

(deftest recognizes-single-word-anagram
  (is (anagram/test-anagram "tan" "ant")))

(deftest recognize-single-word-is-not-an-anagram
  (is (not (anagram/test-anagram "mother" "father"))))

(deftest recognize-partial-word-is-not-an-anagram-of-part
  (is (not (anagram/test-anagram "an" "ant"))))

(deftest recognize-partial-word-is-not-an-anagram
  (is (not (anagram/test-anagram "ant" "an"))))

(deftest no-matches
  (is (= []
         (anagram/anagrams-for "diaper" ["hello" "world" "zombies" "pants"]))))

(deftest detect-simple-anagram
  (is (= ["tan"] (anagram/anagrams-for "ant" ["tan" "stand" "at"]))))

(deftest does-not-confuse-different-duplicates
  (is (= [] (anagram/anagrams-for "galea" ["eagle"]))))

(deftest eliminate-anagram-subsets
  (is (= [] (anagram/anagrams-for "good" ["dog" "goody"]))))

(deftest detect-anagram
  (is (= ["inlets"]
         (let [coll ["enlists" "google" "inlets" "banana"]]
           (anagram/anagrams-for "listen" coll)))))

(deftest multiple-anagrams
  (is (= ["gallery" "regally" "largely"]
         (let [coll ["gallery" "ballerina" "regally"
                     "clergy"  "largely"   "leading"]]
           (anagram/anagrams-for "allergy" coll)))))

(deftest case-insensitive-anagrams
  (is (= ["Carthorse"]
         (let [coll ["cashregister" "Carthorse" "radishes"]]
           (anagram/anagrams-for "Orchestra" coll)))))

(deftest word-is-not-own-anagram
  (is (= [] (anagram/anagrams-for "banana" ["banana"]))))

(deftest capital-word-is-not-own-anagram
  (is (= [] (anagram/anagrams-for "BANANA" ["banana"]))))
