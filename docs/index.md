# clojure-postmark

`clojure-postmark` lets you talk to [Postmark](http://postmarkapp.com/) from
Clojure.

* Documentation: <https://danielcompton.github.io/clojure-postmark/>
* Source (Git): <http://github.com/danielcompton/clojure-postmark>
* Issues: <http://github.com/danielcompton/clojure-postmark/issues>
* License: [MIT/X11](http://www.opensource.org/licenses/mit-license.php)


Installation
============

To get started just slap this in your `project.clj` `:dependencies`:

    [postmark "1.1.0"]

`clojure-postmark` works with Clojure 1.8.0.


Usage
=====

First you need to load the `postmark` function:

    :::clojure
    ; In an (ns)
    (:use [postmark.core :only (postmark)])

    ; Outside an (ns)
    (use '[postmark.core :only (postmark)])

Create a customized `postmark` function:

    :::clojure
    (def pm (postmark "YOUR_API_KEY" "from-address@example.com"))

Now just call the function to send an email:

    :::clojure
    (pm {:to "fluffy@example.com"
         :subject "Your Noms"
         :text "I wants them."})

You can send to multiple addresses by using a seq for `:to`, but remember that
Postmark's API won't let you send to more than twenty recipients at a time:

    :::clojure
    (pm {:to ["fluffy@example.com" "sprinkles@example.com"]
         :subject "All of Your Noms"
         :text "I wants them."})

There are a few other keys you can use in the map you pass to the call:

    :::clojure
    (pm {:to ["fluffy@example.com" "sprinkles@example.com"]
         :cc ["haiku@example.com"]
         :bcc ["admin@example.com"]
         :subject "All of Your Noms"
         :text "I wants them."
         :html "I <b>wants</b> them."
         :tag "Noms"
         :reply-to "avedon@example.com"})

## Testing

If you just want to run a test you can use `postmark-test` without an API key
instead of `postmark`:

    :::clojure
    (use '[postmark.core :only (postmark-test)])
    (def pt (postmark-test "from-address@example.com"))

    (pt {:to ["fluffy@example.com" "sprinkles@example.com"]
         :subject "Testing"
         :text "I might want your noms."})

---

Created by [Steve Losh](http://stevelosh.com), and maintained by [Daniel Compton](https://danielcompton.net).


