package helloworld

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport

class JobStartEndListener() : JobExecutionListenerSupport() {

    override fun beforeJob(jobExecution: JobExecution) {
        super.beforeJob(jobExecution)
        println("開始")
    }

    override fun afterJob(jobExecution: JobExecution) {
        super.afterJob(jobExecution)
        println("終了")
    }
}
