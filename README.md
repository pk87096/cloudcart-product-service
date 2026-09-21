# CloudCart - Product Service

CloudCart is a production-style cloud-native application built to demonstrate DevOps, Docker and Kubernetes deployment practices.

## Tech Stack

- Java 21
- Spring Boot 3.5
- Maven
- PostgreSQL 16
- Docker
- Kubernetes
- Kind
- NGINX Ingress
- ConfigMap
- Secret
- PersistentVolumeClaim
- Horizontal Pod Autoscaler
- GitHub
- Jenkins
- SonarQube

## Architecture

Developer
    |
    v
GitHub
    |
    v
Jenkins CI/CD
    |
    +--> Maven Build
    +--> SonarQube Analysis
    +--> Docker Image
    |
    v
Kubernetes
    |
    +--> NGINX Ingress
    |
    +--> Product Service
    |       |
    |       v
    |    PostgreSQL
    |       |
    |       v
    |      PVC
    |
    +--> HPA
    +--> Health Probes
    +--> ConfigMap / Secret

## Project Structure

product-service/
├── src/
├── k8s/
│   ├── product-deployment.yaml
│   ├── product-service.yaml
│   ├── product-configmap.yaml
│   ├── product-secret.yaml
│   ├── product-ingress.yaml
│   ├── postgres-deployment.yaml
│   ├── postgres-service.yaml
│   └── postgres-pvc.yaml
├── Dockerfile
├── .dockerignore
├── .gitignore
├── pom.xml
└── README.md

## Application

CloudCart Product Service is a Spring Boot REST API connected to PostgreSQL.

### API

GET /api/products

Example response:

[
  {
    "id": 1,
    "name": "Laptop",
    "price": 75000.0
  },
  {
    "id": 2,
    "name": "Keyboard",
    "price": 2500.0
  },
  {
    "id": 3,
    "name": "Mouse",
    "price": 1200.0
  }
]

## Docker

The application uses a multi-stage Docker build.

Build image:

docker build -t cloudcart/product-service:2.1 .

Run container:

docker run -p 8080:8080 cloudcart/product-service:2.1

## Kubernetes

The application is deployed on Kubernetes using:

- Deployment
- Service
- ConfigMap
- Secret
- PersistentVolumeClaim
- Ingress
- Horizontal Pod Autoscaler
- Readiness Probe
- Liveness Probe
- CPU and Memory Resource Limits

## Kubernetes Deployment

Apply the resources:

kubectl apply -f k8s/product-configmap.yaml
kubectl apply -f k8s/product-secret.yaml
kubectl apply -f k8s/postgres-pvc.yaml
kubectl apply -f k8s/postgres-deployment.yaml
kubectl apply -f k8s/postgres-service.yaml
kubectl apply -f k8s/product-deployment.yaml
kubectl apply -f k8s/product-service.yaml
kubectl apply -f k8s/product-ingress.yaml

Check deployment:

kubectl get pods
kubectl get services
kubectl get deployments
kubectl get ingress

## Health Check

Spring Boot Actuator provides:

/actuator/health

Kubernetes uses this endpoint for readiness and liveness probes.

## Horizontal Pod Autoscaling

The Product Service uses CPU-based autoscaling.

Example:

kubectl autoscale deployment product-service --cpu=50% --min=2 --max=5

Check HPA:

kubectl get hpa

## Database

PostgreSQL runs inside Kubernetes.

Application database connection:

jdbc:postgresql://postgres:5432/cloudcart

Persistent storage is provided using a Kubernetes PersistentVolumeClaim.

## Ingress

NGINX Ingress exposes the Product Service using:

cloudcart.local

Example:

curl -H "Host: cloudcart.local" http://127.0.0.1:8080/api/products

## CI/CD Roadmap

Git Push
    |
    v
Jenkins
    |
    +--> Maven Build
    +--> Unit Tests
    +--> SonarQube
    +--> Docker Build
    +--> Push Image
    +--> Kubernetes Deployment
    |
    v
Application Running

## DevOps Concepts Demonstrated

- Git and GitHub
- CI/CD
- Docker containerization
- Multi-stage Docker builds
- Kubernetes Deployments
- Kubernetes Services
- ConfigMaps
- Secrets
- Persistent Volumes
- Ingress
- Health Probes
- Resource Requests and Limits
- Horizontal Pod Autoscaling
- PostgreSQL
- Container Image Versioning

## Future Improvements

- Jenkins CI/CD pipeline
- SonarQube Quality Gate
- Docker Registry integration
- Automated image tagging
- Prometheus and Grafana
- Centralized logging
- Helm charts
- AWS EKS deployment
