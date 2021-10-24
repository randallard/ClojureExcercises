(ns multimethods.core
  (:gen-class))

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people to sweat to the oldies"))
(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))
(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))
(full-moon-behavior {:were-type :office-worker
                     :name "Jimmy from sales"})
(full-moon-behavior {:were-type nil
                     :name "Martin the nurse"})
(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})
(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})
(defmulti types (fn [x y] [(class x) (class y)]))
(defmethod types [java.lang.String java.lang.String]
  [x y]
  "Two strings!")
(defmethod types :default 
  [x y]
  (str "You sent " (class x) " " (class y)))
(types "string 1" "string 2")
(types 1 2)

(ns data-phsychology)
(defprotocol Psychodynamics
  "Plumb the inner depths of your data types"
  (thoughts [x] "The data type's innermost thoughts")
  (feelings-about [x] [x y] "Feelings about self or other"))
;; (extend-type java.lang.String
  ;; Psychodynamics
  ;; (thoughts [x] (str x " thinks, 'Truly, the character defines the data type'"))
  ;; (feelings-about
    ;; ([x] (str x " is longing for a simpler way of life"))
    ;; ([x y] (str x " is envious of " y "'s simpler way of life"))))

;; (extend-type java.lang.Object
  ;; Psychodynamics
  ;; (thoughts [x] "Maybe the Internet is just a vector for toxoplasmosis")
  ;; (feelings-about
    ;; ([x] "meh")
    ;; ([x y] (str "meh about " y))))
(extend-protocol Psychodynamics
  java.lang.String
  (thoughts [x] (str x " thinks, 'Truly, the character defines the data type'"))
  (feelings-about
    ([x] (str x " is longing for a simpler way of life"))
    ([x y] (str x " is envious of " y "'s simpler way of life")))

  java.lang.Object
  (thoughts [x] "Maybe the Internet is just a vector for toxoplasmosis")
  (feelings-about
    ([x] "meh")
    ([x y] (str "meh about " y))))

(thoughts "blorb")
(feelings-about "schmorb")
(feelings-about "schmorb" 2)
(thoughts 3)
(feelings-about 3)
(feelings-about 3 "blorb")

(ns were-records)
(defrecord WereWolf [name title])
(WereWolf. "David" "London Tourist")
(->WereWolf "Jacob" "Lead Shirt Discarder")
(map->WereWolf {:name "Lucian" :title "CEO of Melodrama"})

(ns monster-mash
  (:import [were_records WereWolf]))
(WereWolf. "Ryan" "Loves silver")

(def ryan (->WereWolf "Ryan" "Hates fur"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
