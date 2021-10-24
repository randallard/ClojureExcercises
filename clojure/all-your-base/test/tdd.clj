(ns all-your-base-test
  (:require [clojure.test :refer [deftest testing is]]
            [all-your-base]))

(deftest test-one-is-one
  (testing "one is one"
    (is (= 1 1))))

