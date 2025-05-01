package com.learn.jpa.springBatch.Configurations;

import com.learn.jpa.springBatch.Processor.PersonProcessor;
import com.learn.jpa.springBatch.Utility.JobCompletionNotificationListener;
import com.learn.jpa.springBatch.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchJobConfig {

   @Autowired
   private JobRepository jobRepository;

   @Autowired
    private DataSource dataSource;

   @Autowired
    public  PlatformTransactionManager platformTransactionManager;

   @Bean
    public FlatFileItemReader<Person> personReader() {
       return new FlatFileItemReaderBuilder<Person>()
               .name("personReader")
               .resource(new ClassPathResource("Person.csv"))
               .delimited()
               .names("firstName", "lastName", "email")
               .targetType(Person.class)
               .build();
   }

   @Bean
    public PersonProcessor personProcessor(){
       return new PersonProcessor();
   }

   @Bean
    public JdbcBatchItemWriter<Person> personWriter(){
       return new JdbcBatchItemWriterBuilder<Person>()
               .sql("INSERT INTO people (firstName, lastName, email) VALUES (:firstName, :lastName, :email)")
               .dataSource(dataSource)
               .beanMapped()
               .build();
   }

   @Bean
    public Step personStep(){
       return new StepBuilder("personStep",jobRepository)
               .<Person,Person>chunk(10,platformTransactionManager)
               .reader(personReader())
               .processor(personProcessor())
               .writer(personWriter())
               .build();
   }

    @Bean
    public Job personJob(JobCompletionNotificationListener listener){
       return new JobBuilder("personJob",jobRepository)
               .incrementer(new RunIdIncrementer())
               .listener(listener)
               .flow(personStep())
               .end()
               .build();
   }



}
