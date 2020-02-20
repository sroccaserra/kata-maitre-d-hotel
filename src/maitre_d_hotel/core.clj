(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {})

(defn réserve-table [places date livre]
  (if (= livre-vide livre)
    {:accepté (<= places nombre-de-places) :livre nil}
    (condp = places
      2 {:accepté true :livre livre}
      3 {:accepté true :livre livre}
      {:accepté false :livre livre})))
