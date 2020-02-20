(ns maitre-d-hotel.core)

(def nombre-de-places 12)

(defn réserve-table [places date]
  (<= places nombre-de-places))
