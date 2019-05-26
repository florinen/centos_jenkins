import hudson.EnvVars

def app


properties([
  parameters([
    booleanParam(defaultValue: false,
      description: 'Click this if you would like to deploy to latest', name: 'PUSH_LATEST'
      )])])


node {

  // Pooling the docker image
  checkout scm

  stage('Build docker image') {

      // Build the docker image
      app = docker.build("fsadykov/centos_jenkins", "-f ${WORKSPACE}/Dockerfile/Dockerfile .")
  }


  stage('Push image') {

     // Push docker image to the Docker hub
      docker.withRegistry('', 'dockerhub-credentials') {
          app.push("kube-v0.${BUILD_NUMBER}")

          // If push to latest parameters selected
          if (params.PUSH_LATEST){
            app.push("kube")
          }
      }
  }
}
