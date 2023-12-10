package org.onpier.library.data.model;



import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
This class is for creating a table "users" by JPA
 */
@Table(name = "books")
@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Books {
    @Id
    Integer id;
    String title;
    String author;
    String genre;
    String publisher;
}
