pipeline {
  agent {
    node {
      label 'maven'
    }

  }
  stages {
    stage('拉取代码') {
      agent none
      steps {
        container('maven') {
          git(url: 'http://192.168.68.194:5010/backend/exception-service.git', credentialsId: 'gitee-account', branch: env.BranchName, changelog: true, poll: false)
          sh 'ls -l'
        }

      }
    }

    stage('编译项目') {
      agent none
      steps {
        container('maven') {
          sh 'ls -la'
          sh 'mvn clean package -Dmaven.test.skip=true'
        }

      }
    }

    stage('default-2') {
      parallel {

        stage('构建service-task镜像') {
          agent none
          steps {
            container('maven') {
              sh 'ls service/service-task/target'
              sh 'docker build -t service-task:latest -f service/service-task/Dockerfile  ./service/service-task/'
            }

          }
        }

        stage('构建service-user镜像') {
          agent none
          steps {
            container('maven') {
              sh 'ls service/service-user/target'
              sh 'docker build -t service-user:latest -f service/service-user/Dockerfile  ./service/service-user/'
            }

          }
        }

      }
    }

    stage('default-3') {
      parallel {
        stage('推送service-task镜像') {
          agent none
          steps {
            container('maven') {
              withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                sh 'docker tag service-task:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-task:SNAPSHOT-$BUILD_NUMBER'
                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-task:SNAPSHOT-$BUILD_NUMBER'
              }

            }

          }
        }

        stage('推送service-user镜像') {
          agent none
          steps {
            container('maven') {
              withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                sh 'docker tag service-user:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-user:SNAPSHOT-$BUILD_NUMBER'
                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-user:SNAPSHOT-$BUILD_NUMBER'
              }

            }

          }
        }

      }
    }

     stage('default-4') {
        parallel {
            stage('service-sms - 部署到dev环境') {
                agent none
                steps {
                 container ('maven') {
                      withCredentials([
                          kubeconfigFile(
                          credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                          variable: 'KUBECONFIG')
                          ]) {
                          sh 'ls ./service/service-sms/deploy/ -al'
                          sh 'envsubst < ./service/service-sms/deploy/deploy.yml | kubectl apply -f -'
                      }
                 }
              }
            }
            stage('service-oss - 部署到dev环境') {
                agent none
                steps {
                 container ('maven') {
                      withCredentials([
                          kubeconfigFile(
                          credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                          variable: 'KUBECONFIG')
                          ]) {
                          sh 'ls ./service/service-oss/deploy/ -al'
                          sh 'envsubst < ./service/service-oss/deploy/deploy.yml | kubectl apply -f -'
                      }
                 }
              }
            }

        }
    }
  }
  environment {
    DOCKER_CREDENTIAL_ID = 'dockerhub-id'
    GITHUB_CREDENTIAL_ID = 'github-id'
    KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
    REGISTRY = 'registry.cn-beijing.aliyuncs.com'
    DOCKERHUB_NAMESPACE = 'wxr_hello'
    GITHUB_ACCOUNT = 'kubesphere'
    APP_NAME = 'devops-java-sample'
    ALIYUNHUB_NAMESPACE = 'wxr_hello'
  }
//   parameters {
//     string(name: 'TAG_NAME', defaultValue: '', description: '')
//   }
}