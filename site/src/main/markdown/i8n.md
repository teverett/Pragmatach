i8n support
------------------------

I8N support is provided by Pragmatach plugins which expose implementation of `I8NProvider` to Pragmatach.  The variable `i8n` is then available in template engines to use for internationalization.  The plugin `pragmatach-i8n` provides simple internationalization of strings and dates.

An example i8n file, used by `pragmatach-i8n` for the locale `en_us` would have the name `i8n.properties_en_us`.  The contents of the example are:
<pre><code>
admin=Administration
home=Home
rssviewer=RSS Viewer
</code></pre>

An example of using this localization data from a Freemarker template is:

`<a class="brand" href="#">${i8n.text("en_us","rssviewer")}</a>`

which generates this HTML:

`<a class="brand" href="#">RSS Viewer</a>`
