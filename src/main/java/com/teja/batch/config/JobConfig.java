package com.teja.batch.config;

import com.teja.batch.domain.Book;
import com.teja.batch.processor.JsonProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

  @Bean
  public Job createJob(Step step, JobRepository jobRepository) {
    return new JobBuilder("job", jobRepository).start(step).build();
  }

  @Bean
  public Step createStep(
      JsonItemReader<Book> jsonItemReader,
      JsonFileItemWriter<Book> jsonFileItemWriter,
      JobRepository jobRepository,
      PlatformTransactionManager platformTransactionManager) {
    return new StepBuilder("createStep", jobRepository)
        .<Book, Book>chunk(3, platformTransactionManager)
        .allowStartIfComplete(true)
        .reader(jsonItemReader)
        .processor(new JsonProcessor())
        .writer(jsonFileItemWriter)
        .build();
  }
}
