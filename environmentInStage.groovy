pipeline {
  agent any

  environment {
    // FOO will be available in entire pipeline
    FOO = "PIPELINE "
  }

  stages {
    // stage("local") {
    //   environment {
    //     // BAR will only be available in this stage
    //     BAR = "STAGE"
    //   }
    //   steps {
    //     sh 'echo "FOO is $FOO and BAR is $BAR"'
    //   }
    // }
    // stage("global") {
    //   steps {
    //     sh 'echo "FOO is $FOO and BAR is $BAR"'
    //   }
    // }
    // stage("pr-test") {
    //   steps {
    //       sh 'echo "GIT_COMMIT: $GIT_COMMIT"'
    //       sh 'echo "GIT_COMMITTER_EMAIL: $GIT_COMMITTER_EMAIL"'
    //       sh 'echo "GIT_AUTHOR_EMAIL: $GIT_AUTHOR_EMAIL"'
    //       sh 'echo "CHANGE_TITLE: $CHANGE_TITLE"'
    //       sh 'echo "GITHUB_PR_TITLE: $GITHUB_PR_TITLE"'
    //       sh 'echo "GITHUB_PR_TRIGGER_SENDER_EMAIL: $GITHUB_PR_TRIGGER_SENDER_EMAIL"'
    //       sh 'echo "GITHUB_PR_COMMIT_AUTHOR_EMAIL: $GITHUB_PR_COMMIT_AUTHOR_EMAIL"'
    //       sh 'echo "GITHUB_PR_HEAD_SHA: $GITHUB_PR_HEAD_SHA"'
    //       sh 'echo "CHANGE_ID: $CHANGE_ID"'
    //       sh 'echo "BRANCH_NAME: $BRANCH_NAME"'
    //   }
    // }
    // stage('Get Changes') {
    //     steps {
    //         script {

    //             if (currentBuild.changeSets.size() == 0) {
    //                 echo "No changes detected in this build."
    //             } else {
    //                 echo "Changes detected:"
    //                 for (changeSet in currentBuild.changeSets) {
    //                     for (entry in changeSet.items) {
    //                         echo "Commit by ${entry.author}: ${entry.msg}"
    //                         echo "Commit ID: ${entry.commitId}"

    //                         for (file in entry.affectedFiles) {
    //                             echo "Modified file: ${file.path}"
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

    stage('Print Build Causes') {
        steps {
          script {
              def buildCauseDescription = currentBuild.getBuildCauses()[0].shortDescription
              def user = sh(script: "echo '${buildCauseDescription}' | awk '{print \$NF}'", returnStdout: true).trim()
              echo "${user}"
            
              if (currentBuild.changeSets) {
                  echo "Change Sets:"
                  currentBuild.changeSets.each { changeSet ->
                      changeSet.items.each { change ->  
                          echo "Commit ID: ${change.commitId}"
                          echo "Author: ${change.author?.fullName}"
                          echo "Message: ${change.msg}"
                      }
                  }
              } else {
                  echo "No changes detected."
              }            
          }

        }
    }
 
  }
}
