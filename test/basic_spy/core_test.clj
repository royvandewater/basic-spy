(ns basic_spy.core-test
  (:require [clojure.test :refer :all]
            [basic_spy.core :refer :all]))

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
      (is (false? ((:called? (meta inner-spy))))))))

(deftest called?-test
  (testing "when called"
    (let [my-spy (create-spy)]
      (my-spy)
      (is (true? (called? my-spy)))))

  (testing "when not called"
    (let [my-spy (create-spy)]
      (is (false? (called? my-spy))))))
