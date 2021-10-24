(ns log-levels
  (:require [clojure.string :as str]))

(def teststring "[SUPER]: Di duper")

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (str/trim ((str/split s #":") 1)))
(message teststring)
(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (str/lower-case (subs s 1 (.indexOf s "]"))))
(log-level teststring)
(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")")
  )
(reformat teststring)
