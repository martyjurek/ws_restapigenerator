#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
    }
    stages {
        stage ('Initialize') {
            steps {
                bat '''
                    echo "PATH = %PATH%"
                    echo "M2_HOME = %M2_HOME%"
                '''
            }
        }

        stage ('Build') {
            steps {
                bat 'mvn clean deploy' 
            }
        }
        stage ('Scan') {
            steps {
                bat 'mvn verify sonar:sonar -Dsonar.projectKey=CME_RestAPIGenerator:master -Dsonar.projectName=CME_RestAPIGenerator:master' 
            }
        }
    }
}