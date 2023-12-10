package org.onpier.library.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
this class is as DTO for return result Books entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksDto {
    String title;
    String author;
    String Genre;
    String Publisher;
}
