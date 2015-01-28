(defproject postmark "1.2.0-SNAPSHOT"
  :description "Clojure bindings for http://postmarkapp.com/"
  :exclusions  [org.clojure/clojure]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cheshire "5.4.0"]
                 [clj-http "0.7.8"]])
  :plugins  [[lein-ancient "0.6.1"]
             [lein-kibit "0.0.8"]
             [jonase/eastwood "0.2.1"]]
  :aliases {"lint" ["do" ["ancient"] ["kibit"] ["eastwood"]]})
>>>>>>> 68dba29... Troubleshoot clj-http by downgrading library.
