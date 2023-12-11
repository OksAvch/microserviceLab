# Project details
Project configuration could be found in `./docker-compose.yml` and `./configuration`. And additionally in corresponding
`application.yml` and `Dockerfile` of each particular submodule.

### Guides
The following guides illustrate how to start application:
1. Run compose file in the root of the project and all the services should be up and running:
```
docker-compose build --no-cache    
docker-compose up -d
```
2. Now the messages could be send via sender service via request below:
```
POST http://localhost:8090/notification
   {
   "user": "1st user",
   "message": "message8"
   }
```

If you need to build and start microservices sepparetly you may use commands below:
```
docker build --progress=plain --no-cache -t sender .
docker run -d --name sender sender
```

### Links
- Grafana: http://localhost:15672/
    login: guest
    password: guest
- Prometheus: http://localhost:9090/targets
  - useful metric: rate(messages_sent_total[5m])
  - Grafana:
    login: admin
    password: 


### Additional Links
Microservice essentials: 
https://www.linkedin.com/learning/spring-boot-2-0-essential-training-2
https://www.linkedin.com/learning/extending-securing-and-dockerizing-spring-boot-microservices/
Microservice architecture: https://www.linkedin.com/learning/spring-cloud-cloud-native-architecture-and-distributed-systems
Actuator, Prometheus, Grafana configuration: https://www.linkedin.com/learning/advanced-spring-spring-boot-actuator/

