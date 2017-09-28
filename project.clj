(defproject postmark "1.4.1"
  :description "Clojure bindings for http://postmarkapp.com/"
  :url "https://github.com/sjl/clojure-postmark"
  :scm {:name "git"
        :url "https://github.com/sjl/clojure-postmark"}
  :license {:name "MIT"
            :url "https://github.com/sjl/clojure-postmark/blob/master/LICENSE.markdown"}
  :exclusions  [org.clojure/clojure]
  :deploy-repositories [["releases" :clojars]
                        ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.8.0"]
                 [clj-http "3.7.0"]]
  :plugins  [[lein-ancient "0.6.10"]
             [lein-kibit "0.1.5"]
             [jonase/eastwood "0.2.4"]]
  :aliases {"lint" ["do" ["ancient"] ["kibit"] ["eastwood"]]})
