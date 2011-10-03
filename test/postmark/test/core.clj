(ns postmark.test.core
  (:use [postmark.core])
  (:use [cheshire.core :only (generate-string parse-string)])
  (:use [clojure.test]))

(deftest test-maybe-assoc
  (is (= (@#'postmark.core/maybe-assoc {:foo 1} :bar 2)
         {:foo 1 :bar 2})
      "maybe-assoc didn't work with a truthy value")

  (is (= (@#'postmark.core/maybe-assoc {:foo 1} :bar nil)
         {:foo 1})
      "maybe-assoc didn't work with a falsy value"))

(deftest test-parse-mail
  (is (= (@#'postmark.core/parse-mail {"Subject" "foo" "Nothing" nil})
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
