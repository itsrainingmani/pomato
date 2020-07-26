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
 :pomo
 (fn [cofx [_ pomo-type]]
   (case pomo-type
     :start (when-not (clojure.core/contains? (:db cofx) :is-timer?)
              {:interval {:action :start
                          :id :pomo-timer
                          :frequency 1000}
               :db (assoc (:db cofx) :is-timer? true)})
     :stop {:interval {:action :cancel
                       :id :pomo-timer}
            :dispatch [:no-timer]}
     :reset {:interval {:action :cancel
                        :id :pomo-timer}
             :dispatch-n [[:reset-time] [:no-timer]]})))

(rf/reg-event-db
 :no-timer
 (fn [db _]
   (clojure.core/dissoc db :is-timer?)))

(rf/reg-event-db
 :reset-time
 (fn [db _]
   (assoc db :cur-time 1500)))

(rf/reg-event-db
 :dec-time
 (fn [db _]
   (update db :cur-time dec)))