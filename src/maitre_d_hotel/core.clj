(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-restantes nombre-de-places})
(def livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private traite-demande [{places-restantes :places-restantes :as livre} places]
  (assoc livre :places-restantes (if (<= places places-restantes)
                                   (- places-restantes places)
                                   places-restantes)))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places)]
     {:accepté (not= ancien nouveau)})))
