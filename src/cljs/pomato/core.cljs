(ns pomato.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [pomato.events :as events]
   [pomato.views :as views]
   [pomato.util :as util]
   [pomato.config :as config]
   [stylefy.core :as stylefy]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (util/keybinds)
  (dev-setup)
  (stylefy/init)
  (mount-root))
