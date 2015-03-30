(ns adzerk.boot-cljs.impl
  (:require [clojure.java.io :as io]
            [cljs.closure    :as cljs]
            [cljs.env        :as env]))

(defrecord CljsSourcePaths [paths]
  cljs/Compilable
  (-compile [this opts]
    (mapcat #(cljs/-compile % opts) paths)))

(def ^:private stored-env (atom nil))

(defn cljs-env [opts]
  (compare-and-set! stored-env nil (env/default-compiler-env opts))
  @stored-env)

(defn compile-cljs
  [src-paths opts]
  (binding [env/*compiler* (cljs-env opts)]
    (cljs/build (CljsSourcePaths. (filter #(.exists (io/file %)) src-paths)) opts)))
