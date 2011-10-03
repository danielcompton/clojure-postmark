(ns postmark.core
  (:require [clj-http.client :as client])
  (:use [clojure.string :only (join)])
  (:use [cheshire.core :only (generate-string parse-string)]))


;; # Internal Functions
(defn- maybe-assoc [m k v]
  (if v
    (assoc m k v)
    m))

(defn- parse-mail [mail]
  (generate-string
    (loop [m {}
           ks (keys mail)
           vs (vals mail)]
      (if (seq ks)
        (recur (maybe-assoc m (first ks) (first vs))
               (rest ks)
               (rest vs))
        m))))

(defn- send-to-postmark [api-key mail]
  (let [resp (client/post "http://api.postmarkapp.com/email"
                          {:basic-auth ["user" "pass"]
                           :body (parse-mail mail)
                           :headers {"X-Postmark-Server-Token" api-key}
                           :content-type :json
                           :accept :json})
        body (parse-string (:body resp))]
    (assoc resp :body body)))

(defn- get-to-string [to]
  (when to
    (if (= java.lang.String (class to))
      to
      (join "," to))))

(defn- mail
  "Send an email with the Postmark API.

  Remember: Postmark only lets you send to at most twenty addresses at once."
  [api-key from mail]
  {:pre [(or (= java.lang.String (class (:to mail)))
             (<= (count (:to mail)) 20))]}
  (send-to-postmark api-key {"From" from
                             "To" (get-to-string (:to mail))
                             "Subject" (:subject mail)
                             "Cc" (get-to-string (:cc mail))
                             "Bcc" (get-to-string (:bcc mail))
                             "Tag" (:tag mail)
                             "TextBody" (:text mail)
                             "HtmlBody" (:html mail)
                             "ReplyTo" (:reply-to mail)}))


;; # External API
(defn postmark [api-key from]
  (partial mail api-key from))

(defn postmark-test [from]
  (postmark "POSTMARK_API_TEST" from))

