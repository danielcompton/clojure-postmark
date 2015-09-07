(ns postmark.core
  (:require [clj-http.client :as client])
  (:use [clojure.string :only (join)])
  (:use [cheshire.core :only (generate-string parse-string)]))


(defn- mail-to-json
  "Return the JSON for the given mail map.

  Falsey values will be removed from the map before serializing."
  [mail]
  (generate-string (into {} (filter second mail))))

(defn- send-to-postmark [api-key mail]
  (let [resp (client/post "http://api.postmarkapp.com/email"
                          {:body (mail-to-json mail)
                           :headers {"X-Postmark-Server-Token" api-key}
                           :content-type :json
                           :accept :json})
        body (parse-string (:body resp))]
    (assoc resp :body body)))

(defn- get-to-string
  "Create a string appropriate for the to/cc/bcc fields in a Postmark call.

  Can be passed a email address as a string like 'foo@bar.com' or
  'Foo Bar <foo@bar.com>', or a seq of such strings."
  [to]
  (when to
    (if (= java.lang.String (class to))
      to
      (join "," to))))

(defn- no-more-than-20-recipients [to]
  (or (= java.lang.String (class to))
      (<= (count to) 20)))

(defn- format-attachments
  "Format attachments given a map such as:
  {:name \"my-file.csv\"
   :content \"dGVzdCBjb250ZW50\"
   :content-type \"text/csv\"}"
  [coll]
  (map (fn [a] {"Name" (:name a)
                "Content" (:content a)
                "ContentType" (:content-type a)}) coll))
 

(defn- mail
  "Send an email with the Postmark API.

  Remember: Postmark only lets you send to at most twenty addresses at once."
  [api-key from {:keys [to subject cc bcc tag text html reply-to attachments]}]
  {:pre [(no-more-than-20-recipients to)]}
  (send-to-postmark api-key {"From" from
                             "To" (get-to-string to)
                             "Subject" subject
                             "Cc" (get-to-string cc)
                             "Bcc" (get-to-string bcc)
                             "Tag" tag
                             "TextBody" text
                             "HtmlBody" html
                             "ReplyTo" reply-to
                             "Attachments" (format-attachments attachments)}))


(defn postmark [api-key from]
  (partial mail api-key from))

(defn postmark-test [from]
  (postmark "POSTMARK_API_TEST" from))
