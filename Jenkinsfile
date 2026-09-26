pipeline {
    agent any

    environment {
        KUBECONFIG = '/tmp/jenkins-kubeconfig'
    }

    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t cloudcart/product-service:${BUILD_NUMBER} .'
            }
        }

        stage('Load Image into Kind') {
            steps {
                sh 'kind load docker-image cloudcart/product-service:${BUILD_NUMBER} --name cloudcart'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl set image deployment/product-service product-service=cloudcart/product-service:${BUILD_NUMBER}'
                sh 'kubectl rollout status deployment/product-service'
            }
        }
    }
}
