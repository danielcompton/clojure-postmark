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

Postmark also support [templates](https://www.google.com.br/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=postmark%20template). In this case you need to use
``postmark-template`` function instead of ``postmark``:

   ::clojure
   (def pmt (postmark-template "YOUR_API_KEY" "from-address@example.com"))

Now just call the function to send an email using a exist template:

   ::clojure
   (pmt {:to "fuffy@example.com", :template_id 383213, :template_model {:name "John Locke"}})

## Testing

If you just want to run a test you can use `postmark-test` without an API key
instead of `postmark`:

    :::clojure
    (use '[postmark.core :only (postmark-test)])
    (def pt (postmark-test "from-address@example.com"))

    (pt {:to ["fluffy@example.com" "sprinkles@example.com"]
         :subject "Testing"
         :text "I might want your noms."})
