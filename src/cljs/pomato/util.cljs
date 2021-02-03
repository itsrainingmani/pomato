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

;; (defn capture-key
;;   "Given a `keycode`, execute function `f` "
;;   [keycode-map]
;;   (let [key-handler (KeyHandler. js/document)
;;         press-fn (fn [key-press]
;;                    (when-let [f (get keycode-map (.. key-press -keyCode))]
;;                      (f)))]
;;     (gev/listen key-handler
;;                 (-> KeyHandler .-EventType .-KEY)
;;                 press-fn)))

;; (defn app-keybindings []
;;   ;; sets up the event listener
;;   (capture-key {keycodes/l #(js/alert "Luna Lovegood")
;;                 keycodes/d #(js/alert "Dumbledore")
;;                 keycodes/h #(js/alert "Hermione")
;;                 keycodes/y #(js/alert "harrY")
;;                 keycodes/r #(js/alert "Ron")}))
