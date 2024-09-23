package com.teja.batch.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teja.batch.domain.Book;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class JsonReader {
  @Value("classpath:book.json")
  private Resource jsonFile;

  @Bean
  @StepScope
  public JsonItemReader<Book> bookJsonItemReader() {

    ObjectMapper objectMapper = new ObjectMapper();
    JacksonJsonObjectReader<Book> jsonObjectReader =
            new JacksonJsonObjectReader<>(
                    objectMapper.setDefaultPrettyPrinter(
                            objectMapper
                                    .writerWithDefaultPrettyPrinter()
                                    .getConfig()
                                    .getDefaultPrettyPrinter()),
                    Book.class);

    return new JsonItemReaderBuilder<Book>()
            .name("bookJsonItemReader")
            .jsonObjectReader(jsonObjectReader)
            .resource(jsonFile)
            .build();
  }
}
