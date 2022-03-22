podTemplate(containers: [
    containerTemplate(
        name: 'maven', 
        image: '670274357914.dkr.ecr.us-gov-west-1.amazonaws.com/maven:3.8.4-openjdk8-slim-buster', 
        command: 'sleep', 
        args: '99d'
        )
  ]) {
  node (POD_LABEL) {
    stage('Check Version') {
          sh 'echo Before Maven Java 8'
          container('maven'){
                sh 'mvn -v'
                sh 'java -version'
           }
           sh 'echo After Maven Java 8'
      }
	stage ('SCM checkout'){
		git branch: 'main', credentialsId: 'mygithub', url: 'https://github.com/shree665/selenium-test.git'
	}
	stage ('Build'){
	  container('maven') {
	    sh 'pwd'
	    sh 'ls -lathr'
	    sh 'mvn clean install'
       }
	}
  }
}