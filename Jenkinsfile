def projects = [
    "exception-cloud-gateway",
    "exception-dashboard",
    "exception-manager",
    "exception-transfer"
]

pipeline {
  agent {
    node {
      label 'maven'
    }

  }
  environment {
    DOCKER_CREDENTIAL_ID = 'dockerhub-id'
    GITHUB_CREDENTIAL_ID = 'github-id'
    KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
    REGISTRY = 'registry.cn-beijing.aliyuncs.com'
    DOCKERHUB_NAMESPACE = 'wxr_dev'
    GITHUB_ACCOUNT = 'kubesphere'
    APP_NAME = 'devops-java-sample'
    ALIYUNHUB_NAMESPACE = 'wxr_dev'
    HarborUrl = "registry.cn-beijing.aliyuncs.com"
    HarborRepo = "wxr_dev";
    ActiveProfile = "--spring.profiles.active=${env.BRANCH_NAME}"
    OriginVersion = "0.0.1-SNAPSHOT"
    ProjectVersion = "${env.BRANCH_NAME}-${BUILD_NUMBER}-SNAPSHOT"
  }
  stages {
    stage('拉取代码') {
      agent none
      steps {
        container('maven') {
          git(url: 'http://192.168.68.194:5010/backend/exception-service.git', credentialsId: 'gitee-account', branch: env.BRANCH_NAME, changelog: true, poll: false)
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


    stage('构建镜像') {
      agent none
      steps {
        script {
          container('maven') {
            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
              sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
              projects.each {
                def ImageName = "${REGISTRY}/${DOCKERHUB_NAMESPACE}/${it}:"
                sh "mvn -f ${it} dockerfile:build"
                sh "docker tag ${ImageName}${OriginVersion} ${ImageName}${ProjectVersion}"
                sh "docker push  ${ImageName}${ProjectVersion}"
              }
              sh "docker image prune -f"
            }
          }
        }
      }
    }


  

    stage('部署') {
        agent none
        steps {
          script {
            container ('maven') {
              withCredentials([
                  kubeconfigFile(
                  credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                  variable: 'KUBECONFIG')
                  ]) {
                    env.GATEWAY_PORT = env.BRANCH_NAME == 'dev' ? 30218 : 30219
                    projects.each {
                       sh "ls ./${it}/deploy/ -al"
                      sh "envsubst < ./${it}/deploy/deploy.yml | kubectl apply -f -"
                    }
                }
            }
          }
      }
    }
  }
}