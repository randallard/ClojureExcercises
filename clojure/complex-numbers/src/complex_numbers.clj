(ns complex-numbers)

(defn real [[a b]] 
  a
)

(defn imaginary [[a b]]
  b
)

(defn abs [[a b]] 
  ;; square root of a^2 + b^2
  (Math/sqrt (+ (* a a) (* b b)))
)

(defn conjugate [[a b]] 
  [a (* -1 b)]
)

(defn add [[a b] [c d]] 
  [(+ a c) (+ b d)]
)

(defn sub [[a b] [c d]] 
  [(- a c) (- b d)]
)

(defn mul [[a b] [c d]] 
  [(- (* a c) (* b d)) (+ (* b c) (* a d))]
)

(defn div [[a b] [c d]] ;; <- arglist goes here
  ;; (a * c + b * d)/(c^2 + d^2) + (b * c - a * d)/(c^2 + d^2) * i
  [ (float (/ (+ (* a c) (* b d)) (+ (* c c) (* d d))))
   (/ (- (* b c) (* a d)) (+ (* c c) (* d d)))]
)

