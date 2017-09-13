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

(deftest test-postmark-test
  (testing "Do Postmark test function and API key work?"
    (let [test-rsp ((postmark.core/postmark-test "from-address@example.com") {:to ["fluffy@example.com" "sprinkles@example.com"]
                                                                              :subject "Testing"
                                                                              :text "I might want your noms."})]
      (is (= 200 (:status test-rsp)))
      (is (= "fluffy@example.com,sprinkles@example.com" (-> test-rsp :body :To)))
      (is (= "Test job accepted" (-> test-rsp :body :Message)))
      (is (zero? (-> test-rsp :body :ErrorCode))))))
