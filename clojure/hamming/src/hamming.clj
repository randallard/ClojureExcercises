(ns hamming)
(defn increment-difference
  [char1 char2 difference-count]
  (if (not (= char1 char2))
    (inc difference-count)
    difference-count))

(defn distance
  ([strand1 strand2]
   (if (not (=  (count strand1) (count strand2)))
     nil
     (distance strand1 strand2 0)))
  ([[this-char & strand1] [that-char & strand2] difference-count]
   (if (empty? strand1)
     (increment-difference this-char that-char difference-count)
     (recur strand1 strand2 (increment-difference this-char that-char difference-count)))))

(defn distance-soulflyer
  [a b]
  (when (= (count a) (count b))
    (count (filter false? (map = a b)))))
