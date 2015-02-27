(ns com.iterinc.web-file
  "CLJS wrapper around JS File API."
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.core.async :refer [chan <! >!]]))

(defn read-as-binary-string
  "Given a JS File object, return async channel containing binary contents as base64-encoded data: URL."
  [f]
  (let [fr (js/FileReader.)
        ch (chan)]
    (set! (.-onloadend fr) (fn [] (go (>! ch (.-result fr)))))
    (.readAsDataURL fr f)
    ch))

(defn read-as-binary-array
  "Given a JS File object, return async channel containing binary contents as ArrayBuffer"
  [f]
  (let [fr (js/FileReader.)
        ch (chan)]
    (set! (.-onloadend fr) (fn [] (go (>! ch (.-result fr)))))
    (.readAsArrayBuffer fr f)
    ch))

(defn read-as-string
  "Given a JS File object, return async channel containing binary contents as ArrayBuffer"
  [f]
  (let [fr (js/FileReader.)
        ch (chan)]
    (set! (.-onloadend fr) (fn [] (go (>! ch (.-result fr)))))
    (.readAsText fr f)
    ch))
