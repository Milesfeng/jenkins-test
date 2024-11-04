pipeline {
  agent any

  environment {
    // FOO will be available in entire pipeline
    FOO = "PIPELINE "
  }

  stages {
    stage("local") {
      environment {
        // BAR will only be available in this stage
        BAR = "STAGE"
      }
      steps {
        sh 'echo "FOO is $FOO and BAR is $BAR"'
      }
    }
    stage("global") {
      steps {
        sh 'echo "FOO is $FOO and BAR is $BAR"'
      }
    }
    stage("pr-test") {
      steps {
          sh 'echo "GIT_COMMIT: $GIT_COMMIT"'
          sh 'echo "GIT_COMMITTER_EMAIL: $GIT_COMMITTER_EMAIL"'
          sh 'echo "GIT_AUTHOR_EMAIL: $GIT_AUTHOR_EMAIL"'
          sh 'echo "CHANGE_TITLE: $CHANGE_TITLE"'        
      }
    }    
  }
}
