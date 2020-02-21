(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-restantes nombre-de-places})
(def livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private sont-disponibles [places livre]
  (<= places (:places-restantes livre)))

(defn ^:private réserve [places {:keys [places-restantes] :as livre}]
  (assoc livre :places-restantes (- places-restantes places)))

(defn ^:private traite-demande [livre places]
(if (sont-disponibles places livre)
  (réserve places livre)
  livre))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places)]
     {:accepté (not= ancien nouveau)})))
