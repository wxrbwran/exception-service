  def git_auth = "9d9a2707-eab7-4dc9-b106-e52f329cbc95" //构建版本的名称
  def tag = "latest"
  //Harbor私服地址
  def harbor_url = "192.168.6.150:8085"
  //Harbor的项目名称
  def harbor_project_name = "tensquare"
  //Harbor的凭证
  def harbor_auth = "71eff071-ec17-4219-bae1-5d0093e3d060"
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
        // 第1步
        stage('拉取代码'){
          echo env.BRANCH_NAME
          git branch: "${env.BRANCH_NAME}", credentialsId: 'ssh-gitlab-ubuntu105', url: 'git@192.168.6.225:backend/exception-service.git'
          sh "ls -la"
        }
        // 第2步
        //编译并安装公共工程
        stage("编译安装common项目") {
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
                    sh "${mvnHome}/bin/mvn -f ${it} dockerfile:build"
                    sh "docker tag ${ImageName}${OriginVersion} ${ImageName}${ProjectVersion}"
                    sh "docker rmi ${ImageName}${OriginVersion}"
                    sh "docker push ${ImageName}${ProjectVersion}"
                    //部署到K8S
                    sh """
                      sed -i 's#\$IMAGE_NAME#${ImageName}#' ${it}/deploy.yml
                      sed -i 's#\$SECRET_NAME#${secret_name}#' ${it}/deploy.yml
                    """
                    kubernetesDeploy configs: "${it}/deploy.yml", kubeconfigId: "${k8s_auth}"
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
  