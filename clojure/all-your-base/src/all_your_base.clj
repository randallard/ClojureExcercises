(ns all-your-base)

(defn to-decimal
  "return a list of each column as its decimal value"
  [col-values base]
  (if (some #(<= base %) col-values)
    nil
    (map #(* %1 (int (Math/pow base %2))) col-values
         (reverse (take (count col-values) (range))))))  
(defn number-is-greater-than-zero
  [[base number cols]]
  (> number 0))
(defn conj-remainder-to-cols
  [[base number cols]]
  [base
   (int (Math/floor (/ number base)))
   (conj cols (mod number base))])
(defn last-step
  [[base first-col cols]]
  (conj cols first-col))
(defn from-decimal
  "return a list of each column value for a number converted from decimal"
  [decimal base]
  (->> (last (take-while number-is-greater-than-zero
                         (iterate conj-remainder-to-cols [base decimal '()])))
       (last-step)))
(let [decimal 229
      base 8]
  (last-step (last (take-while number-is-greater-than-zero (iterate conj-remainder-to-cols [base decimal '()])))))

(defn convert
  "convert number-in-cols from this-base to that-base where number in cols is a list of the numbers digits in positional notation"
  [this-base number-in-cols that-base]
  (cond
    (not-every? #(<= 2 %) [this-base that-base]) nil
    (not-every? #(< % this-base) number-in-cols) nil
    (not-every? #(>= % 0) number-in-cols) nil
    (empty? number-in-cols) '()
    (not-every? #(zero? %) number-in-cols)
    (let [decimal (apply + (to-decimal number-in-cols this-base))]
      (from-decimal decimal that-base))
    :else '(0)))

(apply conj [0] [1])

;; pascal's triangle solution - try to use iterate like this
(defn pascal-row [n]
  (take n
        (iterate
         (fn next-row
           [v]
           (vec (map +
                     (apply conj [0] v)
                     (conj v 0))))
         [1])))

(last (pascal-row 10))

(take-while #(not (nil? %)) (iterate (fn [[x & y]] y) '(0 1 2 3 4 5)))

(pascal-row 7)

(defn fib
  [n]
  (take n
        ;; (map first
             (iterate
              (fn [[a b]] [b (+' a b)])
              '(0 1)))
        ;; )
  )
(fib 7)

