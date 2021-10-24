(ns robot-simulator)

(defn robot [xy-location bearing] 
  "takes xy coordinates in a map ie {:x -2 :y 1} and a bearing :north :south :east :west and returns {:coordinates {:x -2 :y 1} :bearing :east}"
  (hash-map :coordinates xy-location :bearing bearing)
)

(defn simulate-move [bearing]
  "takes bearing and returns {:x transform-x :y transform-y} where transforms is sign and 1 if moving on that axis, otherwise 0"
  (cond
    (= bearing :north) {:x 0 :y 1}
    (= bearing :east) {:x 1 :y 0}
    (= bearing :south) {:x 0 :y -1}
    (= bearing :west) {:x -1 :y 0}
    :else (str "cannot process initial bearing [" bearing "]")
    ))

(defn simulate [move-string robot-instance]
  "takes move string and returns transform xy"
  ; split string into characters
  ; when A transform robot-instance by simulate move
  (let [xy-transform (simulate-move (:bearing robot-instance))]
    ; add simulate move :x to robot-instance :coordinates :x
    (swap! (:coordinates robot-instance) update-in [:x] + (:x xy-transform) )
    ; add simulate move :y to robot-instance :coordinates :y
    (swap! (:coordinates robot-instance) update-in [:y] + (:y xy-transform) )
    )
  ; when L turn-left robot-instance bearing
  (turn-left (:bearing robot-instance))
  ; when R turn-right robot-instance bearing
  (turn-right (:bearing robot-instance))
  )

(defn turn-right [bearing]
  "takes initial bearing and returns new bearing after turning to the right"
  (cond
    (= bearing :north) :east
    (= bearing :east) :south
    (= bearing :south) :west
    (= bearing :west) :north
    :else (throw (IllegalStateException. (str "cannot process initial bearing [" bearing "]")))
    )
)

(defn turn-left [bearing] 
  "takes initial bearing and returns new bearing after turning to the left"
  (cond
    (= bearing :north) :west
    (= bearing :west) :south
    (= bearing :south) :east
    (= bearing :east) :north
    :else (str "cannot process initial bearing [" bearing "]")
    )
  )
