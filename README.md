# boot-cljs-alt

Proof of concept. Minimal ClojureScript task for Boot.

Running [ClojureScript directly](https://github.com/clojure/clojurescript/wiki/Reporting-Issues)
is surprisingly easy and works really well. I created this task to test
how well minimal ClojureScript task would work.

## Differences to boot-cljs

- Uses fewer tempdirs


--- | Boot-cljs 0.0-2814 | Boot-cljs 0.0-3126 | *This*
--- | --- | --- | ---
Lines of code |   | 396 | 45
Cold build time | 15s | 9.5s | 9.5s
Watch build time | 0.25s | 0.25s | 0.25s


Windows

--- | Boot-cljs 0.0-2814 | Boot-cljs 0.0-3126 | *This*
--- | --- | --- | ---
Cold build time | 32s | 22s | 19s
Watch build time | 1.2s | 1.4s | 0.85s


Tested with [saapas][saapas]

## Results

- No performance improvements despite using fewer tempdirs, at least on Linux

## Missing features

- [ ] Setting `:main` and `:output-to` from `.cljs.edn`
- [ ] Error reporting
- [ ] Support for [boot-reload][boot-reload]

## License

Copyright © 2014 Adzerk<br>
Copyright © 2015 Juho Teperi

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[boot-cljs]: https://github.com/adzerk-oss/boot-cljs
[boot-reload]: https://github.com/adzerk-oss/boot-reload
[saapas]: https://github.com/Deraen/saapas
