package com.teja.batch.processor;

import com.teja.batch.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class JsonProcessor implements ItemProcessor<Book, Book> {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonProcessor.class);

  @Override
  public Book process(Book book) throws Exception {
    LOGGER.info("Processing person information: {}", book);
    return book;
  }
}
