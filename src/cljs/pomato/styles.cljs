(ns pomato.styles
  (:require [stylefy.core :as stylefy]))

(stylefy/keyframes "rainbow"
                   [:0% {:background-position-x 0}]
                   [:to {:background-position-x "10240px"}])

;; (stylefy/tag "body" {:background "linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab)"
;;                      :background-size "400% 400%"
;;                      :animation "flowy 15s ease infinite"
;;                      ::stylefy/vendors ["webkit" "moz" "o"]
;;                      ::stylefy/auto-prefix #{:animation}})

(stylefy/tag "body" {:margin "0 0 0 0"})


(def general-btn-style {:border-radius "6px"
                        :font-weight 600
                        :line-height 1
                        :color "rgb(34, 34, 34)"
                        :border "2px solid #222"
                        :box-shadow "5px 5px 0 #222"
                        :margin-right "8px"
                        :padding "0.09em 0.5em 0.09em"
                        :font-size "2em"
                        ::stylefy/mode [[:hover {:box-shadow "2px 2px 0 #222"}]
                                        [:active {:background-color "#d9e5f1"}]]})

(def start-btn-style (merge general-btn-style {:background-color "rgb(131, 255, 205)"}))

(def stop-btn-style (merge general-btn-style {:background-color "#DC352C"}))

(def reset-btn-style (merge general-btn-style {:background-color "grey"}))

(def app-style {:display "flex"
                :flex-direction "column"
                :align-items "center"
                :font-family "sans-serif"})

(def time-style {:text-align "center"
                 :font-size "5em"
                 :background-clip "text"})

(def rainbow-color {:color "transparent"
                    :background-clip "text"
                    :background-image "linear-gradient(270deg,#1e5799,#2ce0bf,#76dd2c,#dba62b,#ff1493,#1e5799)"
                    :animation "rainbow 64s linear infinite"
                    ::stylefy/vendors ["webkit" "moz" "o"]
                    ::stylefy/auto-prefix #{:animation :background-clip}})

(def heading-style {
                    ;; :font-style "italic"
                    ;; :text-transform "uppercase"
                    :font-size "4em"
                    :font-weight 800
                    :margin-bottom 0
                    :font-family "Work Sans"
                    ;; :text-shadow "rgb(26, 254, 73) 2px 4px 0px"
                    ;; :animation "rainbow 4s ease 2s infinite reverse"
                    })
