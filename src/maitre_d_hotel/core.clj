(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre (atom {:places-restantes nombre-de-places}))

(defn efface-livre []
  (swap! livre assoc :places-restantes nombre-de-places))

(defn réserve-table
  ([places date]
   (let [places-restantes (:places-restantes @livre)]
     (if (<= places places-restantes)
       (do (swap! livre assoc :places-restantes (- places-restantes places))
           {:accepté true})
       {:accepté false}))))
