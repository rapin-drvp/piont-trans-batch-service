package com.bigc.pointtransbatchservice.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobRunner implements ApplicationRunner {
    @Autowired
    private Job insertPointTransJob;

    @Autowired
    private JobLauncher jobLauncher;
    @Value("${type:#{NULL}}")
    private String type;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Job type JobRunner: {}", type);
        int status = 1;
        try {
            this.jobLauncher.run(insertPointTransJob,
                    new JobParametersBuilder()
                    .addString("TYPE", type)
                    .toJobParameters());

        } catch (Exception e) {
            log.error("Job Runner Exception : ", e);
        }
        log.info("### EXIT STATUS [{}]", status);
        System.exit(status);
    }
}