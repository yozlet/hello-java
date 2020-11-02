pipeline {
    agent any

    stages {
        stage('code-refs') {
            environment {
                LD_ACCESS_TOKEN = credentials('launchdarkly-access-token')
                LD_PROJ_KEY = 'hello-java'
                LD_REPO_NAME = 'hello-java'
                LD_DIR = '.'
                LD_BRANCH = env.BRANCH_NAME
            }
            steps {
                sh 'ld-find-code-refs'
            }
        }
        stage('build') {
            steps {
                withGradle {
                    sh './gradlew run'
                }
            }
        }
    }
}
