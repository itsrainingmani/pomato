(ns pomato.events
  (:require
   [re-frame.core :as rf]
   [pomato.db :as db]
   [pomato.effects :as effects]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [goog.events.KeyCodes :as keycodes]
   [goog.events :as gev])
  (:import [goog.event EventType KeyHandler]))

(defn capture-key
  "Given a keycode, execute a function"
  [keycode-map]
  (let [key-handler (KeyHandler. js/document)
        press-fn (fn [key-press]
                   (when-let [f (get keycode-map (.. key-press -keyCode))]
                     (f)))]
    (gev/listen key-handler
                (-> KeyHandler .-EventType .-KEY)
                press-fn)))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/rfdb))

(rf/reg-event-fx
 :pomo
 (fn [cofx [_ pomo-type]]
   (case pomo-type
     :start (when-not (or (zero? (get-in cofx [:db :cur-time] 0)) (clojure.core/contains? (:db cofx) :is-timer?))
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

(rf/reg-event-fx
 :type
 (fn [cofx [_ timer-type]]
   {:db (assoc (:db cofx) :timer-type timer-type)
    :dispatch [:pomo :reset]}))


(rf/reg-event-db
 :no-timer
 (fn [db _]
   (clojure.core/dissoc db :is-timer?)))

(rf/reg-event-db
 :reset-time
 (fn [db _]
   (let [timer-type (:timer-type db)
         type-dur   (get-in db [:timer-dur timer-type])]
     (assoc db :cur-time type-dur))))

(rf/reg-event-fx
 :dec-time
 (fn [cofx _]
   (let [curtime (get-in cofx [:db :cur-time] 0)]
     (if (zero? curtime)
       {:dispatch [:pomo :stop]}
       {:db (update (:db cofx) :cur-time dec)}))))
