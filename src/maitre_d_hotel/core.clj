(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-restantes nombre-de-places})
(def livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private sont-disponibles [places date livre]
  (or (contains? #{"2020-02-21" "2020-02-22"} date)
      (<= places (:places-restantes livre))))

(defn ^:private réserve [places {:keys [places-restantes] :as livre}]
  (assoc livre :places-restantes (- places-restantes places)))

(defn ^:private traite-demande [livre places date]
  (if (sont-disponibles places date livre)
    (réserve places livre)
    livre))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places date)]
       {:accepté (not= ancien nouveau)})))
