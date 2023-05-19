pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'openjdk:11.0.5-jre-slim-buster'
                    image 'maven'
                    args '-u root'
                    args '-v $HOME/.m2/repo/:$HOME/.m2/repo/'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("harshvr04/selenium-docker")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
			        	app.push("${BUILD_NUMBER}")
			            app.push("latest")
			        }
                }
            }
        }
    }
}