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
    - cat
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
    parameters { choice(name: 'CHOICES', choices: choiceArray, description: 'Please Select One') }
    stages {
        stage('debug') {
            steps {
                echo "Selected choice is : ${params.CHOICES}"
            }
        }
    }
}

