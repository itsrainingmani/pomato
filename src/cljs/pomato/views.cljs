(ns pomato.views
  (:require
   [pomato.util :refer [convert-sec-to-string]]
   [re-frame.core :refer [dispatch subscribe]]
   [pomato.subs :as subs]
   [pomato.styles :as styles]
   [stylefy.core :refer [use-style]]))

(defn start-button []
  [:<>
   [:button  (use-style styles/start-btn-style
                        {:on-click (fn [] (dispatch [:pomo :start]))}) "Start"]])

(defn stop-button []
  [:<>
   [:button (use-style styles/stop-btn-style
                       {:on-click (fn [] (dispatch [:pomo :stop]))}) "Stop"]]) 

(defn reset-button []
  [:<>
   [:button (use-style styles/reset-btn-style
                       {:on-click (fn [] (dispatch [:pomo :reset]))}) "Reset"]])

(defn timer-input []
  (let [current-time (subscribe [::subs/curtime])
        is-timer?    (subscribe [::subs/is-timer?])]
    (fn []
      [:div
       [:p (use-style (merge styles/time-style
                      (when (= @is-timer? true) styles/rainbow-color)))
          (convert-sec-to-string @current-time)]
       [start-button]
       [stop-button]
       [reset-button]])))


(defn main-panel []
  [:div (use-style styles/app-style)
   [:h1 (use-style styles/heading-style) "Pomato Timer"]
   [timer-input]
   [:svg {:viewBox "0 0 1100 320" :style {:position "absolute" :z-index "-1"}}
    [:path
     {:fill "rgb(135, 224, 255)"
      :fill-opacity "1"
      :d "M0,128L26.7,112C53.3,96,107,64,160,74.7C213.3,85,267,139,320,149.3C373.3,160,427,128,480,138.7C533.3,149,587,203,640,224C693.3,245,747,235,800,197.3C853.3,160,907,96,960,69.3C1013.3,43,1067,53,1120,58.7C1173.3,64,1227,64,1280,58.7C1333.3,53,1387,43,1413,37.3L1440,32L1440,0L1413.3,0C1386.7,0,1333,0,1280,0C1226.7,0,1173,0,1120,0C1066.7,0,1013,0,960,0C906.7,0,853,0,800,0C746.7,0,693,0,640,0C586.7,0,533,0,480,0C426.7,0,373,0,320,0C266.7,0,213,0,160,0C106.7,0,53,0,27,0L0,0Z"}
     ]]])
