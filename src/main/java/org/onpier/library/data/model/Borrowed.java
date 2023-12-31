package org.onpier.library.data.model;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

/*
This class is for creating a table "borrowed" by JPA
and primary keys for the "users" and "books" tables are foreign keys. It
*/
@Table(name = "borrowed")
@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Borrowed {
    @Id
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    Books books;
    @Column(name = "borrowed_from" , nullable = false)
    LocalDate BorrowedFrom;
    @Column(name = "borrowed_to", nullable = false)
    LocalDate BorrowedTo;

}
