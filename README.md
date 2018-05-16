# basic-spy

Basic spy for tracking if a method has been called.

## Installation

If using Leiningen:

```
[royvandewater/basic-spy "1.1.0"]
```

## Usage

```clojure
(ns example.core-test
  (:require [clojure.test :refer :all]
            [example.core :refer :all]
            [basic_spy.core :refer [create-spy called? call-count]]))

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
```

## License

Copyright © 2018

Distributed under the MIT License
