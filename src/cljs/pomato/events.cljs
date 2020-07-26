(ns pomato.events
  (:require
   [re-frame.core :as rf]
   [pomato.db :as db]
   [pomato.effects :as effects]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/rfdb))

(rf/reg-event-fx
 :pomo-start
 (fn [_ _]
   {:interval {:action :start
               :id :pomo-timer
               :frequency 1000}}))

(rf/reg-event-fx
 :pomo-stop
 (fn [_ _]
   {:interval {:action :cancel
               :id :pomo-timer}}))

(rf/reg-event-db
 :dec-time
 (fn [db _]
   (update db :cur-time dec)))