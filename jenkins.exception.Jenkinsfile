// 声明式

// def mvnHome = tool 'MAVEN3.6.3'
def projects = [
    "exception-cloud-gateway",
    "exception-dashboard",
    "exception-manager",
    "exception-transfer"
]
def projectPorts = [
    "exception-cloud-gateway": 10086,
    "exception-dashboard": 9999,
    "exception-manager": 9998,
    "exception-transfer": 9997
]

pipeline {

    environment {
        HarborUrl="registry.cn-beijing.aliyuncs.com"
         HarborRepo = "wxr_dev";
         ActiveProfile = "--spring.profiles.active=${env.BRANCH_NAME}"
         OriginVersion = "0.0.1-SNAPSHOT"
         ProjectVersion = "0.0.1-${env.BRANCH_NAME}-SNAPSHOT"
    }
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
        stage("清理") {
            steps {
                cleanWs()
            }
        }
        stage("拉代码") {
            steps {
                echo env.BRANCH_NAME
                git branch: "${env.BRANCH_NAME}", credentialsId: 'gitee-account', url: 'https://gitee.com/wu-xiaoran/exception-service.git'
                sh "ls -la"
            }

            
        }
    //     stage("代码审查") {
    //          def mvnHome = tool 'MAVEN3.6.3'
    //          def scannerHome = tool 'sonarqube-scanner';
    //          withSonarQubeEnv('sonarqube-server') {
    //             sh "${mvnHome}/bin/mvn clean verify sonar:sonar"
    //         }
    //     }
        
        stage("编译打包微服务工程") {
            steps {
                sh "mvn clean package"
            }
        }
        stage("构建docker镜像") {
            steps {
                    script {
                    withCredentials([usernamePassword(
                        credentialsId: 'harbor-account',
                        passwordVariable: 'password',
                        usernameVariable: 'username'
                    )]) {
                    
                        sh "docker login -u ${username} -p ${password} http://${HarborUrl}"
                        projects.each {
                            def ImageName = "${HarborUrl}/${HarborRepo}/${it}:"
                            sh "mvn -f ${it} dockerfile:build"
                            sh "docker tag ${ImageName}${OriginVersion} ${ImageName}${ProjectVersion}"
                            sh "docker rmi ${ImageName}${OriginVersion}"
                            sh "docker push ${ImageName}${ProjectVersion}"
                        }
                        sh "docker image prune -f"
                    }
                }
            }
        }
    //     stage("部署服务器拉取镜像") {
    //         sshagent(credentials: ["jenkins-private"]) {
    //             projects.each {
    // //                 def port = projectPorts[it]
    // //                 sshPublisher(publishers: [sshPublisherDesc(configName: "ubuntu174", transfers: [sshTransfer(cleanRemote: false, excludes: '',
    // //                  execCommand: "ip addr && /home/xiaoran/sh/deploy.sh $HarborUrl $HarborRepo $it $ProjectVersion ${port}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
    //                 def ProjectName = it
    //                 def Port = projectPorts[it]
    //                 def user = ServerUserByEnv[env.BRANCH_NAME]
    //                 def host = ServerHostByEnv[env.BRANCH_NAME]
    //                 sh "ssh -o StrictHostKeyChecking=no -l ${user} ${host}  '/home/xiaoran/sh/deploy.sh $HarborUrl $HarborRepo $ProjectName $ProjectVersion $Port $ActiveProfile' && docker image prune -f"
    //             }
    //         }
    //     }
    }
}



