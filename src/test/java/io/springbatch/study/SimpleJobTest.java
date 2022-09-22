package io.springbatch.study;

import io.springbatch.study.configuration.SimpleJobConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBatchTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SimpleJobConfiguration.class, TestBatchConfig.class})
public class SimpleJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void simple_job_test() throws Exception {

        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("date", new Date().getTime())
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
//        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

        // then
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        Assert.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);

//        StepExecution stepExecution = (StepExecution) ((List) jobExecution.getStepExecutions()).get(0);
//
//        Assert.assertEquals(stepExecution.getCommitCount(), 11);
//        Assert.assertEquals(stepExecution.getWriteCount(), 1000);
    }

    @After
    public void clear() throws Exception {
//        jdbcTemplate.execute("delete from customer2");
    }
}
