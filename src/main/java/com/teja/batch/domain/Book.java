package com.teja.batch.domain;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer serialNumber;
    private String bookName;
}
