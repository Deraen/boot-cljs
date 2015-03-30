(ns adzerk.boot-cljs.impl
  (:require [clojure.java.io :as io]
            [cljs.closure    :as cljs]))

(defrecord CljsSourcePaths [paths]
  cljs/Compilable
  (-compile [this opts]
    (mapcat #(cljs/-compile % opts) paths)))

(defn compile-cljs
  [src-paths opts]
  (println src-paths opts)
  (io/make-parents (io/file (:output-to opts)))
  (time (cljs/build (CljsSourcePaths. (filter #(.exists (io/file %)) src-paths)) opts)))
