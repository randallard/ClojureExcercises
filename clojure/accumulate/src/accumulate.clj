(ns accumulate)
(defn- square [n] (* n n))
(defn- to-s [xs] (apply str xs))

(defn accumulate [do-this [to-this & to-these]]
  (if (nil? to-this)
    []
    (if (nil? to-these)
      (cons (do-this to-this) to-these)
      (cons (do-this to-this) (accumulate do-this to-these))))
)
(accumulate square [])
(= ["yppah" "syad" "era" "ereh" "niaga"]
   (->> ["happy" "days" "are" "here" "again"]
        ;; (accumulate reverse)
        (trikitrok-accumulate reverse)
        (map to-s)))

(defn trikitrok-accumulate [func coll]
  (for [x coll] (func x)))

(trikitrok-accumulate square [])
