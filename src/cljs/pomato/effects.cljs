(ns pomato.effects
  (:require [re-frame.core :refer [reg-fx dispatch]]))

(reg-fx                                                     ;; the re-frame API for registering effect handlers
  :interval                                                 ;; the effect id
  (let [live-intervals (atom {})]                           ;; storage for live intervals
    (fn [{:keys [action id frequency]}]                     ;; the handler
      (if (= action :start)
        (swap! live-intervals assoc id (js/setInterval #(dispatch [:dec-time]) frequency))
        (do (js/clearInterval (get @live-intervals id))
            (swap! live-intervals dissoc id))))))

(reg-fx
  ::log
  (fn [message]
    (js/console.log message)))