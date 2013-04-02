statsd
------------------------

Pragmatach includes simple support for statsd, via the a plugin which subscribes to Pragmatach lifecycle events.

The configuration parameters to enable the pragmatach-statsd plugin are:

* statsd.prefix
* statsd.host
* statsd.port

Events for statsd are supplied by an implemenation of the Pragmatach interface:

`com.khubla.pragmatach.framework.lifecycle.LifecycleListener`

