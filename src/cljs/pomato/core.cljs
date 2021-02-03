(ns pomato.core
  (:require
    [reagent.dom :as rdom]
    [re-frame.core :as re-frame]
    [pomato.events :as events]
    [pomato.views :as views]
    [pomato.config :as config]
    [stylefy.core :as stylefy]
    [re-pressed.core :as rp]))

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
  (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keypress"])
  (re-frame/dispatch [::rp/set-keypress-rules
                      {:event-keys [[[::events/space] [{:keyCode 32}]]]}])
  (dev-setup)
  (stylefy/init)
  (mount-root))
