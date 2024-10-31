node('java') {
    stage('checkout') {
        git branch: 'main', url: 'https://github.com/sudheer76R/java-example.git'
    }

    stage('test') {
        echo 'running unit test and integration test'
    }

    stage('build') {
        sh 'mvn clean package'
    }

    stage('deploy') {
        try {
            sh "sudo rsync -av /home/ubuntu/jenkins/workspace/'assign-2-deploy tomcat'/target/works-with-heroku-1.0.war /home/ubuntu/tomcat/webapps/"
            sh "/home/ubuntu/tomcat/bin/shutdown.sh"
            sh "/home/ubuntu/tomcat/bin/startup.sh"
            echo 'Successfully deployed'
        } catch (Exception e) {
            error "Deployment failed: ${e.message}"
        }
    }
}
