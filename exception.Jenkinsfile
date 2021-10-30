pipeline {
    agent {
          label 'master'
      }


      tools {
          jdk 'JDK11'
          maven 'MAVEN3.6.3'
      }

      stages {
          stage('build') {
            steps {
              script {
                sh "mvn clean package"
              }
            }
          }
//           stage('clean') {
//               steps {
//                 script {
//                   cleanWs()
//                 }
//               }
//             }
      }
}
