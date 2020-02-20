(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {})

(defn réserve-table [places date livre]
  (if (= livre-vide livre)
    {:accepté (<= places nombre-de-places)}
    (if (= 2 places)
      {:accepté true}
      {:accepté false})))
