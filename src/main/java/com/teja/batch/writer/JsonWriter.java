package com.teja.batch.writer;

import com.teja.batch.domain.Book;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class JsonWriter {
  @Bean
  public JsonFileItemWriter<Book> personJsonFileItemWriter() {

    return new JsonFileItemWriterBuilder<Book>()
        .name("personJsonFileItemWriter")
        .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
        .resource(
            (WritableResource)
                new PathMatchingResourcePatternResolver().getResource("file:output.json"))
        .build();
  }
}
