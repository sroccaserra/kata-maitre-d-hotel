(ns maitre-d-hotel.core)

(def nombre-de-places-max 12)
(def livre-vide {})

(def ^:private livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private places-réservées [date livre]
  (or (livre date) 0))

(defn ^:private sont-disponibles [places date livre]
  (<= (+ places (places-réservées date livre)) nombre-de-places-max))

(defn ^:private réserve [places date livre]
  (assoc livre date (+ places (places-réservées date livre))))

(defn ^:private traite-demande [livre places date]
  (if (sont-disponibles places date livre)
    (réserve places date livre)
    livre))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places date)]
       {:accepté (not= ancien nouveau)})))
