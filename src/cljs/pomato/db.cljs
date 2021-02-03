(ns pomato.db)

(defonce rfdb
  {:cur-time     1500
   :default-time 1500
   :timer-type   :classic
   :timer-dur    {:classic 1500
                  :long    600
                  :short   300}})
