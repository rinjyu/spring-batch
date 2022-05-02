package io.springbatch.study.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobParameterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        JobParameters jobParameters1 = stepContribution.getStepExecution().getJobExecution().getJobParameters();
                        String name1 = jobParameters1.getString("name");
                        long seq1 = jobParameters1.getLong("seq");
                        Date date1 = jobParameters1.getDate("date");
                        double age1 = jobParameters1.getDouble("age");

                        System.out.println("===========================");
                        System.out.println("name1 :" + name1);
                        System.out.println("seq1 : " + seq1);
                        System.out.println("date1 : " + date1);
                        System.out.println("age1 : " + age1);
                        System.out.println("===========================");

                        Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();
                        String name2 = (String)jobParameters2.get("name");
                        long seq2 = (long)jobParameters2.get("seq");
                        Date date2 = (Date) jobParameters2.get("date");
                        double age2 = (double) jobParameters2.get("age");

                        System.out.println("===========================");
                        System.out.println("name2 :" + name2);
                        System.out.println("seq2 : " + seq2);
                        System.out.println("date2 : " + date2);
                        System.out.println("age2 : " + age2);
                        System.out.println("===========================");

                        System.out.println(">> step1 <<");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(">> step2 <<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
