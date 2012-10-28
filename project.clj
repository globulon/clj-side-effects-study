(defproject side-effect-study "1.0.0-SNAPSHOT"
	:description "side-effect-study"
	:plugins [[lein-ring "0.7.5"]]
	:dev-dependencies [[ring-server "0.2.4"]]
	:dependencies 	[[org.clojure/clojure "1.4.0"]
					 [org.clojure/algo.monads "0.1.3-SNAPSHOT"]
					 [org.clojure/java.jdbc "0.2.3"]
					 [ring "1.1.6"]
           [com.h2database/h2 "1.3.168"]])