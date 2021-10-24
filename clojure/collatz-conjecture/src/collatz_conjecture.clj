(ns collatz-conjecture)

(defn get-n-over-2
  [n]
  (/ n 2))
(defn get-3n-plus-1
  [n]
  (+ 1 (* 3 n)))
(defn collatz 
  ([num count]
   (println (str count " " num))
   (cond
     (= 1 (get-n-over-2 num)) (inc count)
     (even? num) (recur (get-n-over-2 num) (inc count))
     (odd? num) (recur (get-3n-plus-1 num) (inc count))))
  ([num] ;; <- arglist goes here
   ;; your code goes here
   (cond
     (< num 1) (throw (Exception. "The starting number must be greater than zero"))
     (= num 1) 0
     (even? num) (collatz num 0)
     (odd? num) (collatz num 0)
     :else (throw (Exception. (str "Can't process the number " num))))))

