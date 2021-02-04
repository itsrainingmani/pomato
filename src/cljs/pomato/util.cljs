(ns pomato.util
  (:require [goog.string :as gstring]
            [goog.string.format]
            [goog.events.KeyCodes :as keycodes]
            [goog.events :as gev])
  (:import [goog.events EventType KeyHandler]))

(defn convert-sec-to-string
  "Converts seconds into a string"
  [seconds]
  (let [mins (quot seconds 60)
        secs (rem seconds 60)]
    (str (gstring/format "%02d" mins) ":" (gstring/format "%02d" secs))))

