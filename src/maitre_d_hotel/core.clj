(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre (atom {:places-restantes nombre-de-places}))

(defn efface-livre []
  (swap! livre assoc :places-restantes nombre-de-places))

(defn réserve-table
  ([places date]
   (let [différence (- (:places-restantes @livre) places)]
     (if (>= différence 0)
       (do (swap! livre assoc :places-restantes différence)
           {:accepté true})
       {:accepté false}))))
