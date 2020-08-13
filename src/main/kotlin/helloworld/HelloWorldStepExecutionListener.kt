package helloworld

import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener

class HelloWorldStepExecutionListener : StepExecutionListener {

    override fun beforeStep(stepExecution: StepExecution) {
        println("StepExecutionListener#beforeStep")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        println("StepExecutionListener#afterStep")
        return null
    }
}
