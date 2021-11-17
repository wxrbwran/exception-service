def HarborUrl = "192.168.6.194:8085"
def HarborAccount = "harbor-account"
def projects = [
    "exception-eureka",
//     "exception-cloud-gateway",
//     "exception-dashboard",
//     "exception-manager",
//     "exception-transfer"
]
def projectPorts = [
    "exception-eureka": 10010,
    "exception-cloud-gateway": 10086,
    "exception-dashboard": 9999,
    "exception-manager": 9998,
    "exception-transfer": 9997
]
def HarborRepo = "tensquare";
def K8sHarbor = "k8s-harbor";

podTemplate(label: 'jenkins-slave', cloud: 'kubernetes', containers: [
      containerTemplate(
          name: 'jnlp',
          image: "wxrbw/jenkins-slave-maven:latest"
      ),
      containerTemplate(
          name: 'docker',
          image: "docker:stable",
          ttyEnabled: true,
          command: 'cat'
      ),
    ],
    volumes: [
      hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
      nfsVolume(
        mountPath: '/usr/local/apache-maven/repo',
        serverAddress: '192.168.6.142',
        serverPath: '/opt/nfs/maven'
      ),
    ],
  ) {
    node("jenkins-slave"){
      def ActiveProfile = "--spring.profiles.active=${env.BRANCH_NAME}"
      def ServerUserByEnv = [
          "dev": "xiaoran",
          "test": "xiaoran"
      ]
      def ServerHostByEnv = [
          "dev": "192.168.6.174",
          "test": "192.168.6.182"
      ]
      def OriginVersion = "0.0.1-SNAPSHOT"
      def ProjectVersion = "0.0.1-${env.BRANCH_NAME}-SNAPSHOT"
        // 第1步
        stage('拉取代码'){
          echo env.BRANCH_NAME
          checkout([$class: 'GitSCM', branches: [[name: env.BRANCH_NAME]], extensions: [], userRemoteConfigs: [[credentialsId: 'git-private', url: 'ssh://git@192.168.6.194:9022/back/exception-service.git']]])
          sh "ls -la"
        }
        // 第2步
        //编译并安装公共工程
        stage("编译安装common项目") {
//             sh "mvn clean compile"
            sh "mvn -v"
            sh "mvn -f exception-common clean install"
        }
        // 第3步
        stage('代码编译'){
          sh "mvn clean package"
        }
        // 第4步
        stage('构建镜像，部署项目'){
          //把选择的项目信息转为数组
          withCredentials([usernamePassword(
                credentialsId: 'harbor-account',
                passwordVariable: 'password',
                usernameVariable: 'username'
          )]) {
              container('docker') {
                sh "docker login -u ${username} -p ${password} http://${HarborUrl}"
                projects.each {
                    def ImageName = "${HarborUrl}/${HarborRepo}/${it}:"
                    sh "mvn -f ${it} dockerfile:build"
                    sh "docker tag ${ImageName}${OriginVersion} ${ImageName}${ProjectVersion}"
                    sh "docker rmi ${ImageName}${OriginVersion}"
                    sh "docker push ${ImageName}${ProjectVersion}"
                    //部署到K8S
//                     sh """
//                       sed -i 's#\$IMAGE_NAME#${ImageName}#' ${it}/deploy.yml
//                       sed -i 's#\$SECRET_NAME#${K8sHarbor}#' ${it}/deploy.yml
//                     """
//                     kubernetesDeploy configs: "${it}/deploy.yml", kubeconfigId: "k8s-config"
                }
                sh "docker image prune -f"
                //给镜像打标签
                // sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
                // //登录Harbor，并上传镜像
                // withCredentials([usernamePassword(
                //   credentialsId: "${harbor_auth}",
                //   passwordVariable: 'password',
                //   usernameVariable: 'username')]){
                //   //登录
                //   sh "docker login -u ${username} -p ${password} ${harbor_url}"
                //   //上传镜像
                //   sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
                // }
                // //删除本地镜像
                // sh "docker rmi -f ${imageName}"
                // sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"
              }
              
          }
           
      }
    }
}
  