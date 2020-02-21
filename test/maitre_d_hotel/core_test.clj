(ns maitre-d-hotel.core-test
  (:require [clojure.test :refer :all]
            [maitre-d-hotel.core :refer :all]))
;;;
;; Test list

;; Note : douze = taille max

;; ok: Une table pour une personne demain (idem)
;; ok: Une table pour une personne aujourd'hui (table de un déjà réservée à la même date)
;; ok: Une table pour une personne aujourd'hui (table de un déjà réservée à une autre date)

(def aujourd'hui "2020-02-20")
(def demain "2020-02-21")
(def après-demain "2020-02-22")

(defn setup [f]
  (efface-livre)
  (f))

(use-fixtures :each setup)

(deftest une-table-pour-1-aujourd'hui
    (is (true? (:accepté (réserve-table 1 aujourd'hui)))))

(deftest Une-table-pour-12-aujourd'hui
    (is (true? (:accepté (réserve-table 12 aujourd'hui)))))

(deftest une-table-pour-13-aujourd'hui
  (is (false? (:accepté (réserve-table (inc nombre-de-places) aujourd'hui)))))

(deftest une-table-pour-2-puis-2-aujourd'hui
  (réserve-table 2 aujourd'hui)
  (is (true? (:accepté (réserve-table 2 aujourd'hui)))))

(deftest une-table-pour-3-puis-3-aujourd'hui
  (réserve-table 3 aujourd'hui)
  (is (true? (:accepté (réserve-table 3 aujourd'hui)))))

(deftest une-table-pour-12-puis-une-pour-1-aujourd'hui
  (réserve-table nombre-de-places aujourd'hui)
  (is (false? (:accepté (réserve-table 1 aujourd'hui)))))

(deftest une-table-pour-12-aujourd'hui-et-une-table-de-un-demain
  (réserve-table nombre-de-places aujourd'hui)
  (is (true? (:accepté (réserve-table 1 demain)))))

(deftest une-table-pour-12-aujourd'hui-et-une-table-de-un-après-demain
  (réserve-table nombre-de-places aujourd'hui)
  (is (true? (:accepté (réserve-table 1 après-demain)))))
