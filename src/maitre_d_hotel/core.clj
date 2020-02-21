(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre (atom {:places-restantes nombre-de-places}))

(defn efface-livre []
  (swap! livre assoc :places-restantes nombre-de-places))

(defn traite-demande [livre places]
  (let [différence (- (:places-restantes livre) places)]
    (when (>= différence 0)
      (assoc livre :places-restantes différence))))

(defn réserve-table
  ([places date]
   (if (>= (- (:places-restantes @livre) places) 0)
     (do (swap! livre traite-demande places)
         {:accepté true})
     {:accepté false})))
