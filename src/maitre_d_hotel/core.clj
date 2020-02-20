(ns maitre-d-hotel.core)

(def nombre-de-places 12)

(defn réserve-table
  ([places date]
   (réserve-table nil places date))
  ([livre places date]
   (if (nil? livre)
     {:accepté (<= places nombre-de-places)}
     {:accepté false})))
