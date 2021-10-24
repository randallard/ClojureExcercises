(ns practice-jvm.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(System/getenv)

{"USER" "the-incredible-bulk"
 "JAVA_ARCH" "x86_64"}

(System/getProperty "user.dir")
(System/getProperty "java.version")

(let [file (java.io.File. "/")]
  (println (.exists file))
  (println (.canWrite file))
  (println (.getPath file)))

(spit "/tmp/hercules-todo-list"
      "- kill dat lion brov
- chop up what nasty multi-headed snake thing")
(slurp "/tmp/hercules-todo-list")
(let [s (java.io.StringWriter.)]
  (spit s "- capture cerynian hind like for real")
  (.toString s))
(let [s (java.io.StringReader. "- get erymanthian pig what with the tusks")]
  (slurp s))
(with-open [todo-list-rdr (clojure.java.io/reader "/tmp/hercules-todo-list")]
  (println (first (line-seq todo-list-rdr))))
