package com.learn.jpa.springBatch.Utility;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);


    @Override
    public void beforeJob(JobExecution jobExecution){
        log.info("The job has started");
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("The job has been completed successfully!");
        }
    }
}
