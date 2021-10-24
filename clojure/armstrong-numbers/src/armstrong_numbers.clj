(ns armstrong-numbers)
(defn find-power
  [number to-power]
  (cond
    (= to-power 0) 1
    (= to-power 1) number
    (= to-power 2) (* number number)
    :else (* number (find-power number (- to-power 1)))))

(defn armstrong?
  [num]
  (let [list (map #(Character/digit % 10) (str num))
        exponent (count list)
        sum-of-exp (apply + (map #(find-power % exponent) list))]
    (= num sum-of-exp)))
(find-power 0 1)
