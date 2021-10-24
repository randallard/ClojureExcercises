(ns etl)

(defn transform [source]
  (reduce (fn [this-final-map this-key]
     (reduce (fn [final-map value]
               (into final-map 
                     (hash-map (clojure.string/lower-case value) this-key)))
             this-final-map
             (get source this-key)))
          {}
          (keys source)))

(let [source {1 ["a" "b"]
              2 ["c" "d"]}]
  (reduce
   (fn [this-final-map this-key]
     (reduce
      (fn [final-map value]
        (into final-map
              (hash-map
               value
               this-key)))
      this-final-map
      (get source this-key)))
   {}
   (keys source)))

(defn transform-bmaddy [m]
  (into {}
        (for [[score letters] m
              letter letters]
          [(clojure.string/lower-case letter) score])))

(defn transform-mikelikesbikes [m]
  (into {}
        (for [k (keys m)
              v (get m k)]
          [(clojure.string/lower-case v) k])))
