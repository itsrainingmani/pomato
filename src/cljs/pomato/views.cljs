(ns pomato.views
  (:require
   [pomato.util :refer [convert-sec-to-string]]
   [re-frame.core :refer [dispatch subscribe]]
   [pomato.subs :as subs]
   [stylefy.core :as stylefy]))

(defn start-button []
  [:<>
   [:button {:style {:background-color "green"}
             :on-click (fn [] (dispatch [:pomo :start]))} "Start"]])

(defn stop-button []
  [:<>
   [:button {:style {:background-color "red"}
             :on-click (fn [] (dispatch [:pomo :stop]))} "Stop"]])

(defn reset-button []
  [:<>
   [:button {:style {:background-color "grey"}
             :on-click (fn [] (dispatch [:pomo :reset]))} "Reset"]])

(defn timer-input []
  (let [current-time (subscribe [::subs/curtime])]
    (fn []
      [:div
       [:p (convert-sec-to-string @current-time)]
       [start-button]
       [stop-button]
       [reset-button]])))

(defn main-panel []
  [:div
   [:h1 "Pomato Timer"]
   [timer-input]])
