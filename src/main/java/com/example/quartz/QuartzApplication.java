package com.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(QuartzApplication.class, args);
    }
    @Bean()
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean
    public CommandLineRunner run(Scheduler scheduler) {
        return (String[] args) -> {
            JobDetail job = JobBuilder.newJob(SimpleJob.class)
                    .usingJobData("param", scheduler.getSchedulerName()) // add a parameter
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group" )
                    .withDescription("test quartz scheduler")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(30)
                            .withRepeatCount(3))
                    .build();
            scheduler.scheduleJob(job, trigger);
        };
    }

}
