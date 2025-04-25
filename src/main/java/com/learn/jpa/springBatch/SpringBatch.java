package com.learn.jpa.springBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBatch  implements CommandLineRunner {

    private final JobLauncher jobLauncher;

    @Autowired
    @Qualifier("personJob")
    private  Job personJob;

	public static void main(String[] args) {
        SpringApplication.run(SpringBatch.class, args);
        System.out.print("hello world");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this job has started");
//        jobLauncher.run(personJob,
//                new JobParametersBuilder()
//                        .addLong("time", System.currentTimeMillis())
//                        .toJobParameters());
    }
}
