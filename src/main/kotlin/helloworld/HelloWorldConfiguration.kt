package helloworld

import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.PassThroughLineMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.nio.charset.StandardCharsets

@Configuration
@EnableBatchProcessing
class HelloWorldConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun helloWorld(): Job {
        return jobBuilderFactory["hello-world"]
            .incrementer(RunIdIncrementer())
            .listener(listener())
            .flow(step1())
            .next(step2())
            .end()
            .build()
    }

    @Bean
    fun listener(): JobExecutionListener {
        return JobStartEndListener()
    }

    // ステップ１
    @Bean
    fun step1(): Step {
        return stepBuilderFactory["step1"]
            .chunk<String, String>(10)
            .reader(helloWorldItemReader())
            .processor(helloWorldItemProcessor())
            .writer(helloWorldItemWriter())
            .listener(chunkListener())
            .listener(stepListener())
            .build()
    }

    // ステップ２
    @Bean
    fun step2(): Step {
        return stepBuilderFactory["step2"]
            .chunk<String, String>(10)
            .reader(helloWorldItemReader())
            .processor(helloWorldItemProcessor())
            .writer(helloWorldItemWriter())
            .listener(chunkListener())
            .listener(stepListener())
            .build()
    }

    @Bean
    fun helloWorldItemReader(): FlatFileItemReader<String> {
        val reader = FlatFileItemReader<String>()

        reader.setLineMapper(PassThroughLineMapper())
        reader.setEncoding(StandardCharsets.UTF_8.name())
        reader.setResource(ClassPathResource("test.txt"))
        return reader
    }

    @Bean
    fun helloWorldItemProcessor(): ItemProcessor<String, String> = HelloWorldItemProcessor()

    @Bean
    fun helloWorldItemWriter(): ItemWriter<String> = HelloWorldItemWriter()

    @Bean
    fun stepListener(): StepExecutionListener = HelloWorldStepExecutionListener()

    @Bean
    fun chunkListener(): ChunkListener = HelloWorldChunkListener()
}
