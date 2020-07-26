(ns pomato.util
  (:require [goog.string :as gstring]
            [goog.string.format]))

(defn convert-sec-to-string
  "Converts seconds into a string"
  [seconds]
  (let [mins (quot seconds 60)
        secs (rem seconds 60)]
    (str mins ":" (gstring/format "%02d"  secs))))