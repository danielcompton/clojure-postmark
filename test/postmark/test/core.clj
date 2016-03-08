(ns postmark.test.core
  (:require [postmark.core]
            [cheshire.core :refer [generate-string parse-string]]
            [clojure.test :refer :all]))


(deftest test-mail-to-json
  (is (= (@#'postmark.core/mail-to-json {"Subject" "foo" "Nothing" nil})
         (generate-string {"Subject" "foo"}))
      "parse mail"))

(deftest test-get-to-string
  (is (= (@#'postmark.core/to-string nil)
         nil)
      "get-to-string didn't handle nil")

  (is (= (@#'postmark.core/to-string "foo")
         "foo")
      "get-to-string didn't pass a string through")

  (is (= (@#'postmark.core/to-string ["foo"])
         "foo")
      "get-to-string didn't pass a string in a seq through")

  (is (= (@#'postmark.core/to-string ["foo" "bar"])
         "foo,bar")
      "get-to-string didn't join a seq properly"))
