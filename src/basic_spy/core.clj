(ns basic_spy.core)

(defmacro called?
  "Convenience macro for checking if a spy has been called"
  [spy]
  `((:called? (meta ~spy))))

(defn create-spy
  "Create a new spy function"
  ([]
   (create-spy #(constantly nil)))

  ([f]
   (let [n (atom 0)]
     (with-meta
       (fn [& args]
         (swap! n inc)
         (apply f args))
       {:called? (fn [] (> @n 0))}))))
