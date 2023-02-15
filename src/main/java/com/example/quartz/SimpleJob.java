package com.example.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class SimpleJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(SimpleJob.class);
    @Override
    public void execute(JobExecutionContext context) {
        logger.info("start : {}", LocalDateTime.now());
//        try {
//            Thread.sleep(5 * 1000);
//            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//            String param = dataMap.getString("param");
//            logger.info("Job: {}; Param: {}; Thread: {}", getClass().getSimpleName(), param, Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
       logger.info("end : {}", LocalDateTime.now());
    }


}
