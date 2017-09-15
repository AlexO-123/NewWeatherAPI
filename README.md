# weather

The Project
---
This project was one of my first coding challenges after taking up a position after graduating from university. The task was to read some json from an api, map it to a pojo and display that pojo back to the user. This task allowed me to discover technologies I have never experienced before such as Dropwizard, Maven, Json and ... eventually MongoDB. 

Dropwizard comes packaged with helpful libraries such as Jackson which I use to map json data to my POJO. My first task was understanding how this was done. Jackson binds keywords to the pojo, so I knew my varaiable names needed to match .. can be different but makes it easier to read. Further more, the Json I was mapping was nested with different objects within. This meant that I had to create a pojo for each of these, and include one final pojo which wraps all these pojos together.

Once this was completed and the pojo was displayed back to the user, my next challenge was to write this to a mongoDB and cache the data. Mongo was not something I have had experience with before, and was interesting to see how it handles compared to something like MySQL or PostgreSQL. To cache the data, we simply wanted to call the api, and if the time between calling the api was grater than an hour, then we should update the record in Mongo ... if it existed in the first place.

Overall, this exercise was a great way to explore the technologies currently being used in my place of work. The final stage of this was to Dockerise the project. However, this has not been acheive as of yet due to time constraints on my end.

How to start the weather application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/new-weather-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
