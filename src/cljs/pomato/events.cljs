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

;; (rf/reg-event-fx
;;  :pomo
;;  (fn [cofx [_ pomo-type]]
;;    (case pomo-type
;;      :start (when-not (or (zero? (get-in cofx [:db :cur-time] 0)) (clojure.core/contains? (:db cofx) :is-timer?))
;;               {:interval {:action :start
;;                           :id :pomo-timer
;;                           :frequency 1000}
;;                :db (assoc (:db cofx) :is-timer? true)})
;;      :stop {:interval {:action :cancel
;;                        :id :pomo-timer}
;;             :dispatch [:no-timer]}
;;      :reset {:interval {:action :cancel
;;                         :id :pomo-timer}
;;              :dispatch-n [[:reset-time] [:no-timer]]})))

(rf/reg-event-fx
 :pomo-start
 (fn [cofx _]
   (when-not (or (zero? (get-in cofx [:db :cur-time] 0)) (clojure.core/contains? (:db cofx) :is-timer?))
     {:interval {:action :start
                 :id :pomo-timer
                 :frequency 1000}
      :db (assoc (:db cofx) :is-timer? true)})))

(rf/reg-event-fx
 :pomo-stop
 (fn [cofx _]
   (when (clojure.core/contains? (:db cofx) :is-timer?)
     {:interval {:action :cancel
                 :id :pomo-timer}
      :dispatch [:no-timer]})))

(rf/reg-event-fx
 :pomo-reset
 (fn [_ _]
   {:interval {:action :cancel
               :id :pomo-timer}
    :dispatch-n [[:no-timer] [:reset-time]]}))

(rf/reg-event-fx
  :type
  (fn [cofx [_ timer-type]]
    {:db       (assoc (:db cofx) :timer-type timer-type)
     :dispatch [:pomo-reset]}))

(rf/reg-event-fx
  ::space
  (fn [cofx _]
    (prn cofx)
    (let [is-timer (get-in cofx [:db :is-timer?] false)]
      (if is-timer
        {:dispatch [:pomo-stop]}
        {:dispatch [:pomo-start]}))))

(rf/reg-event-db
  :no-timer
  (fn [db _]
    (clojure.core/dissoc db :is-timer?)))

(rf/reg-event-db
  :reset-time
  (fn [db _]
    (let [timer-type (:timer-type db)
          type-dur (get-in db [:timer-dur timer-type])]
      (assoc db :cur-time type-dur))))

(rf/reg-event-fx
  :dec-time
  (fn [cofx _]
    (let [curtime (get-in cofx [:db :cur-time] 0)]
      (if (zero? curtime)
        {:dispatch [:pomo :stop]}
        {:db (update (:db cofx) :cur-time dec)}))))
