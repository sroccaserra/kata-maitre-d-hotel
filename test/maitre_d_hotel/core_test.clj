(ns maitre-d-hotel.core-test
  (:require [clojure.test :refer :all]
            [maitre-d-hotel.core :refer :all]))
;;;
;; Test list

;; Note douze = taille max

;; ok: Une table pour une personne demain (idem)
;; ok: Une table pour douze personnes aujourd'hui (idem)
;; KO: Une table pour treize personnes aujourd'hui (idem)
;; ok: Une table pour une personne aujourd'hui (table de un déjà réservée à la même date)
;; ok: Une table pour une personne aujourd'hui (table de un déjà réservée à une autre date)
;; KO: Une table pour une personne aujourd'hui (table de douze déjà réservée à la même date)
;; ok: Une table pour une personne aujourd'hui (table de douze déjà réservée à une autre date)

(deftest scénarios-qui-fonctionnent
  (testing "Une table pour 1 aujourd'hui"
    (is (réserve-table 1 "2020-02-20")))

  (testing "Une table pour 12 aujourd'hui"
    (is (réserve-table 12 "2020-02-20"))))

(deftest scénarios-qui-coincent
  (testing "Une table pour 13 aujourd'hui"
    (is (not (réserve-table (inc nombre-de-places) "2020-02-20")))))
