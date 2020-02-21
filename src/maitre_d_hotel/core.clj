(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-réservées {}})
(def livre (atom livre-vide))

(defn efface-livre []
  (reset! livre livre-vide))

(defn ^:private sont-disponibles [places date livre]
  (<= places (- nombre-de-places (or ((livre :places-réservées) date)
                                     0))))

(defn ^:private réserve [places date {{places-réservées date} :places-réservées :as livre}]
  (assoc-in livre [:places-réservées date] (+ (or places-réservées 0) places)))

(defn ^:private traite-demande [livre places date]
  (if (sont-disponibles places date livre)
    (réserve places date livre)
    livre))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! livre traite-demande places date)]
       {:accepté (not= ancien nouveau)})))
