def choiceArray = []
agent {
    kubernetes {
        yaml '''
apiVersion: v1
kind: Pod
metadata:
spec:
    containers:
    - name: $label
    image: busybox
    command:
    - ls
    tty: true
    imagePullPolicy: Always
    volumeMounts:
    - mountPath: /var/run/docker.sock
        name: docker-sock
    volumes:
    - name: docker-sock
    hostPath:
        path: /var/run/docker.sock
'''
        }
    }

pipeline {
    agent any;
    parameters { string(name: "branch", defaultValue: 'main')}
    stages {
        stage('debug') {
            steps {
                echo "Selected choice is : ${params.branch}"
            }
        }
    }
}

