podTemplate(containers: [
    containerTemplate(
        name: 'maven', 
        image: 'maven-jdk:latest', 
        command: 'sleep', 
        args: '99d'
        )
  ]) {
  node (POD_LABEL) {
    stage('Check Maven. Java Version: 8') {
          sh 'echo Before Maven Java 8'
          container('mavenjdk8'){
                sh 'mvn -v'
                sh 'java -version'
           }
      }
	stage ('SCM checkout'){
		git branch: 'main', credentialsId: 'mygithub', url: 'https://github.com/shree665/selenium-test.git'
	}
	stage ('Build'){
	  container('maven') {
	    sh "mvn clean install"
       }
	}
  }
}