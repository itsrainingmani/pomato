(ns pomato.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::curtime
 (fn [db]
   (:cur-time db)))

(rf/reg-sub
 ::is-timer?
 (fn [db]
   (get db :is-timer? false)))
