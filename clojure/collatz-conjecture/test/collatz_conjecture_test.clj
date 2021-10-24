(ns collatz-conjecture-test
  (:require [clojure.test :refer [deftest is testing]]
            [collatz-conjecture :refer [collatz]]))

(deftest first-test
  (testing "connected to source file"
    (is (= 1 1))))

(deftest exception-if-not-a-number
  (testing "thrown exception if not a number"
    (is (thrown? Exception (collatz-conjecture/collatz "Howdy")))))

(deftest get-next-from-4
  (testing "get-n-over-2 gives 2 when n is 4"
    (is (= 2 (collatz-conjecture/get-n-over-2 4)))))

(deftest get-next-from-even-n
  (testing "get-n-over-2 gives 1 when n is 2"
    (is (= 1 (collatz-conjecture/get-n-over-2 2)))))

(deftest get-next-from-odd-n
  (testing "get-3n-plus-1 gives 10 when n is 3"
    (is (= 10 (collatz-conjecture/get-3n-plus-1 3)))))

(deftest exception-thrown-when-starting-with-0
  (testing "an exception is thrown when n starts as 0"
    (is (thrown? Exception (collatz-conjecture/collatz 0)))))

(deftest collatz-of-1-is-0
  (testing "collatz of 1 is 0"
    (is (= 0 (collatz-conjecture/collatz 1)))))

(deftest collatz-of-2-is-1
  (testing "collatz of 2 is 1"
    (is (= 1 (collatz-conjecture/collatz 2)))))

(deftest collatz-of-3-is-7
  (testing "collatz of 3 is 7"
    (is (= 7 (collatz-conjecture/collatz 3)))))

(deftest collatz-of-4-is-2
  (testing "collatz of 4 is 2"
    (is (= 2 (collatz-conjecture/collatz 4))))) 

(deftest collatz-of-5-is-5
  (testing "collatz of 5 is 5"
    (is (= 5 (collatz-conjecture/collatz 5)))))

(deftest collatz-of-6-is-8
  (testing "collatz of 6 is 8"
    (is (= 8 (collatz-conjecture/collatz 6)))))
