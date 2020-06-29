pipeline {
    options {
        timestamps()
    }
    parameters {
        string(name: 'BUILD_VERSION', defaultValue: '', description: 'The build version to deploy (optional)')
    }
    agent {
        label 'medtag-dev-ec2-02'
    }
    triggers {
        pollSCM('H/5 * * * *')
    }  
  environment {
        PROJECT_NAME = "medtagger_web"
        DOCKER_REPO_NAME = "ymnliu/medtagger_web"
    }
    stages {
        stage('Build Version') {
            when {
                expression {
                    return !params.BUILD_VERSION
                }
            }
            steps{
                script {
                    BUILD_VERSION_GENERATED = VersionNumber(
                        versionNumberString: 'v${BUILD_YEAR, XX}.${BUILD_MONTH, XX}${BUILD_DAY, XX}.${BUILDS_TODAY}',
                        projectStartDate:    '1970-01-01',
                        skipFailedBuilds:    true)
                    currentBuild.displayName = BUILD_VERSION_GENERATED
                    env.BUILD_VERSION = BUILD_VERSION_GENERATED
                    env.BUILD = 'true'
                }
            }
        }
        stage('Build') {
            when {
                expression {
                    // Skip build when a specific version is provided
                    return !params.BUILD_VERSION
                }
            }
            steps {
                   withEnv([
                      "IMAGE_NAME=medtagger_web",
                       "BUILD_VERSION=" + (params.BUILD_VERSION ?: env.BUILD_VERSION)
                   ]) {
                        checkout scm
                          // sh 'mvn clean install'
                           script {
                              
                           // See: https://jenkins.io/doc/book/pipeline/docker/#building-containers
                      
                              docker.withRegistry("https://registry-1.docker.io/v2/", "e84699be-4f1d-409f-9bd9-9012dc426603") {
                                 def image = docker.build(
                                             "ymnliu/${env.IMAGE_NAME}:${env.BUILD_VERSION}",
                                             "--no-cache ."
                                              )
                           // Push the image to the registry
                                  image.push("${env.BUILD_VERSION}")
                        }
                    }
                }
            }
        }
        stage('deploy docker') {
            agent {
                node { label 'medtag-dev-ec2-02'}
            }
            steps {
                cleanWs()
                checkout scm
                configFileProvider([
                    configFile(fileId: 'medtaggerweb-dev-docker-compose', targetLocation: 'docker-compose.yml')     
                ]) {    
                   script {
                        sh '''
                            docker-compose -p $PROJECT_NAME down -v --rmi all | xargs echo
                            docker pull $DOCKER_REPO_NAME:$BUILD_VERSION
                            docker rmi $DOCKER_REPO_NAME:current | xargs echo
                            docker tag $DOCKER_REPO_NAME:$BUILD_VERSION $DOCKER_REPO_NAME:current
                            docker-compose -p $PROJECT_NAME up -d
                            docker rmi \$(docker images -aq) | xargs echo
                            '''
                    }
                }
            }
        }
    }
}
