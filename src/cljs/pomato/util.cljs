(ns pomato.util
  (:require [goog.string :as gstring]
            [goog.string.format]
            [keybind.core :as key]
            [re-frame.core :as rf]
            [pomato.events :as events]))

(defn convert-sec-to-string
  "Converts seconds into a string"
  [seconds]
  (let [mins (quot seconds 60)
        secs (rem seconds 60)]
    (str (gstring/format "%02d" mins) ":" (gstring/format "%02d" secs))))

(defn keybinds
  "Define global keybinds"
  []
  (key/bind! "ctrl-c" ::my-trigger #(js/console.log "Sequence fired properly"))
  (key/bind! "space" ::start-stop #(rf/dispatch [::events/space])))
