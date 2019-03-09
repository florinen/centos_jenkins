import hudson.EnvVars

def app


node {

  checkout scm

  stage('New release GIT') {

    // Get latest release from local git
    env.build = sh returnStdout: true, script: 'git describe --abbrev=0 --tags'
  }

  stage('Build docker image') {

    // Build the image
      app = docker.build("fsadykov/centos_jenkins", "-f ${WORKSPACE}/Dockerfile/Dockerfile .")
  }

  stage('Push image') {

     // Push image to the Nexus with new release
      docker.withRegistry('', 'dockerhub-credentials') {
          app.push("${env.build}")
          app.push("latest")
      }
  }
}
