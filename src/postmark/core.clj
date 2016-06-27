(ns postmark.core
  (:require [clj-http.client :as client]
            [clojure.string :refer [join]]
            [cheshire.core :refer [generate-string parse-string]]))


(defn- mail-to-json
  "Return the JSON for the given mail map.

  Falsey values will be removed from the map before serializing."
  [mail]
  (->> mail
       (filter (comp some? val))                            ;; Filter out keys with nil values
       (into {})
       (generate-string)))

(defn- send-to-postmark [path api-key mail]
  (client/post (str "https://api.postmarkapp.com/" path)
               {:body         (mail-to-json mail)
                :headers      {"X-Postmark-Server-Token" api-key}
                :content-type :json
                :accept       :json
                :coerce       :always
                :as           :json}))

(defn- to-string
  "Create a string appropriate for the to/cc/bcc fields in a Postmark call.

  Can be passed a email address as a string like 'foo@bar.com' or
  'Foo Bar <foo@bar.com>', or a seq of such strings."
  [to]
  (when to
    (if (string? to)
      to
      (join "," to))))

(defn- no-more-than-50-recipients [to]
  (or (string? to)
      (<= (count to) 50)))

(defn- mail
  "Send an email with the Postmark API.

  Remember: Postmark only lets you send to at most fifty addresses at once."
  [api-key default-from {:keys [to subject cc bcc from tag text html reply-to headers]}]
  {:pre [(no-more-than-50-recipients to)]}
  (send-to-postmark "email/" api-key
                    {"From"     (or from default-from)
                     "To"       (to-string to)
                     "Subject"  subject
                     "Cc"       (to-string cc)
                     "Bcc"      (to-string bcc)
                     "Tag"      tag
                     "TextBody" text
                     "HtmlBody" html
                     "ReplyTo"  reply-to
                     "Headers"  headers}))


(defn postmark [api-key from]
  (partial mail api-key from))

(def postmark-test-api-key "POSTMARK_API_TEST")

(defn postmark-test [from]
  (postmark postmark-test-api-key from))

(defn mail-with-template
  [api-key from {:keys [to cc bcc tag reply-to template-id template-model]}]
  (send-to-postmark "email/withTemplate/" api-key
                    {"From"          from
                     "To"            (to-string to)
                     "Cc"            (to-string cc)
                     "Bcc"           (to-string bcc)
                     "Tag"           tag
                     "ReplyTo"       reply-to
                     "TemplateId"    template-id
                     "TemplateModel" template-model}))

