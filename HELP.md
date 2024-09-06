# Project details
Project configuration could be found in `./docker-compose.yml` and `./configuration`. And additionally in corresponding
`application.yml` and `Dockerfile` of each particular submodule.

### Guides
The following guides illustrate how to start and use the application:
1. Run compose file in the root of the project and all the services should be up and running:
```
docker-compose build --no-cache
docker-compose up -d
```
2. Now the messages could be sent via sender service using request below:
```
POST http://localhost:8090/notification
   {
   "user": "1st user",
   "message": "message8"
   }
```

If you need to build and start microservices separately, you can use the commands below:
```
docker build --progress=plain --no-cache -t sender .
docker run -d --name sender sender
```

### Gide for Kubernetes
0. Set minikube to use local images: `eval $(minikube docker-env)`. 
**Important note**: You have to run eval $(minikube docker-env) on each terminal you want to use, since it only sets the environment variables for the current shell session.
ref: https://stackoverflow.com/questions/42564058/how-can-i-use-local-docker-images-with-minikube/48999680#48999680

1. Build docker containers for all the services using commands:
```
docker build -t sender:release ./sender
docker build -t recipient:release  ./recipient
docker build -t collector:release ./collector
docker build -t visualizer:release ./visualizer
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
kubectl create configmap prometheus-config --from-file=./k8_configuration/prometheus.yml -n microserviceLab 
kubectl apply -f deployment_prometheus.yml

kubectl apply -f deployment_grafana.yml
```
to stop a pod use `kubectl delete -f deployment_rabbitmq.yml`.

4. To be able to reach services from your local machine establish a tunnel in separate console via command: `minikube tunnel`

### Canary deployment
1. Build docker image for visualizer canary version:
```
docker build -t visualizer:canary ./visualizer
```
2. Start canary pod and its service using commands:
```
kubectl apply -f canary_deployment/deployment_visualizer.yml
```
3. Install ingress-nginx
```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install nginx-ingress ingress-nginx/ingress-nginx
```
4. Configure ingress:
```
kubectl apply -f canary_deployment/deployment_ingress.yml
```
5. Check service: `visualizer.com/saved-messages` 


### Blue/green deployment
1. Build docker images for collector and receiver for blue/green deploy:
```
docker build -t collector:blueGreen ./collector
docker build -t receiver:blueGreen ./receiver
```

### Apps Links
- Grafana: http://localhost:15672/    
    login: admin   
    password: admin
- Prometheus: http://localhost:9090/targets
- Postgres: jdbc:postgresql://localhost:5432/messagesDb    
    login: admin    
    password: admin

### References
Microservice essentials:
https://www.linkedin.com/learning/spring-boot-2-0-essential-training-2    
https://www.linkedin.com/learning/extending-securing-and-dockerizing-spring-boot-microservices/    
Microservice architecture: https://www.linkedin.com/learning/spring-cloud-cloud-native-architecture-and-distributed-systems    
Actuator, Prometheus, Grafana configuration: https://www.linkedin.com/learning/advanced-spring-spring-boot-actuator/     
Canary deployment:   
https://earthly.dev/blog/canary-deployment-in-k8s/    
https://medium.com/tech-at-wildlife-studios/canary-deployment-in-kubernetes-how-to-use-the-pattern-b2e9c40d085d     