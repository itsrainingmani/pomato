(ns pomato.views
  (:require
   [pomato.util :refer [convert-sec-to-string]]
   [re-frame.core :refer [dispatch subscribe]]
   [pomato.subs :as subs]
   [pomato.styles :as styles]
   [stylefy.core :refer [use-style]]))

(defn start-button []
  [:<>
   [:button (use-style styles/start-btn-style
                       {:on-click (fn [] (dispatch [:pomo-start]))}) "Start"]])

(defn stop-button []
  [:<>
   [:button (use-style styles/stop-btn-style
                       {:on-click (fn [] (dispatch [:pomo-stop]))}) "Stop"]])

(defn reset-button []
  [:<>
   [:button (use-style styles/reset-btn-style
                       {:on-click (fn [] (dispatch [:pomo-reset]))}) "Reset"]])

(defn classic-btn [tt]
  [:<>
   [:button (use-style (merge styles/pomo-btn-style (when tt {:background-color "purple"}))
                       {:on-click (fn [] (dispatch [:type :classic]))}) "Classic"]])

(defn long-btn [tt]
  [:<>
   [:button (use-style (merge styles/pomo-btn-style (when tt {:background-color "purple"}))
                       {:on-click (fn [] (dispatch [:type :long]))}) "Long"]])

(defn short-btn [tt]
  [:<>
   [:button (use-style (merge styles/pomo-btn-style (when tt {:background-color "purple"}))
                       {:on-click (fn [] (dispatch [:type :short]))}) "Short"]])

(defn timer-input []
  (let [current-time (subscribe [::subs/curtime])
        is-timer? (subscribe [::subs/is-timer?])]
    (fn []
      [:div
       [:p (use-style (merge styles/time-style
                             (if (true? @is-timer?)
                               (cond
                                 (< @current-time 30) styles/flash-color
                                 :else styles/rainbow-color)
                               (when (< @current-time 30) {:color "#DC352C"}))))
        (convert-sec-to-string @current-time)]
       [start-button]
       [stop-button]
       [reset-button]])))

(defn pomodoro-types []
  (fn []
    (let [timer-type @(subscribe [::subs/timer-type])]
      [:div
       (classic-btn (= :classic timer-type))
       (long-btn (= :long timer-type))
       (short-btn (= :short timer-type))])))

(defn footer-comp []
  [:footer {:style {:position "absolute" :bottom "2px" :width "100%" :text-align "center" :font-family "Work Sans"}}
   "© 2020. Built by "
   [:a {:title "My GitHub Profile" :href "https://github.com/itsrainingmani" :style {:text-decoration "none" :color "#3182ce" :font-style "italic"}} "@itsrainingmani"]
   " using "
   [:a {:title "Re-frame" :href "https://github.com/day8/re-frame" :style {:text-decoration "none" :color "#e53e3e" :font-style "italic"}} "re-frame"]])

(defn main-panel []
  [:div (use-style styles/app-style)
   [:h1 (use-style styles/heading-style) "Pomato Timer"]
   [pomodoro-types]
   [timer-input]
   [:svg {:viewBox "0 0 1100 320" :style {:position "absolute" :z-index "-1"}}
    [:path
     {:fill         "rgb(30, 199, 252)"
      :fill-opacity "1"
      :d            "M0,64L21.8,101.3C43.6,139,87,213,131,218.7C174.5,224,218,160,262,160C305.5,160,349,224,393,245.3C436.4,267,480,245,524,250.7C567.3,256,611,288,655,288C698.2,288,742,256,785,218.7C829.1,181,873,139,916,149.3C960,160,1004,224,1047,229.3C1090.9,235,1135,181,1178,149.3C1221.8,117,1265,107,1309,133.3C1352.7,160,1396,224,1418,256L1440,288L1440,0L1418.2,0C1396.4,0,1353,0,1309,0C1265.5,0,1222,0,1178,0C1134.5,0,1091,0,1047,0C1003.6,0,960,0,916,0C872.7,0,829,0,785,0C741.8,0,698,0,655,0C610.9,0,567,0,524,0C480,0,436,0,393,0C349.1,0,305,0,262,0C218.2,0,175,0,131,0C87.3,0,44,0,22,0L0,0Z"}]]
   [footer-comp]])
