# basic-spy

Basic spy for tracking if a method has been called.

## Installation

If using Leiningen:

```
[royvandewater/basic-spy "1.0.0"]
```

## Usage

```clojure
(ns example.core-test
  (:require [clojure.test :refer :all]
            [example.core :refer :all]
            [basic_spy.core :refer [create-spy called?]]))

; Create an noop spy
(let [spy (create-spy)]
  (func-that-calls create-spy)
  (is (called? spy))) ; will only pass if func-that-calls create-spy actually calls create-spy

; Create an passthru
(let [spy (create-spy #(inc %))]
  (map spy [1 2 3]); will return lazy seq: [2 3 4]
  (is (called? spy))) ; will pass because spy was called thrice
```

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

```

```
