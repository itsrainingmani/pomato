(ns pomato.views
  (:require
   [pomato.util :refer [convert-sec-to-string]]
   [re-frame.core :refer [dispatch subscribe]]
   [pomato.subs :as subs]
   [pomato.styles :as styles]
   [stylefy.core :refer [use-style]]))

(defn start-button []
  [:<>
   [:button {:style {:background-color "green"}
             :on-click (fn [] (dispatch [:pomo :start]))} "Start"]])

(defn stop-button []
  [:<>
   [:button (use-style styles/stop-btn {:on-click (fn [] (dispatch [:pomo :stop]))}) "Stop"]])

(defn reset-button []
  [:<>
   [:button {:style {:background-color "grey"}
             :on-click (fn [] (dispatch [:pomo :reset]))} "Reset"]])

(defn timer-input []
  (let [current-time (subscribe [::subs/curtime])]
    (fn []
      [:div
       [:p (use-style styles/time-style) (convert-sec-to-string @current-time)]
       [start-button]
       [stop-button]
       [reset-button]])))

(defn main-panel []
  [:div (use-style styles/app-style)
   [:h1 "Pomato Timer"]
   [timer-input]])
