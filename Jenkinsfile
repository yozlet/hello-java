pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                withGradle {
                    sh './gradlew run'
                }
            }
        }
    }
}
