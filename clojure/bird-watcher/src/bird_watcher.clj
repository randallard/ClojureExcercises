(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])
(def birds-per-day
  [2 5 0 7 4 1])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (update birds (dec (count birds)) inc))

(defn day-without-birds? [birds]
  (not (nil? (some #(= 0 %) birds))))

(defn n-days-count [birds n]
  (apply + (take n birds)))

(defn busy-days [birds]
  (def busy-count 5)
  (def busy-day-count ((frequencies (map #(conj [] (>= % busy-count)) birds)) [true]))
  (if (nil? busy-day-count)
    0
    busy-day-count)
  )

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1])
  )
