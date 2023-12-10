package org.onpier.library.controller;

import lombok.extern.slf4j.Slf4j;

import org.onpier.library.data.model.dto.BooksDto;
import org.onpier.library.data.model.dto.UsersDto;
import org.onpier.library.data.repository.RepositoryNativeQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/Library/")
@Slf4j
public class LibraryController {
    private final RepositoryNativeQuery repositoryNativeQuery;

    public LibraryController(RepositoryNativeQuery repositoryNativeQuery) {
        this.repositoryNativeQuery = repositoryNativeQuery;
    }

    @GetMapping("/getUserAtLeastOneBorrowed")
    public ResponseEntity<List<UsersDto>> getUserAtLeastOneBorrowed() {
        try {
            return ResponseEntity.ok().body(repositoryNativeQuery.findUserAtLeastOneBorrowed());
        } catch (Exception e) {
            log.error(" Exception {} ", e);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getUserWithoutBorrowed")
    public ResponseEntity<List<UsersDto>> getUserWithoutBorrowed() {
        try {
            return ResponseEntity.ok().body(repositoryNativeQuery.findUserWithoutBorrowed());
        } catch (Exception e) {
            log.error( "Exception {}" , e);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getUserBorrowedGivenDate/{date}")
    public ResponseEntity<List<UsersDto>> getUserBorrowedGivenDate(
            @PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            return ResponseEntity.ok().body(repositoryNativeQuery.findUserBorrowedGivenDate(date));
        } catch (Exception e) {
            log.error(" Exception {} ", e);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getUserBorrowedGivenDateByRang/{userId}/{start}/{end}")
    public ResponseEntity<List<BooksDto>> getUserBorrowedGivenDateByRange(
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @PathVariable(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            return ResponseEntity.ok().body(repositoryNativeQuery.findUserBorrowedGivenDateByRang(userId,fromDate, toDate));
        } catch (Exception e) {
            log.error( "Exception {} ", e);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getBookNotBorrowed")
    public ResponseEntity<List<BooksDto>> getBookNotBorrowed() {
        try {
            List<BooksDto> books = repositoryNativeQuery.findBookNotBorrowed();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            log.error( "Exception {}" , e);
            return ResponseEntity.noContent().build();
        }
    }

}
