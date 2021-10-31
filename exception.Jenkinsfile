pipeline {
    agent {
          label 'master'
      }


      tools {
          jdk 'JDK11'
          maven 'MAVEN3.6.3'
      }

    triggers {
        GenericTrigger (
            // 构建时的标题
            causeString: 'Triggered by $ref',
            // 获取POST参数中的变量，key指的是变量名，通过$ref来访问对应的值，value指的是JSON匹配值（参考Jmeter的JSON提取器）
            // ref指的是推送的分支，格式如：refs/heads/master
            genericVariables: [[key: 'ref', value: '$.ref']],
            // 打印获取的变量的key-value，此处会打印如：ref=refs/heads/master
            printContributedVariables: true,
            // 打印POST传递的参数
            printPostContent: true,
            // regexpFilterExpression与regexpFilterExpression成对使用
            // 当两者相等时，会触发对应分支的构建
            regexpFilterExpression: '^refs/heads/(master|production)$',
            regexpFilterText: '$ref',
            // 与webhook中配置的token参数值一致
            token: 'exception-service'
        )
    }

      stages {
          stage('check code') {
              steps {
//                     script {
//                       scannerHome = tool 'sonarqube-scanner'
//                     }
                    // 引入sonarqube服务器环境
                    withSonarQubeEnv() {
//                       sh "${scannerHome}/bin/sonar-scanner"
                      sh "mvn clean verify sonar:sonar"
                    }
              }
            }
          stage('build') {
            steps {
              script {
    //                 sh "mvn clean package"
                sh "ls -la"
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
