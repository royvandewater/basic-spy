(ns basic_spy.core-test
  (:require [clojure.test :refer :all]
            [basic_spy.core :refer :all]))

(deftest examples-test

  (testing "with a default noop spy"
    (let [spy (create-spy)]
      (spy)
      (is (called? spy))))

  (testing "when not called"
    (let [spy (create-spy)]
      (is (not (called? spy)))))

  (testing "with a passthru spy"
    (let [spy (create-spy #(inc %))]
      (is (= [2 3 4] (doall (map spy [1 2 3]))))
      (is (called? spy)))) 

  (testing "counting the calls"
    (let [spy (create-spy)]
      (spy)
      (spy)
      (spy)
      (is (= 3 (call-count spy))))))

(deftest spy-test
  (testing "when called"
    (let [my-spy (create-spy)]
      (my-spy)
      (is (true? ((:called? (meta my-spy)))))))

  (testing "when not called"
    (let [my-spy (create-spy)]
      (is (false? ((:called? (meta my-spy)))))))

  (testing "when created with a function, should call that function"
    (let [inner-spy (create-spy)
          my-spy (create-spy inner-spy)]
      (my-spy)
      (is (true? ((:called? (meta inner-spy)))))))

  (testing "when not called, should not call that function"
    (let [inner-spy (create-spy)
          my-spy (create-spy inner-spy)]
      (is (false? ((:called? (meta inner-spy)))))))

  (testing "when called once"
    (let [my-spy (create-spy)]
      (my-spy)
      (is (= 1 ((:call-count (meta my-spy)))))))

  (testing "when called twice"
    (let [my-spy (create-spy)]
      (my-spy)
      (my-spy)
      (is (= 2 ((:call-count (meta my-spy))))))))

(deftest called?-test
  (testing "when called"
    (let [my-spy (create-spy)]
      (my-spy)
      (is (true? (called? my-spy)))))

  (testing "when not called"
    (let [my-spy (create-spy)]
      (is (false? (called? my-spy))))))

(deftest call-count-test
  (testing "when called once"
    (let [my-spy (create-spy)]
      (my-spy)
      (is (= 1 (call-count my-spy)))))

  (testing "when called twice"
    (let [my-spy (create-spy)]
      (my-spy)
      (my-spy)
      (is (= 2 (call-count my-spy))))))
