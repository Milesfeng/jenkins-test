pipeline { 
    agent any 
    stages {
        stage('Build') { 
            steps { 
                echo " ***Build***"
                echo $ghprbActualCommit
                echo $ghprbActualCommitAuthor
                echo $ghprbActualCommitAuthorEmail
                echo $ghprbPullDescription
                echo $ghprbPullId
                echo $ghprbPullLink
                echo $ghprbPullTitle
                echo $ghprbSourceBranch
                echo $ghprbTargetBranch
                echo $ghprbCommentBody
                echo $sha1
            }
        }
        stage('Test'){
            steps {
                echo "***Test***"
            }
        }
        stage('Deploy') {
            steps {
                echo "***Deploy***"
            }
        }
    }
}
