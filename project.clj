(defproject org.clojars.danielcompton/postmark "1.3.0"
  :description "Clojure bindings for http://postmarkapp.com/"
  :url "https://github.com/sjl/clojure-postmark"
  :scm {:name "git"
        :url "https://github.com/sjl/clojure-postmark"}
  :license {:name "MIT"
            :url "https://github.com/sjl/clojure-postmark/blob/master/LICENSE.markdown"}
  :exclusions  [org.clojure/clojure]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cheshire "5.4.0"]
                 [clj-http "2.1.0"]]
  :plugins  [[lein-ancient "0.6.1"]
             [lein-kibit "0.0.8"]
             [jonase/eastwood "0.2.1"]]
  :aliases {"lint" ["do" ["ancient"] ["kibit"] ["eastwood"]]})
