#URL Shortner

In this challenge we're asking you to spice up your life with your very own URL Shortener!
We've all seen sites like bit.ly that allow you to shorten a URL into something... well... shorter! It's time to
you make your own.
There are roughly four parts to this challenge:
1. Make a small API app that receives in a URL with a stack of your choice.

2. Using the supplied URL, generate a unique URL with the base of tier.app. 
It should be generated keeping uniqueness in mind.

3. Return the shortened URL.   

4.Bonus: track the visits in a second DB table for stats.

5.Bonus: have a high test coverage.

####To Run the Project

1. start up a mongodb docker instance
            ``docker run -p 27017:27017 mongo:4.0.10``

2.  ``sbt run``

To create a shortened url

``curl -X POST 'localhost:8080/overlylongandcomplicatedurl.com' -v``

Should return 
`` tier.app/b3Zlcmx5bG9uZ2FuZGNvbXBsaWNhdGVkdXJsLmNvbQ==``


To get the url back 
``curl -X GET 'localhost:8080/b3Zlcmx5bG9uZ2FuZGNvbXBsaWNhdGVkdXJsLmNvbQ==' -v``

Should return ``overlylongandcomplicatedurl.com``
