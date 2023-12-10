package org.onpier.library.data.repository;


import org.onpier.library.data.model.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Integer> {

}
