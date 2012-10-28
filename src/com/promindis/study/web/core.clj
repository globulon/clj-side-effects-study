(ns com.promindis.study.web.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [hiccup.core :as hiccup]
            [hiccup.form :as form]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :as reload]
            [ring.middleware.session :as session]
            [ring.middleware.params :as params]
            [ring.util.response :as response]
            [com.promindis.study.commons.persistence :as db]))

(defn registration-page [req]
  (hiccup/html
    [:html
      [:body
        (form/form-to
          [:post "/register"]
          [:fieldset
            [:legend "Fill your personal information:"]
            [:div 
              (form/label :first-name "First name:")
              [:div.controls
                (form/text-field :first-name "")]]
            [:div 
              (form/label :last-name "Last name:")
              [:div.controls
                (form/text-field :last-name "")]]
            [:div 
              (form/label :login "Last name:")
              [:div.controls
                (form/text-field :login "")]]
                (form/submit-button "Submit")])]]))

(defn registration-successful [req]
  (let [first-name (get-in req [:query-params "first-name"])]
    (hiccup/html 
      [:div 
        (str "Registration done " first-name)])))

(defn handle-registration [req]
  (let [
  	first-name (get-in req [:params "first-name"])
  	last-name (get-in req [:params "last-name"])
  	login (get-in req [:login "user-name"])]
        (response/redirect (str "/done?first-name=" first-name))))

(defn app-routes [req]
  (routing req
           (GET "/" [] "Welcome to the main page")
           (GET "/register" [] registration-page)
           (POST "/register" [] handle-registration)
           (GET "/done" [] registration-successful)
           (route/not-found "Page not found")))

;;Gijs was using (reload/wrap-reload [his_namespace]) => why ?
(def main-app
     (-> app-routes
         params/wrap-params
         (reload/wrap-reload ['com.promindis.study.web.core])))

(defn -main
  [& args]
  (jetty/run-jetty #'main-app {:port 8888 :join? false}))