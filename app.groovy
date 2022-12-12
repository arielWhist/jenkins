parameters {
    stringParam(name: "branch", defaultValue: 'main')
    choiceParam(name: "env", choices: ["test", "prod"], description: "Env for deployment")
    booleanParam(name: "deploy_archive_scanner", value: false, description: "check the box if you would like to deploy archive scanner lambda")
    booleanParam(name: "deploy_archive_file_extractor", value: false, description: "check the box if you would like to deploy archive file extractor lambda")
}

podTemplate {
    node(POD_LABEL) {
        // pipeline steps...
        print "echo"
    }
}
