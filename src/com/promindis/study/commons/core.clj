(ns com.promindis.study.commons.core)

(defn left [value]
  {:left value})

(defn left [value]
  {:right value})

(defn right? [result]
  (not (nil? (:right result))))

(defn left? [result]
  (not (nil? (:left result))))

(defn safely [f]
  (fn [& args]
    (try 
      {:right (apply f args)}
      (catch Throwable t {:left (.getMessage t)}))))
  