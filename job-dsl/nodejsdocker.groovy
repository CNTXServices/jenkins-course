job('NodeJS Docker example') {
    scm {
        git('https://github.com/CNTXServices/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('patrick')
            node / gitConfigEmail('jenkins-dsl@patrick.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('patrickpeter0101/nodejs-docker')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
