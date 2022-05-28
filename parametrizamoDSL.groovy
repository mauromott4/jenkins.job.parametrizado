job('ejemplo2-job-DSL') {
	description('Job de ejemplo para el curso de Jenkins')
  	scm {
      git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
        node / gitConfigName('mauricio')
        node / gitConfigEmail('mauromott.demo@gmail.com')
      }
    }
    parameters {
  		 stringParam('nombre' , defaultValue = 'Mauricio', description = 'Parametro de cadena para el job Boleano')
         choiceParam('planeta',['Mercurio', 'Venus', 'Jupiter', 'Saturno', 'Urano'])
         booleanParam('agente', false)
    }
    triggers {
        cron('H/7 * * * *')
    }
  	steps {
  	    shell("bash jobscript.sh")
  	}
    publishers {
        mailer('mauromott.demo@gmail.com', true, true)
        slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
    }
}
