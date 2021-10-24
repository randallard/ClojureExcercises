(ns bob-test
  (:require [clojure.test :refer [deftest is]]
            bob))

(deftest responds-so-blanks-after-question
  (is (= "Sure."
         (bob/response-for "Is this OK with you?   "))))

(deftest responds-to-spaces-as-silence
  (is (= "Fine. Be that way!"
         (bob/response-for "    "))))

(deftest responds-to-counting
  (is (= "Whatever."
         (bob/response-for "1, 2, 3"))))

(deftest responds-to-silence
  (is (= "Fine. Be that way!"
         (bob/response-for ""))))

(deftest responds-to-yelled-question
  (is (= "Calm down, I know what I'm doing!"
         (bob/response-for "HOW ARE YOU?"))))

(deftest responds-to-a-question
  (is (= "Sure."
         (bob/response-for "How are you?"))))

(deftest responds-to-non-question
  (is (= "Whatever."
         (bob/response-for "This is a statement."))))

(deftest responds-to-yelling
  (is (= "Whoa, chill out!"
         (bob/response-for "ANYTHING"))))
