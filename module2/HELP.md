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

### Gide for Kubernetes
0. Set minikube to use local images: `eval $(minikube docker-env)`. 
**Important note**: You have to run eval $(minikube docker-env) on each terminal you want to use, since it only sets the environment variables for the current shell session.
ref: https://stackoverflow.com/questions/42564058/how-can-i-use-local-docker-images-with-minikube/48999680#48999680

1. Build docker containers for all the images using commands:
```
docker build -t module2/sender:release ./sender
docker build -t module2/recipient:release  ./recipient
docker build -t module2/collector:release ./collector
docker build -t module2/visualizer:release ./visualizer
```
2. Create namespace using command: `kubectl apply -f namespaces.yaml`
3. Start pods using commands: 
```
kubectl apply -f deployment_rabbitmq.yml
kubectl apply -f deployment_sender.yml
kubectl apply -f deployment_recipient.yml
kubectl apply -f deployment_collector.yml
kubectl apply -f deployment_visualizer.yml

#to start prometheus
kubectl create configmap prometheus-config --from-file=./k8_configuration/prometheus.yml -n module2
kubectl apply -f deployment_prometheus.yml

kubectl apply -f deployment_grafana.yml
```
to stop a pod use `kubectl down -f deployment_rabbitmq.yml`.

4. To be able to reach services from your local machine establish a tunnel in separate console via command: `minikube tunnel`

### Links
- Grafana: http://localhost:15672/
    login: guest
    password: guest
- Prometheus: http://localhost:9090/targets
  - useful metric: rate(messages_sent_total[5m])
  - Grafana:
    login: admin
    password: admin


### Additional Links
Microservice essentials: 
https://www.linkedin.com/learning/spring-boot-2-0-essential-training-2
https://www.linkedin.com/learning/extending-securing-and-dockerizing-spring-boot-microservices/
Microservice architecture: https://www.linkedin.com/learning/spring-cloud-cloud-native-architecture-and-distributed-systems
Actuator, Prometheus, Grafana configuration: https://www.linkedin.com/learning/advanced-spring-spring-boot-actuator/

