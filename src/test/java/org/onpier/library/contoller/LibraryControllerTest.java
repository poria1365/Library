package org.onpier.library.contoller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.onpier.library.controller.LibraryController;
import org.onpier.library.data.model.dto.BooksDto;
import org.onpier.library.data.model.dto.UsersDto;
import org.onpier.library.data.repository.RepositoryNativeQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LibraryControllerTest {
    @Mock
    private RepositoryNativeQuery repositoryNativeQuery;
    private LibraryController libraryController;


    @Test
    public void testGetBookNotBorrowed() throws Exception {
        // Arrange
        List<BooksDto> bookDtoList = new ArrayList<>();
        BooksDto bookDto = new BooksDto();
        bookDto.setTitle("Book title");
        bookDto.setAuthor("Author Name");
        bookDtoList.add(bookDto);
        when(repositoryNativeQuery.findBookNotBorrowed()).thenReturn(bookDtoList);
        libraryController = new LibraryController(repositoryNativeQuery);
        // Act
        ResponseEntity<List<BooksDto>> response = libraryController.getBookNotBorrowed();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDtoList, response.getBody());
    }

    @Test
    public void testGetUserAtLeastOneBorrowed() {
        // Arrange
        List<UsersDto> usersDtoList = new ArrayList<>();
        UsersDto usersDto=new UsersDto();
        usersDto.setFirstName("poria");
        usersDto.setLastName("alizadeh");
        usersDtoList.add(usersDto);
        when(repositoryNativeQuery.findUserAtLeastOneBorrowed()).thenReturn(usersDtoList);
        libraryController = new LibraryController(repositoryNativeQuery);
        // Act
        ResponseEntity<List<UsersDto>> response = libraryController.getUserAtLeastOneBorrowed();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersDtoList, response.getBody());
    }

    @Test
    public void testGetUserWithoutBorrowed() {
        // Arrange
        List<UsersDto> usersDtoList = new ArrayList<>();
        UsersDto usersDto=new UsersDto();
        usersDto.setFirstName("poria");
        usersDto.setLastName("alizadeh");
        usersDtoList.add(usersDto);
        when(repositoryNativeQuery.findUserWithoutBorrowed()).thenReturn(usersDtoList);
        libraryController = new LibraryController(repositoryNativeQuery);
        // Act
        ResponseEntity<List<UsersDto>> response = libraryController.getUserWithoutBorrowed();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersDtoList, response.getBody());
    }

    @Test
    public void testGetUserBorrowedGivenDate() {
        // Arrange
        List<UsersDto> usersDtoList = new ArrayList<>();
        UsersDto usersDto=new UsersDto();
        usersDto.setFirstName("poria");
        usersDto.setLastName("alizadeh");
        usersDtoList.add(usersDto);
        when(repositoryNativeQuery.findUserBorrowedGivenDate(LocalDate.now())).thenReturn(usersDtoList);
        libraryController = new LibraryController(repositoryNativeQuery);
        // Act
        ResponseEntity<List<UsersDto>> response = libraryController.getUserBorrowedGivenDate(LocalDate.now());
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersDtoList, response.getBody());
    }

    @Test
    public void testGetUserBorrowedGivenDateByRange() {
        // Arrange
        List<BooksDto> bookDtoList = new ArrayList<>();
        BooksDto bookDto = new BooksDto();
        bookDto.setTitle("Book title");
        bookDto.setAuthor("Author Name");
        bookDtoList.add(bookDto);
        when(repositoryNativeQuery.findUserBorrowedGivenDateByRang(1,LocalDate.now(),LocalDate.now().plusDays(5))).thenReturn(bookDtoList);
        libraryController = new LibraryController(repositoryNativeQuery);
        // Act
        ResponseEntity<List<BooksDto>> response = libraryController.getUserBorrowedGivenDateByRange(1,LocalDate.now(),LocalDate.now().plusDays(5));
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDtoList, response.getBody());
    }
}

