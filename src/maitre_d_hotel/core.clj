(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-restantes nombre-de-places})
(def livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private traite-demande [livre places]
  (let [différence (- (:places-restantes livre) places)]
    (if (>= différence 0)
      (assoc livre :places-restantes différence)
      livre)))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places)]
     {:accepté (not= ancien nouveau)})))
