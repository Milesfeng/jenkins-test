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
    stage('Get Changes') {
        steps {
            script {

                if (currentBuild.changeSets.size() == 0) {
                    echo "No changes detected in this build."
                } else {
                    echo "Changes detected:"
                    for (changeSet in currentBuild.changeSets) {
                        for (entry in changeSet.items) {
                            echo "Commit by ${entry.author}: ${entry.msg}"
                            echo "Commit ID: ${entry.commitId}"

                            for (file in entry.affectedFiles) {
                                echo "Modified file: ${file.path}"
                            }
                        }
                    }
                }
            }
        }
    }    
  }
}
