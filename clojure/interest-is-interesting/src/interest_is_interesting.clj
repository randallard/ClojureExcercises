(ns interest-is-interesting)

(defn interest-rate
  "calculate the interest rate based on the specified balance"
  [balance]
  (cond
    (< balance 0) -3.213
    (< balance 1000) 0.5
    (< balance 5000) 1.621
    :else 2.475)
  )

(defn annual-balance-update
  "calculate annual balance update, taking into account the interest rate"
  [balance]
  (if (>= balance 0)
    (* balance (+ 1 (/ (bigdec (interest-rate balance)) 100)))
    (* balance (- 1 (/ (bigdec (interest-rate balance)) 100))))
  )

(defn amount-to-donate
  "calculate how much money to donate to charities based on balance and tax-free percentage"
  [balance tax-free-percentage]
  (if (> balance 0)
    (int (Math/floor  (* 2 (* (/ tax-free-percentage 100) balance))))
    0)
  )

