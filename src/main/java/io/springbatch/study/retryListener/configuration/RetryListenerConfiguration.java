package io.springbatch.study.retryListener.configuration;

import io.springbatch.study.retryListener.CustomItemProcessor;
import io.springbatch.study.retryListener.CustomItemWriter;
import io.springbatch.study.retryListener.LinkedListItemReader;
import io.springbatch.study.retryListener.exception.CustomRetryException;
import io.springbatch.study.retryListener.listener.CustomRetryListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RetryListenerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Integer, String>chunk(10)
                .reader(listItemReader())
                .processor(new CustomItemProcessor())
                .writer(new CustomItemWriter())
                .faultTolerant()
                .retry(CustomRetryException.class)
                .retryLimit(2)
                .listener(new CustomRetryListener())
                .build();
    }

    @Bean
    public ItemReader<Integer> listItemReader() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        return new LinkedListItemReader<>(list);
    }
}
