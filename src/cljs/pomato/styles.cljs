(ns pomato.styles
  (:require [stylefy.core :as stylefy]))

;; (stylefy/keyframes "flowy"
;;                    [:0% {:background-position "0% 50%"}]
;;                    [:50% {:background-position "100% 50%"}]
;;                    [:100% {:background-position "0% 50%"}])

;; (stylefy/tag "body" {:background "linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab)"
;;                      :background-size "400% 400%"
;;                      :animation "flowy 15s ease infinite"
;;                      ::stylefy/vendors ["webkit" "moz" "o"]
;;                      ::stylefy/auto-prefix #{:animation}})
;;                      

(def stop-btn {:box-shadow "3px 4px 0px 0px #8a2a21"
               :background "linear-gradient(to bottom, #c62d1f 5%, #f24437 100%)"
               :background-color "#c62d1f"
               :border-radius "18px"
               :border "1px solid #d02718"
               :display "inline-block"
               :cursor "pointer"
               :color "#ffffff"
               :font-family "Arial"
               :font-size "17px"
               :padding "7px 25px"
               :text-decoration "none"
               :text-shadow "0px 1px 0px #810e05"})

(def app-style {:display "flex"
                :flex-direction "column"
                :align-items "center"})

(def time-style {:text-align "center"
                 :font-size 30
                 :font-family "rubik"})

