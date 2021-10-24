(ns nth-prime)
(defn has-no-other-factors? [number]
  (= 0 (count (take 1 (filter #(zero? (mod number %)) (range 2 number))))))
(defn nth-prime [n]
  (last (take n (filter #(has-no-other-factors? %) (drop 2 (range)))))
  )
