To run this project

start up a mongodb docker instance

``docker run -p 27017:27017 mongo:4.0.10``


then 
``sbt run``

To create a shortened url

``curl -X POST 'localhost:8080/overlylongandcomplicatedurl.com' -v``

Should return 
`` ???``



