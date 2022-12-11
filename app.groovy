pipeline {
  stages {
  agent {
    kubernetes {
      yaml """
kind: Pod
spec:
  containers:
  - name: example-image
    image: nginx:${params.AGENT_POD_SPEC}
    imagePullPolicy: Always
    command:
    - ls
"""
    }
  }
  parameters {
    choice(
      name: 'AGENT_POD_SPEC',
      choices: ['latest','1.3.0','1.2.0','1.4.0'],
      description: 'Agent pod configuration'
    )
  }
  }
}
