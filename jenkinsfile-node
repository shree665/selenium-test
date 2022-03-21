node {
	stage ('SCM checkout'){
		git "https://github.com/shree665/selenium-test"
		}
	stage ('Build'){
    	dir("comtest") {
	   		sh "mvn clean install"
       }
       	dir("comtest/target") {
	   		sh "java -jar test-0.0.1-SNAPSHOT.jar"
       	}
	}
}