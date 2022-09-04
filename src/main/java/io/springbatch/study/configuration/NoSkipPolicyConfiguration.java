package io.springbatch.study.configuration;

import io.springbatch.study.SkipItemProcessor;
import io.springbatch.study.SkipItemWriter;
import io.springbatch.study.exception.NoSkipException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NoSkipPolicyConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(new ItemReader<String>() {
                    int i = 0;

                    @Override
                    public String read() throws NoSkipException {
                        i++;
                        if (i == 3) {
                            throw new NoSkipException("skip");
                        }
                        System.out.println("ItemReader : " + i);
                        return i > 20 ? null : String.valueOf(i);
                    }
                })
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .noSkip(NoSkipException.class)
                .skipLimit(3)
                .build();
    }

    @Bean
    public ItemWriter<? super String> writer() {
        return new SkipItemWriter();
    }

    @Bean
    public ItemProcessor<? super String, String> processor() {
        return new SkipItemProcessor();
    }
}
