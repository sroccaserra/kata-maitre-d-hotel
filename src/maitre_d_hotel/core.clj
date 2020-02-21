(ns maitre-d-hotel.core)

(def nombre-de-places-max 12)

(def ^:private registre-vide {})
(def ^:private registre (atom registre-vide))

(defn efface-registre []
  (reset! registre registre-vide))

(defn ^:private places-réservées [date registre]
  (get registre date 0))

(defn ^:private sont-disponibles [places date registre]
  (<= (+ places (places-réservées date registre))
      nombre-de-places-max))

(defn ^:private réserve [places date registre]
  (assoc registre date (+ places
                          (places-réservées date registre))))

(defn ^:private traite-demande [registre places date]
  (if (sont-disponibles places date registre)
    (réserve places date registre)
    registre))

(defn réserve-table
  ([places date]
   (let [[ancien nouveau] (swap-vals! registre traite-demande places date)]
     {:accepté (not= ancien nouveau)})))
