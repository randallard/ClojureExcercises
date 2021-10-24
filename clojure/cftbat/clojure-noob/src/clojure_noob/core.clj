(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn train
  []
  (println "Choo choo!"))

;; forms (valid code)
1
"a string"
["a" "vector" "of" "strings"]

;; operations

(+ 1 2 3)

(str "It was the panda " "in the library " "with the dust buster")

;; conditionals
(if true
  "By Zeus's hammer!"        ;; evaluate if true
  "By Aquaman's trident!")   ;; evaluate if false

(if false
  "By Odin's Elbow!")        ;; return nil if false and there's no false line

;; multiple lines in conditionals

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

;; when 
;; executes multiple lines when true
;; returns nil when false

(when true
  (println "Success!")
  "abra cadabra")

(nil?               ;; return true if expression is nil
 (when true
   (println "Success!")
   "abra cadabra"))

(if "cats"
  "of course"
  "dogs")

(if nil
  "dogs"
  "never dogs")

;; equality

(= 1 1)
(= nil nil)
(= 1 2)

;; or - returns first truthy value or the last value

(or false nil :my_true_value :another_true_value)
(or false false (= 1 0))

;; and 
;; returns first falsey value or the last truthy value

(and false 7)
(and true 8 7)

;; naming values with def

(defn not_my_true_value 
  [third_value]
  (or false nil third_value :another_true_value)
)
(not_my_true_value false) ;; returns :another_true_value
(not_my_true_value true) ;; returns true

;; Data structures
;; my_animals = [
              ;; "cows",
              ;; "dogs",
              ;; "chickens",
              ;; "pigs",
              ;; "goats"
              ;; ]

;; Numbers

93
1.2
(* 1/5 2/3)

              "dogs"
              "\"dogs\""

(def name "Newbacca")
(str "\"Snugglglglglgl\" - " name)

;; Maps

{} ;; empty
{:first-name "Charlie" :last-name "Puth"}
{"string-key" +}
{:name {:first "John" :middle "Jacob" :last "Jnglhmrschmidt"}}
(hash-map :a 1 :b 2)

;; gettting values from maps

(get (hash-map :a 1 :b 2) :b)
(get (hash-map :a 1 :b 2) :c "default value")
(get {:a 1 :b 2} :c "default value")
(get {:a 1 :b {:c "value"}} :b)
(get-in {:a 1 :b {:c "value"}} [:b :c])
({:a 1 :b {:c "value"}} :a)
(({:a 1 :b {:c "value"}} :b) :c)
(:c (:b {:a 1 :b {:c "value"}}))
(:this_is_an_undefined_key_name (:b {:a 1 :b {:c "value"}}) "this is a default value for an undefined keyword")
(:the_car (:the_shop {:the_garage "Tesla" :the_shop {:the_bike "Giant" :the_car "Cougar"}}))

;; Vectors

[3 2 1]
(get [3 2 1] 0) 
(get ["a" {:name "Smith Schmitty"} "c"] 1)

(vector 3 2 1)
(get (vector 3 2 1) 0) 
(get (vector "a" {:name "Smith Schmitty"} "c") 1)

(conj [3 2 1] 4)
'(1 2 3 4)
(nth '(1 2 3 4) 0)
(nth '(:a :b :c) 1)

(list 1 2 3 4)
(nth (list 1 2 3 4) 0)
(nth (list :a :b :c) 1)

(conj (list 1 2 3 4) '(:a {1 2} :three))
(conj (list 1 2 3 4) 5)

;; vector list rules of thumb
;; when items need added to the beginning use lists
;; when writing a macro use lists
;; otherwise use a vector



;; Sets

#{"apples" 2 :bear}
(hash-set "apples" 2 :bear)

;; #{"apples" 2 :bear 2 :bear} ;; error - duplicates
(hash-set "apples" 2 :bear  2 :bear)
(conj (hash-set "apples" 2 :bear  2 :bear) "apples")

(set (hash-set "apples" 2 :bear  2 :bear))

(set ["apples" 2 :bear  2 :bear])

(contains? (hash-set "apples" 2 :bear  2 :bear) nil)
(contains? (hash-set "apples" 2 :bear  2 :bear) 2)
;; (2 (hash-set "apples" 2 :bear  2 :bear)) ;; error

(:bear (hash-set "apples" 2 :bear  2 :bear))
(:cow (hash-set "apples" 2 :bear  2 :bear))


(get (hash-set "apples" 2 :bear  2 :bear) :bear)
(get (hash-set "apples" 2 :bear  2 :bear) :cat)

(get (hash-set "apples" 2 :bear  2 :bear) nil)

(get (hash-set "apples" 2 :bear  2 :bear nil) nil)

;; Functions

((or + -) 1 2 3)

((and (= 1 1) +) 1 2 3)
(map inc [0 1 2 3])


(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [object-of-affection]
  (str "OH. MY. GOD! " object-of-affection ", YOU ARE MOST DEFINITELY THE MOST AWESOME")
)
(too-enthusiastic "Brianna")

(defn no-params
  []
  "I take no parameters!")

(defn two-params
  [x y]
  (str "two parameters... smooshed " x y))

;; overloading

(defn multi-arity-function
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
   (str "three args " first-arg second-arg third-arg ))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
   (str "two args " first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
   (str "one arg " first-arg)))

;; use overloading to assign defaults
(defn one-default
  "describe an attack on someone - default to karate "
  ([attack-object chop-type]
   (str "I " chop-type " chop " attack-object "! Take that!"))
  ([attack-object]
   (one-default attack-object "karate")))

;; assign all additional parameters to a list
(defn parameter-to-do-a-thing-with
  "do a thing with the list"
  ([whippersnapper]
   (str "Get off my lawn, " whippersnapper "!!!")))

(defn extra-parameters-to-a-list
  "extra parameters are assigned to a list"
  ([& extra-parameters-this-list]
   (map parameter-to-do-a-thing-with extra-parameters-this-list)))

;; combined rest parameter last parameter
(defn one-then-rest-parameters
  "do a thing with one then do the rest thing"
  [first-thing & rest-of-things]
  (str "A thing: [" first-thing "]; Then any more here: ["
       (clojure.string/join ", " rest-of-things) "]"))

;; this didn't work the way I thought it would...
(defn one-then-rest-parameters-2
  "do a thing with one then do the rest thing"
  [first-thing & rest-of-things]
  (print "A thing: [" first-thing "]")
  (extra-parameters-to-a-list rest-of-things))


;; destructuring parameters
(defn just-the-first-and-ignore-the-rest
  "ignore the rest, return the first"
  [[first-thing]] ; vector in a vector
  first-thing)

(just-the-first-and-ignore-the-rest [1 2 3])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "here they are in case you neet to cry over them "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["one" "two" "three" "Handsome Jack" "The Twelfth Doctor"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})

(defn destructure-map
  [{a :a b :b}]
  (println (str "first parameter: " a))
  (println (str "second parameter: " b)))

(destructure-map {:a "one" :b "another"})

(defn another-way-destructure-map
  [{:keys [a b] :as parameters}]
  (println (str "first parameter: " a))
  (println (str "second parameter: " b))
  (println (str "first parameter from map: " (get parameters :a)))
  (println (str "second parameter from map: " (get parameters :b)))
  ;; (list-parameters! parameters))
  )

  (another-way-destructure-map {:a "one" :b "another"})

;; Function Body
;; clojure returns the last form evaluated in the function body

(defn multi-line-body
  "no parameters and a bunch of stuff with the last line being 'joe'"
  []
  (+ 1 304)
  30
  "joe")

(multi-line-body)

(defn call-with-number-parameter
  "takes a number?  returns a string response"
  [x]
  (if (> x 6)
    "greater than 6 is this number"
    "less than or equal to 6 is this number"))

(call-with-number-parameter 4)

(call-with-number-parameter 7)

;; (call-with-number-parameter "1")

(map (fn [name-of-thing] (str "Hi, " name-of-thing))
     ["Darth Vader" "Mr. Magoo"])

((fn [x] (* x 3)) 8)

(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)

(#(* % 3) 8)

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(#(println %1 " and " %2) "cornbread" "honey") 

;; anonymous function and rest parameter

(#(identity %&) 1 2 3 4 "cose" "cows")

;; returning functions

(defn inc-maker
  "create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-") :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(let [x 3]
  x)

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

(def x 0)
(let [x 1] x)

(def x 0)
(let [x (inc x)] x)

(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])

(into [] (set [:a :a]))

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))
(recursive-printer)

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(matching-part {:name "left-eye" :size 1})
(matching-part {:name "hash-cat" :size 3})

(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

;; reduce
(reduce + [1 2 3 4])

;; reduce with initial value
(reduce + 15 [1 2 3 4])

;; broken down
;; reduce with initial value
(reduce
 ;; function
 + 
 ;; initial value
 15
 ;; set to process

 [1 2 3 4])

(reduce 
 ;; function
 (fn [final-body-parts part]
          (into final-body-parts (set [part (matching-part part)])))
 ;; initial value
        []
 ;; set to process
        asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts ( better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

;; Excercises
;; 1. use the str, vector, list, hash-map and hash-set functions
(str "a string")
(vector 1 2)
((vector 1 2) 0)
(list "a" "b" :c :d)
;; (1 (list "a" "b" :c :d)) ;; doesn't work
;; ((list "a" "b" :c :d) 1) ;; doesn't work
 
(nth (list "a" "b" :c :d) 0)
;; (nth 0 (list "a" "b" :c :d)) ;; doesn't work


'(1 2 3 4)
(nth '(1 2 3 4) 2)

(defn my-100x-incrementor
  [number-to-increment-100x]
  (+ number-to-increment-100x 100))

(defn dec-maker
  [dec-by]
  #(- % dec-by))

(def dec9 (dec-maker 9))

(dec9 10)

(hash-set 2 3 4)

(defn mapset
  [set-this]
  (hash-set set-this))

(mapset [1 1 2 2])

(map inc [1 2 3])
(map str ["a" "b" "c"] ["A" "B" "C"])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
(map unify-diet-data human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [5 5 3 4 5 6 7 8 9])

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "your mom"}
   {:alias "Easter Bunny" :real "your dad"}])

(map :real identities)

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:minion 38 :master 1})

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

(take 3 [1 3 4 3 2 3 5 6 7])
(drop 3 [1 3 4 3 2 3 5 6 7])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 2 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.9}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)

(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))

(drop-while #(< (:month %) 2)
            (take-while #(< (:month %) 4) food-journal))

(filter #(< (:human %) 5) food-journal)

(some #(> (:critter %) 5) food-journal)

(some #(> (:critter %) 3) food-journal)

(some #(and (> (:critter %) 3) %) food-journal)

(filter #(> (:critter %) 3) food-journal)

(sort [3 1 2])

(sort-by count ["aaa" "c" "bb"])

(concat [1 2] [3 4])

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true, :has-pulse? true :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

;; (time (vampire-related-details 0))

;; (time (def mapped-details (map vampire-related-details (range 0 1000000))))  
  
;; (time (first mapped-details))

;; (time (identify-vampire (range 0 1000000)))

(concat (take 8 (repeat "na")) ["Batman!"])

(take 3 (repeatedly (fn [] (rand-int 100000))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

(empty? [])
(empty? ["no!"])

(map identity {:sunlight-reaction "Glitter!"})

(into {} (map identity {:sunlight-reaction "Glitter!"}))

(map identity [:garlic :sesame-oil :fried-eggs])

(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

(map identity [:garlic-clove :garlic-clove])

(into #{} (map identity [:garlic-clove :garlic-clove]))

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])

(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:relationship-with-teenager "creepy"
                                  :least-favorite-smell "dog"})

(into [0] [1])
(conj [0] [1])
(conj [0] 1)
(conj [0] 1 2 3 4 )
(conj {:time "midnight"} [:place "ye olde cemtarium"])

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)

(max 0 1 2)

(max [0 1 2])

(apply max [0 1 2])

(def add10 (partial + 10))

(add10 3)

(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

(def add20 (my-partial + 20))
(add20 3)

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(lousy-logger :warn "Red light ahead")

(def warn (partial lousy-logger :warn))

(warn "Red light ahead")

(def not-vampire? (complement vampire?))
;; (defn identify-humans
  ;; [social-security-numbers]
  ;; (filter #(not (vampire? %))
          ;; (map vampire-related-details social-security-numbers)))

(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))

;; (identify-vampire (range 0 4))
;; (identify-humans (range 0 4))

;; created new Leiningen app 
;; lein new app fwpd

;; (println "back in noob-land")

;; (defn sum
  ;; ([vals] (sum vals 0))
  ;; ([vals accumulating-total]
   ;; (if (empty? vals)
     ;; accumulating-total
     ;; (sum (rest vals) (+ (first vals) accumulating-total)))))

;; Better implementation because recur is optimized for speed

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))


(sum [39 5 1])

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "   My boa constrictor is so sassy lol!   ")

((comp inc *) 2 3)

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
(c-str character)
(c-dex character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character)

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(spell-slots-comp character)

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;; second function, inc, in the following, must take only 1 arg
((two-comp inc *) 5 1 2 3 4)

(=
 (+ 3 (+ 5 8))
 (+ 3 13)
 16)


(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)

;; (sleepy-identity "Mr. Fantastico")

(def memo-sleepy-identity (memoize sleepy-identity))

;; (memo-sleepy-identity "Mr. Fantastico")


(ns-interns *ns*)
(get (ns-interns *ns*) 'memo-sleepy-identity)

(deref #'clojure-noob.core/memo-sleepy-identity)
;; (defmacro my-print-bad
  ;; [expression]
  ;; (list let [result expression]
    ;; (list println result)
    ;; result))

(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

(my-print "printed from macro")
(my-print (+ 1 2))
(my-print '(+ 1 2))

(macroexpand '(when (the-cows-come :home)
                (call me :pappy)
                (slap me :silly)))

(macroexpand '(unless (done-been slapped? me)
                      (slap me :silly)
                      (say "recon that'll learn ya")))

+
'x
`x
'(+ 1 2)
`(+ 1 2)
`(+ 1 ~(inc 1))

(defmacro code-critic
  "Phrases are courtesy Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Gread squid of Madrid, this is bad code:"
              (list 'quote bad))
        (list 'println
              "Sweet gorilla of Manilla, this is good code:"
              (list 'quote good))))

(code-critic (1 + 1) (+ 1 1))

(defmacro code-critic-again
  "Phrases still courtesy Hermes Conrad from Fururama"
  [bad good]
  `(do (println "Great squid of Madrid, this is bad code:"
                (quote ~bad))
       (println "Great gorilla of Manilla, this is good code:"
                (quote ~good))))

(code-critic-again (1 + 1) (+ 1 1))

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))
(defmacro code-critic-with-helper
  [bad good]
  `(do ~(criticize-code "Cursed bacteria of Liberia, this is bad code:" bad)
       ~(criticize-code "Sweet sacred boa of Western and Eastern Samoa, this is good code:" good)))

(code-critic-with-helper (1 + 1) (+ 1 1))

(defmacro code-critic-with-seq
  [bad good]
  `(do ~(map #(apply criticize-code %)
             [["Great squid of Madrid, this is bad code:" bad]
              ["Sweet gorilla of Manilla, this is good code:" good]])))

;; (code-critic-with-seq (1 + 1) (+ 1 1))

;; unquote splicing

;; need to fix this
`(+ ~(list 1 2 3)) 

;; unquote splicing looks like this
`(+ ~@(list 1 2 3))

(defmacro code-critic-seq-splicing
  [{:keys [good bad]}]
  `(do ~@(map #(apply criticize-code %)
              [["Sweet lion of Zion, this is bad code:" bad]
               ["Great cow of Moscow, this is good code:" good]])))

(code-critic-seq-splicing {:good (1 + 1) :bad (+ 1 1)})

;; macro gotchas

(def message "Good job!")

(defmacro with-mischief
  [& stuff-to-do]
;; should be like
;; `(let ['message "Oh, big deal!"])
;;      ~@stuff-to-do))
  (concat (list 'let ['message "Oh, big deal!"])
          stuff-to-do))
(println "-- -- -- -- --")
(with-mischief (println "Here's how I feel about that thing you did:" message))
(println "Here's how I feel about that thing you did:" message)
;; use instead of let
(gensym)

(gensym 'message)

(defmacro without-mischief
  [& stuff-to-do]
  (let [macro-message (gensym 'message)]
    `(let [~macro-message "Oh, big deal!"]
       ~@stuff-to-do
       (println "I still need to say:" ~macro-message))))
(println "------")
(without-mischief (println "Here's how I feel about that thing you did:" message))
(println "Here's how I feel about that thing you did:" message)

;; macro review
(map inc [1 2])
'(map inc [1 2])

;; make this work
;; (defmacro map-inc
  ;; '(map inc [1 2]))
;; (map-inc)

(defmacro infix
  "use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))
(infix (1 + 1))
(macroexpand '(infix (1 + 1)))
(defmacro infix-2
  [[operand1 op operand2]]
  (list op operand1 operand2))
(infix-2 (1 + 1))
(macroexpand '(infix-2 (1 + 1)))

(macroexpand '(when (the-cows-come :home)
                (call me :pappy)
                (slap me :silly)))

(macroexpand '(unless (done-been slapped? me)
                      (slap me :silly)
                      (say "I reckon that'll learn me")))

(defmacro say-a-thing
  [this that]
  `(do (println "Here's this:" (quote ~this))
       (println "Here's that:" (quote ~that))))

(say-a-thing (inc 1) (1))

(def apple '(1 2 3 4))
(nth apple 2)

(defmacro with-mischief-2
  [& stuff-to-do]
;; should be like
;; `(let ['message "Oh, big deal!"])
;;      ~@stuff-to-do))
  `(concat ~@(list stuff-to-do)))
;;(with-mischief-2 (println "Here's how I feel about that thing you did:" message))

(range 5)
(for [x (range 2) y (range 2)] [x y])
(bit-xor 1 2)
(for [x (range 2) y (range 2)]
  [x y (bit-xor x y)])
(defn xors [max-x max-y]
  (for [x (range max-x) y (range max-y)]
    [x y (rem (bit-xor x y) 256)]))

(xors 2 2)

;; (def frame (java.awt.Frame.))
;; frame
;; (for [meth (.getMethods java.awt.Frame)
      ;; :let [name (.getName meth)]
      ;; :when (re-find #"Vis" name)]
  ;; name)
;; (.isVisible frame)
;; (.setVisible frame true)
;; (.setSize frame (java.awt.Dimension. 200 200))
;; (def gfx (.getGraphics frame))
;; (.fillRect gfx 100 100 50 75)
;; (.setColor gfx (java.awt.Color. 255 128 0))
;; (.fillRect gfx 100 150 75 50)
;; (doseq [[x y xor] (xors 500 500)]
  ;; (.setColor gfx (java.awt.Color. xor xor xor))
  ;; (.fillRect gfx x y 1 1))
;; (defn clear [g] (.clearRect g 0 0 200 200))
;; (clear gfx)

(def order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"})
(def batch-order-details
  [{:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"}
   {:name ""
    :email "myemailemail"}
   {:name "apple tinter"
    :email "apple_tinter@email.com"}
   {:name ""
    :email ""}
   {:name ""
    :email "email@email.com"}])
   
(def order-details-validations
  {:name
   ["Please enter a name" not-empty]
   :email
   ["Please enter an email address" not-empty
    "Your email address doesn't look right"
    #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(error-messages-for "asdf" ["Please enter a name" not-empty])
(error-messages-for "" ["Please enter a name" not-empty])

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(validate order-details order-details-validations)
(println "Order:" (get batch-order-details 0))
(println "Error:" (validate (get batch-order-details 0) order-details-validations))
(println "Order:" (get batch-order-details 1))
(println "Error:" (validate (get batch-order-details 1) order-details-validations))
(println "Order:" (get batch-order-details 2))
(println "Error:" (validate (get batch-order-details 2) order-details-validations))
(println "Order:" (get batch-order-details 3))
(println "Error:" (validate (get batch-order-details 3) order-details-validations))
(println "Order:"  (get batch-order-details 4))
(println "Error:" (validate (get batch-order-details 4) order-details-validations))

;; write a function to do the previous, then reduce over batch-order-details
(defn print-order-details-and-errors
  "Prints label and data for order details and errors"
  [to-validate]
  (println "Order details:" to-validate)
  (let [errors (validate to-validate order-details-validations)]
    (if (empty? errors)
      (println "data is valid")
      (println "Error:" errors))))

(print-order-details-and-errors (get batch-order-details 2))

(println "Reduce over batch")
(map print-order-details-and-errors batch-order-details)

(let [errors (validate order-details order-details-validations)]
  (if (empty? errors)
    (println :success)
    (println :failure errors)))

(defmacro if-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~@then-else)))

(println "Doing it with a macro, if-valid")

(if-valid order-details order-details-validations errors
          (println :success)
          (println :failure errors))

(defn print-order-details-and-errors-with-macro
  "Prints label and data for order details and errors"
  [to-validate]
  (println "Order details:" to-validate)
  (if-valid to-validate order-details-validations errors
            (println "data is valid")
            (println "Error:" errors)))

(println "Trying the macro with one valid record")        
(print-order-details-and-errors-with-macro (get batch-order-details 2))
(println "Reducing the function with macro over the whole batch")
(map print-order-details-and-errors-with-macro batch-order-details)

;; macro excercises

(defmacro when-valid
  [to-validate validations errors-name do-action]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~do-action)))

(defn print-order-details-and-errors-with-when
  "Prints label and data for order details and errors"
  [to-validate]
  (println "Order details:" to-validate)
  (when-valid to-validate order-details-validations errors
            (println "data is valid")))

(println "Reducing the function with when macro over the whole batch")
(map print-order-details-and-errors-with-when batch-order-details)

;; (web-api/get :dwarven-beard-waxes)

;; (println "printing in futures")
;; (future (Thread/sleep 4000)
        ;; (println "I'll print after every 4 seconds"))
;; (println "I'll print immediately")

(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))

(deref (future (Thread/sleep 1000) 0) 10 5)
(realized? (future (Thread/sleep 1000)))
(let [f (future)]
  @f
  (realized? f))

(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there"]
           (println "First deref:" message)
           message)))
(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])
(defn email-user
  [email-address]
  (println "Sending headshot notification to" email-address))
(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)
;; (let [notify (delay (email-user "and-my-axe@gmail.com"))]
  ;; (doseq [headshot gimli-headshots]
    ;; (future (upload-document headshot)
            ;; (force notify))))

(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})
(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})
(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  "If the butter meets our criteria, return the butter, else return false"
  [butter]
  (and (<= (:price butter) 100)
       (>= (:smoothness butter) 97)
       butter))

;; (time (some (comp satisfactory? mock-api-call)
            ;; [yak-butter-international butter-than-nothing baby-got-yak]))

;; (time
 ;; (let [butter-promise (promise)]
   ;; (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
     ;; (future (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
               ;; (deliver butter-promise satisfactory-butter))))
   ;; (println "And the winner is:" @butter-promise)))

(let [p (promise)]
  (deref p 100 "timed out"))

;; put the timeout in the butter-promise 

;; do it




(let [ferengi-wisdom-promise (promise)]
  (future (println "Here's some Ferengi wisdom:" @ferengi-wisdom-promise))
  (Thread/sleep 100)
  (deliver ferengi-wisdom-promise "Whisper your way to success."))

(defmacro wait
  "Sleep `timeout` seconds before evaluating body"
  [timeout & body]
  `(do (Thread/sleep ~timeout) ~@body))

(let [saying3 (promise)]
  (future (deliver saying3 (wait 100 "Cheerio!")))
  @(let [saying2 (promise)]
     (future (deliver saying2 (wait 400 "Pip pip!")))
     @(let [saying1 (promise)]
        (future (deliver saying1 (wait 200 "'Ello, gov'na!")))
        (println @saying1)
        saying1)
     (println @saying2)
     saying2)
  (println @saying3)
  saying3)

(defmacro enqueue
  ([q concurrent-promise-name concurrent serialized]
   `(let [~concurrent-promise-name (promise)]
      (future (deliver ~concurrent-promise-name ~concurrent))
      (deref ~q)
      ~serialized
      ~concurrent-promise-name))
  ([concurrent-promise-name concurrent serialized]
   `(enqueue (future) ~concurrent-promise-name ~concurrent ~serialized)))

(time @(-> (enqueue saying (wait 200 "'Ello, gov'na!") (println @saying))
           (enqueue saying (wait 400 "Pip pip!") (println @saying))
           (enqueue saying (wait 100 "Cheerio!") (println @saying))))
