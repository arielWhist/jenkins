parameters {
    stringParam(name: "branch", defaultValue: 'main')
    choiceParam(name: "env", choices: ["test", "prod"], description: "Env for deployment")
    booleanParam(name: "deploy_archive_scanner", value: false, description: "check the box if you would like to deploy archive scanner lambda")
    booleanParam(name: "deploy_archive_file_extractor", value: false, description: "check the box if you would like to deploy archive file extractor lambda")
}

podTemplate(
        cloud: "kubernetes",
        yaml: """\
    apiVersion: v1
    kind: Pod
    metadata:
    spec:
      containers:
      - name: test
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
    """.stripIndent()
) {
    node(label) {
        print ${branch}
    }
}
