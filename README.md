Weather App
===========

A microservice that, for a given city, returns the current temperature, air pressure and an indicator of
whether you should take an umbrella with you or not.

# Requirements

- [Java 11](https://jdk.java.net/11/)

# Build

~~~bash
$ ./gradlew run
~~~

# Test

~~~bash
$ ./gradlew test
~~~

# API

## Current

Provides the current temperature in degrees celsius, air pressure in hPa and
a boolean whether to take an umbrella (`true`) or not (`false`)

~~~bash
$ curl http://localhost:4567/current?location=Berlin
{
  "temp": 12.900000000000034,
  "pressure": 1023,
  "umbrella": false
}
~~~

~~~bash
$ curl http://localhost:4567/current?location=Reykjavík
{
  "temp": 8.520000000000039,
  "pressure": 1013,
  "umbrella": true
}
~~~

~~~bash
$ curl http://localhost:4567/current?location=notfound
No weather info for given location
~~~

## History

Provides historical data as well as an average over the last 5 queries for the same city.

~~~bash
$ curl http://localhost:4567/history?location=Berlin
{
  "avg_temp": 12.876000000000033,
  "avg_pressure": 1023,
  "list": [
    {
      "temp": 12.900000000000034,
      "pressure": 1023,
      "umbrella": false
    },
    {
      "temp": 12.900000000000034,
      "pressure": 1023,
      "umbrella": false
    },
    {
      "temp": 12.900000000000034,
      "pressure": 1023,
      "umbrella": false
    },
    {
      "temp": 12.78000000000003,
      "pressure": 1023,
      "umbrella": false
    },
    {
      "temp": 12.900000000000034,
      "pressure": 1023,
      "umbrella": false
    }
  ]
}
~~~
