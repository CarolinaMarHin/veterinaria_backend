@Library('ceiba-jenkins-library@master') _
pipeline{

    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK8_Centos'
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout scm
            }
        }

        stage('Compilacion y Test Unitarios'){

            steps{

                echo "------------>Clean Tests<------------"

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle clean'

                echo "------------>compile & Unit Tests<------------"

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }

        }

        stage('Static Code Analysis') {
        			steps{
                        echo '------------>Análisis de código estático<-----------'

        				sonarqubeMasQualityGatesP(
                            sonarKey:'co.com.ceiba:adn:veterinaria.carolina.marin',
                            sonarName:'''"CeibaADN-Veterinaria(carolina.marin)"''',
                            sonarPathProperties:'./sonar-project.properties'
                        )
        			}
        		}

        stage('Build'){
            steps{
                echo "------------>Build<------------"
                sh './microservicio/gradlew --b ./microservicio/build.gradle build -x test'
            }
         }
    }

    post {
        failure {
            mail(
                to: 'carolina.marin@ceiba.com.co',
                body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
                subject: "ERROR CI: ${env.JOB_NAME}"
            )
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'failed'
        }
        success {
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'success'
        }
    }
}
