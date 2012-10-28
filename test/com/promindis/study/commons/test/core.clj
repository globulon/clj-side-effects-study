(ns com.promindis.study.commons.test.core
  (:require [com.promindis.study.commons.core :refer :all])
  (:require [clojure.test :refer :all]))

(defn fail [] (throw (Exception. "error")))

(deftest safely-behavior
  (testing "safely should"
    (testing "return right result with safe function"
      (is (= {:right 5} ((safely +) 2 3))))
    (testing "return left result with failing function"
      (is (=  {:left "error"} ((safely fail)))))))

(deftest accessors-behavior
  (testing "accessors should"
    (testing "return true when matching expected behavior"
      (is (right? ((safely +) 2 3)))
      (is (left? ((safely fail)))))
    (testing "return false when not matching expected behavior"
      (is (not (left? ((safely +) 2 3))))
      (is (not (right? ((safely fail))))))))