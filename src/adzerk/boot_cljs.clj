(ns adzerk.boot-cljs
  {:boot/export-tasks true}
  (:require [clojure.java.io :as io]
            [boot.pod        :as pod]
            [boot.core       :as core]))

(def cljs-version "0.0-3126")

(def ^:private deps
  (delay (remove pod/dependency-loaded? `[[org.clojure/clojurescript ~cljs-version]])))

(core/deftask cljs
  "Compile ClojureScript applications.

   The --compiler-options option can be used to set any other options that should
   be passed to the Clojurescript compiler. A full list of options can be found
   here: https://github.com/clojure/clojurescript/wiki/Compiler-Options."

  [c compiler-options OPTS edn  "Options to pass to the Clojurescript compiler."]

  (let [pod-env    (-> (core/get-env) (update-in [:dependencies] into (vec (seq @deps))))
        pod        (future (pod/make-pod pod-env))
        tmp        (core/temp-dir!)
        compiler-options (assoc compiler-options
                                :output-dir (.getPath tmp)
                                :output-to (.getPath (io/file (.getPath tmp) "core.js")))]
    (core/with-pre-wrap fileset
      (let [dirs (map #(.getPath %) (core/input-dirs fileset))]
        (pod/with-call-in @pod
          (adzerk.boot-cljs.impl/compile-cljs ~dirs ~compiler-options)))
      (-> fileset (core/add-resource tmp) core/commit!))))
