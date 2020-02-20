(ns maitre-d-hotel.core)

(def nombre-de-places 12)
(def livre-vide {:places-restantes nombre-de-places})

(defn réserve-table [places date {places-restantes :places-restantes}]
  {:accepté (<= places places-restantes)
   :livre {:places-restantes (- places-restantes places)}})
