(defproject postmark "1.2.0-SNAPSHOT"
  :description "Clojure bindings for http://postmarkapp.com/"
  :url "https://github.com/sjl/clojure-postmark"
  :scm {:name "git"
        :url "https://github.com/sjl/clojure-postmark"}
  :license {:name "MIT"
            :url "https://github.com/sjl/clojure-postmark/blob/master/LICENSE.markdown"}
  :exclusions  [org.clojure/clojure]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]
                 [clj-http "2.1.0"]]
  :plugins  [[lein-ancient "0.6.8"]
             [lein-kibit "0.1.2"]
             [jonase/eastwood "0.2.3"]]
  :aliases {"lint" ["do" ["ancient"] ["kibit"] ["eastwood"]]})
