pipeline {
  agent any

  environment {
    IMAGE = "ghcr.io/fahmi-dot/finance-records-api"
    TAG = "latest"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build App') {
      steps {
        sh './mvnw clean package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh "docker build -t $IMAGE:$TAG ."
      }
    }

    stage('Push to GHCR') {
      steps {
        withCredentials([string(credentialsId: 'ghcr-token', variable: 'GITHUB_TOKEN')]) {
          sh "echo $GITHUB_TOKEN | docker login ghcr.io -u fahmi-dot --password-stdin"
          sh "docker push $IMAGE:$TAG"
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        sh 'kubectl apply -f k8s/deployment.yaml'
        sh 'kubectl apply -f k8s/service.yaml'
        sh 'kubectl apply -f k8s/ingress.yaml'
      }
    }
  }
}
