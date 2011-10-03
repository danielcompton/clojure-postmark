# clojure-postmark

`clojure-postmark` lets you talk [Postmark](http://postmarkapp.com/) from
Clojure.

* Source (Mercurial): <http://bitbucket.org/sjl/clojure-postmark>
* Source (Git): <http://github.com/sjl/clojure-postmark>
* Issues: <http://github.com/sjl/clojure-postmark/issues>
* License: [MIT/X11](http://www.opensource.org/licenses/mit-license.php)

## Installation

Slap this in your `project.clj` `:dependencies`:

    [postmark "1.0.0"]

## Usage

First you need to load the `postmark` function:

    ;; In an (ns)
    (:use [postmark.core :only (postmark)])

    ;; Outside an (ns)
    (use '[postmark.core :only (postmark)])

Create a customized `postmark` function:

    (def pm (postmark "YOUR_API_KEY" "from-address@example.com"))

Now just call the function to send an email:

    (pm {:to "fluffy@example.com"
         :subject "Your Noms"
         :text "I wants them."})

You can send to multiple addresses by using a seq for `:to`, but remember that
Postmark's API won't let you send to more than twenty recipients at a time:

    (pm {:to ["fluffy@example.com" "sprinkles@example.com"]
         :subject "All of Your Noms"
         :text "I wants them."})

There are a few other keys you can use in the map you pass to the call:

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

    (use '[postmark.core :only (postmark-test)])
    (def pt (postmark-test "from-address@example.com"))

    (pt {:to ["fluffy@example.com" "sprinkles@example.com"]
         :subject "Testing"
         :text "I might want your noms."})

## Todo List

* Automatically generate plain text body if you just pass an html body.
