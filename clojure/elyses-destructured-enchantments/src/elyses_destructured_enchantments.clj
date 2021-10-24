;; (ns elyses-destructured-enchantments)

(def cards [1 2 3 4 5 6 7 8 9 10])

(defn first-card
  "Returns the first card from deck."
  [[card & remaining]]
  card)

(defn second-card
  "Returns the second card from deck."
  [[_ card & remaining]]
   card)

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [[one two & deck]]
  (cons two (cons one deck)))

(defn discard-top-card
  "Returns a vector containing the first card and
   a vector of the remaining cards in the deck."
  [[card & deck]]
  [card deck])

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [[card & deck]]
  (reduce conj [card] (reduce conj face-cards deck)))

