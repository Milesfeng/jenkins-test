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
          shell """#!/bin/bash
echo 'GIT_COMMIT:' $GIT_COMMIT
echo 'CHANGE_TITLE:' $CHANGE_TITLE
echo 'CHANGE_AUTHOR:' $CHANGE_AUTHOR
echo 'CHANGE_AUTHOR_EMAIL:' $CHANGE_AUTHOR_EMAIL
echo 'CHANGE_ID:' $CHANGE_ID
"""
      }
    }    
  }
}
