(ns practice.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn train
  []
  (println "Choo choo"))

(def vector-of-strings ["a" "vector" "of" "strings"])
(println vector-of-strings)
(count vector-of-strings)
(if (> (count vector-of-strings) 0)
  (do
    (println "there are more than 0")
    "there are more than 0")
  (do
    (println "there are 0")
    "there are 0"))
(nil?
 (when (> 0 0)
   (do
     (println "there are more than 0")
     "there are more than 0")))

(defn count-comment
  [size]
  (str size " is "
       (if (> size 2)
         "pretty big"
         "not very big")))
(def practice-comment (count-comment (count vector-of-strings)))
(println "comment: " practice-comment)

(def practice-map {:key1 "value1" :key2 {:sub_key_1 "sub value 1" :sub_key_2 "sub value 2"}})
(get practice-map :key1)
(get practice-map :sub_key_1 "not found")
(get (get practice-map :key2) :sub_key_1 "not found")
(get-in practice-map [:key2 :sub_key_1] "not found")
(get-in practice-map [:key2 :sub_key_3] "not found")
(:key1 practice-map)
(:key21 practice-map "not found")
(practice-map :key1)
(def key-21-value (practice-map :key21 "not found"))
(if (nil? (practice-map :key21))
  "not found"
  "got it")
;; confused?
(if (nil? key-21-value)
  "not found"
  key-21-value)
(nil? (:key1 practice-map))
(def a-vector [:one :two practice-map 4 5])
(get a-vector 1)
(get a-vector 2)
(conj a-vector (:key2 (:sub_key_1 practice-map)))
(conj a-vector (:sub_key_1 (:key2 practice-map)) (:key1 practice-map))
(get
 (conj a-vector
       (:sub_key_1 (:key2 practice-map))
       (:key1 practice-map))
 (- (+ (count a-vector) 2) 1))
(def conjoined-vector
  (conj a-vector
       (:sub_key_1 (:key2 practice-map))
       (:key1 practice-map)))
(map str conjoined-vector)
(map println conjoined-vector)
(defn println-return-value
  [value]
  (println value)
  value)
(map println-return-value conjoined-vector)
;; tried with anonymous function - try again later
;; (map #([value] (println value) value) conjoined-vector)

;; lists
'(1 2 3 practice-map :five :six)
(def practice-list '(1 2 3 practice-map :five :six))

(nth practice-list 2)
(nth practice-list 3)
(:key1 practice-map)
(:key1 (nth practice-list 3))
(nth practice-list 0)
(nth (conj practice-list (:key1 practice-map)) 0)

(def my-hash-set #{"this is a value in a set" 222 :another_value})

(println "--- printing and hash sets-ing ---")
;; (map println my-hash-set)
;; (map println (conj my-hash-set 5))
(map println (conj my-hash-set #{"another value" 555 :and_another}))
(def vector-with-duplicates
  (vector (range 10) (range 10)))
(println vector-with-duplicates)
(def set-no-duplicates (set vector-with-duplicates))
(println set-no-duplicates)
(contains? set-no-duplicates 1)
(contains? set-no-duplicates (range 10))
(contains? set-no-duplicates nil)
(def another-set (conj set-no-duplicates [5 6 7]))
(println another-set)

(defn function-with-zero-one-or-two
  "this is a function that takes 0-2 arguments"
  ([]
   (println "no arguments were passed")
   "no arguments were passed")
  ([arg1]
   (println (str "one argument, " arg1 ", was passed"))
   (str "one argument, " arg1 ", was passed"))
  ([arg1 arg2]
   (println (str "two arguments, " arg1 " and " arg2 ", were passed"))
   (str "two arguments, " arg1 " and " arg2 ", were passed")))

(function-with-zero-one-or-two :one :two)

;; ATOMS and such

(def itinerary-item-1 (atom {:start-time "10:00am"
                             :description "Coffee Break"
                             :duration 15}))
(doto itinerary-item-1 prn) 
(doto @itinerary-item-1 prn) 
(println (:start-time @itinerary-item-1))
(swap! itinerary-item-1
       (fn [current-state]
         (merge-with + current-state {:duration 10})))
(swap! itinerary-item-1
       (fn [current-state]
         (assoc current-state :start-time "9:50am")))

(defn update-itinerary-item-description
  [item-state new-description]
  (assoc item-state :description new-description))

(update-itinerary-item-description @itinerary-item-1 "Introduction")
@itinerary-item-1
(swap! itinerary-item-1 update-itinerary-item-description "Introduction")

(let [item itinerary-item-1
      s1 @item]
  (swap! item update-in [:duration] - 10)
  (println "Previously:" s1)
  (println "Currently:" @item))

;; reset does not check the current value as in the swap process
(reset! itinerary-item-1 {:description "Welcome"
                          :start-time "7:30am"
                          :duration 30})

(defn duration-alert
  [key watched old-state new-state]
  (let [duration (:duration new-state)]
    (if (> duration 50)
      (do
        (println "Warning:")        
        (println "This item is running pretty long at " duration " min")
        (println "As per " key))
      (do
        (println "Valid for " key)
        (println "Description: " (:description new-state))
        (println "Duration: " (:duration new-state))))))       

(add-watch itinerary-item-1 :duration-alert-50 duration-alert)
(swap! itinerary-item-1 update-in [:duration] + 3)
(swap! itinerary-item-1 update-in [:duration] + 50)

(defn duration-validator
  [{:keys [duration]}]
  (or (and (>= duration 4)
           (<= duration 150))
      (throw (IllegalStateException.
              (str "Duration issue " duration
                   (if (< duration 4)
                     " too small"
                     " too long"))))))

(def itinerary-item-2
  (atom
   {:description "Breakfast"
    :start-time "7:55am"
    :duration 25}
   :validator duration-validator))

(swap! itinerary-item-2 update-in [:duration] - 15)

(def sock-varieties
  #{"darned" "argyle" "wool" "horsehair" "nulletted"
       "passive-aggressive" "striped" "polka-dotted"
       "athletic" "business" "power" "invisible" "gollumed"})
(defn sock-count
  [sock-variety count]
  {:variety sock-variety
   :count count})

(defn generate-sock-gnome
  "create an initial sock gnome state with no socks"
  [name]
  {:name name
   :socks #{}})

(def sock-gnome (ref (generate-sock-gnome "Barumpharumph")))
(def dryer (ref {:name "LG 1337"
                 :socks (set (map #(sock-count % 2) sock-varieties))}))
(defn steal-sock
  [gnome dryer]
  (dosync
   (when-let [pair (some #(if (= (count %) 2) %) (:socks @dryer))]
     (let [updated-count (sock-count (:variety pair) 1)]
       (alter gnome update-in [:socks] conj updated-count)
       (alter dryer update-in [:socks] disj pair)
       (alter dryer update-in [:socks] conj updated-count)))))
(steal-sock sock-gnome dryer)
(println (:socks @sock-gnome))
(defn similar-socks
  [target-sock sock-set]
  (filter #(= (:variety %) (:variety target-sock)) sock-set))
(similar-socks (first (:socks @sock-gnome)) (:socks @dryer))

;; (def counter (ref 0))
;; (future
  ;; (dosync
   ;; (alter counter inc)
   ;; (println @counter)
   ;; (Thread/sleep 500)
   ;; (alter counter inc)
   ;; (println @counter)))
;; (Thread/sleep 250)
;; (println @counter)

(defn sleep-print-update
  [sleep-time thread-name update-fn]
  (fn [state]
    (Thread/sleep sleep-time)
    (println (str thread-name ": " state))
    (update-fn state)))

(def counter (ref 0))
(future (dosync (commute counter (sleep-print-update 100 "Thread A" inc))))
(future (dosync (commute counter (sleep-print-update 150 "Thread B" inc))))
(println "Finally: " @counter)

(def receiver-a (ref #{}))
(def receiver-b (ref #{}))
(def giver (ref #{1}))
(do (future (dosync (let [gift (first @giver)]
                      (Thread/sleep 10)
                      (commute receiver-a conj gift)
                      (commute giver disj gift))))
    (future (dosync (let [gift (first @giver)]
                      (Thread/sleep 50)
                      (commute receiver-b conj gift)
                      (commute giver disj gift)))))
(println @receiver-a)
(println @receiver-b)
(println @giver)
@receiver-a
@receiver-b
@giver

(def ^:dynamic *notification-address* "dobby@elf.org")
(println *notification-address*)
(binding [*notification-address* "test1@elf.org"]
  (println *notification-address*)
  (binding [*notification-address* "test2elf.org"]
    (println *notification-address*))
  (println *notification-address*))
(println *notification-address*)
(defn notify
  [message]
  (str "TO: " *notification-address* "\n"
       "MESSAGE: " message))
(println (notify "I fell."))
(binding [*notification-address* "test@elf.org"]
  (println (notify "test!")))
(binding [*out* (clojure.java.io/writer "print-output")]
  (println "A man and a cat and learning ... something something --some Clemens or Twain...?"))
(slurp "print-output")
(println ["print" "all" "the" "things"])
(binding [*print-length* 1]
  (println ["print" "just" "one!"]))

(def ^:dynamic *troll-thought* nil)
(defn troll-riddle
  [your-answer]
  (let [number "man meat"]
    (when (thread-bound? #'*troll-thought*)
      (set! *troll-thought* number))
    (if (= number your-answer)
      "TROLL: You can cross the bridge!"
      "TROLL: Time to eat you, succulent human!")))

(binding [*troll-thought* nil]
  (println (troll-riddle 2))
  (println "SUCCULENT HUMAN: Oh! The answer was" *troll-thought*))

*troll-thought*

(.write *out* "prints to repl")
(.start (Thread. #(.write *out* "prints to standard out")))
(let [out *out*]
  (.start
   (Thread. #(binding [*out* out]
               (.write *out* "prints to repl from thread")))))
(.start (Thread. (bound-fn [] (.write *out* "prints to repl from thread"))))


(def power-source "hair")
(alter-var-root #'power-source (fn [_] "7-eleven parking lot"))
power-source
(with-redefs [*out* *out*]
  (doto (Thread. #(println "with redefs allows me to show up in the repl"))
    .start
    .join))

(defn always-1
  []
  1)
(take 5 (repeatedly always-1))
(take 5 (repeatedly (partial rand-int 10)))

(def alphabet-length 26)
(def letters (mapv (comp str char (partial + 65)) (range alphabet-length)))
(defn random-string
  "returns a random string of specified length"
  [length]
  (apply str (take length (repeatedly #(rand-nth letters)))))
(defn random-string-list
  [list-length string-length]
  (doall (take list-length (repeatedly (partial random-string string-length)))))
(def orc-names (random-string-list 3000 7000))

;; (time (dorun (map clojure.string/lower-case orc-names)))
;; (time (dorun (pmap clojure.string/lower-case orc-names)))

(def orc-name-abbreves (random-string-list 20000 300))
;; (time (dorun (map clojure.string/lower-case orc-name-abbreves)))
;; (time (dorun (pmap clojure.string/lower-case orc-name-abbreves)))

(def numbers [1 2 3 4 5 6 7 8 9 10])
(partition-all 3 numbers)
(pmap inc numbers)
(pmap (fn [number-group] (doall (map inc number-group)))
      (partition-all 3 numbers))

(apply concat (pmap (fn [number-group] (doall (map inc number-group)))
      (partition-all 3 numbers)))

(time
 (dorun
  (apply concat
         (pmap (fn [name] (doall (map clojure.string/lower-case name)))
               (partition-all 1000 orc-name-abbreves)))))
(defn ppmap
  "partitioned pmap, for grouping map ops together to make parallel overhead worthwile"
  [grain-size f & colls]
  (apply concat
         (apply pmap
                (fn [& pgroups] (doall (apply map f pgroups)))
                (map (partial partition-all grain-size) colls))))
(time (dorun (ppmap 1000 clojure.string/lower-case orc-name-abbreves)))

;; (ns playsync.core
  ;; (:require [clojure.core.async
             ;; :as a
             ;; :refer [>! <! <!! go chan buffer close! thread
                     ;; alts! alts!! timeout]]))
