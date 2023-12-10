package org.onpier.library.data.repository;


import org.onpier.library.data.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
}
