(ns postmark.test.core
  (:use [postmark.core])
  (:use [cheshire.core :only (generate-string parse-string)])
  (:use [clojure.test]))


(deftest test-mail-to-json
  (is (= (@#'postmark.core/mail-to-json {"Subject" "foo" "Nothing" nil})
         (generate-string {"Subject" "foo"}))
      "parse mail"))

(deftest test-get-to-string
  (is (= (@#'postmark.core/get-to-string nil)
         nil)
      "get-to-string didn't handle nil")

  (is (= (@#'postmark.core/get-to-string "foo")
         "foo")
      "get-to-string didn't pass a string through")

  (is (= (@#'postmark.core/get-to-string ["foo"])
         "foo")
      "get-to-string didn't pass a string in a seq through")

  (is (= (@#'postmark.core/get-to-string ["foo" "bar"])
         "foo,bar")
      "get-to-string didn't join a seq properly"))
