(ns com.promindis.study.commons.persistence
  (:import [java.sql Connection Driver DriverManager]))

(defn loading-driver [name]
  (fn [f]
    (Class/forName name)
    f))

(defn make-connection [url properties]
  (DriverManager/getConnection url properties))

(defn close-connection [connection status]
  (.close connection))

;"jdbc:h2:mem:test"
(defn make-connection-provider [driver-class]
  (fn [url properties]
    ((loading-driver driver-class)
      (fn []
        (make-connection url properties)))))
