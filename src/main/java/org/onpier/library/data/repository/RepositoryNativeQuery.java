package org.onpier.library.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.onpier.library.data.model.dto.BooksDto;
import org.onpier.library.data.model.dto.UsersDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
this repository is for executing native query by JdbcTemplate
 */
@Repository
@Slf4j
public class RepositoryNativeQuery {
    private final JdbcTemplate jdbcTemplate;


    public RepositoryNativeQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UsersDto> findUserAtLeastOneBorrowed() {
        String query = "select distinct  usr.first_name ,usr.last_name , " +
                " usr.member_since,usr.member_till,usr.gender " +
                " from public.users usr " +
                " join public.borrowed bor on bor.users_id = usr.id; ";
        return executeQueryForUserDto(query);
    }

    public List<UsersDto> findUserWithoutBorrowed() {
        String query = "select  usr.first_name ,usr.last_name , " +
                " usr.member_since,usr.member_till,usr.gender  " +
                " from public.users usr  " +
                " left join public.borrowed bor on bor.users_id = usr.id " +
                " where bor.id is null ";
        return executeQueryForUserDto(query);
    }

    public List<UsersDto> findUserBorrowedGivenDate(LocalDate date) {
        String query = " select distinct usr.first_name ,usr.last_name , " +
                " usr.member_since,usr.member_till,usr.gender  " +
                " from public.users usr   " +
                " join public.borrowed bor on bor.users_id = usr.id  " +
                " where borrowed_from = '%s'; ";
        query = query.formatted(date);
        return executeQueryForUserDto(query);
    }

    public List<BooksDto> findUserBorrowedGivenDateByRang(int users_id, LocalDate startDate, LocalDate endDate) {
        String query = "select b.title, b.author, b.genre, b.publisher  from public.users usr  " +
                " join public.borrowed bor on bor.users_id = usr.id  " +
                " join books b on b.id=bor.book_id " +
                "  where borrowed_from  between '%s' and '%s' and users_id= %s  ";
        query = query.formatted(startDate, endDate, users_id);

        return executeQueryForBooksDto(query);
    }

    public List<BooksDto> findBookNotBorrowed() {
        String query = "select b.title, b.author, b.genre, b.publisher from public.books b " +
                " left join public.borrowed bor on bor.book_id = b.id " +
                " where bor.book_id is null ";
        return executeQueryForBooksDto(query);
    }

    private List<UsersDto> executeQueryForUserDto(String query) {
        List<UsersDto> usersDtoList = new ArrayList<>();

        try {
            jdbcTemplate.query(query,
                    resultSet -> {
                        UsersDto usersDto = new UsersDto();
                        usersDto.setFirstName(resultSet.getString(1));
                        usersDto.setLastName(resultSet.getString(2));
                        usersDto.setMemberSince(resultSet.getDate(3).toLocalDate());
                        usersDto.setMemberTill(resultSet.getDate(4) != null ? resultSet.getDate(4).toLocalDate() : null);
                        usersDto.setGender(resultSet.getString(5));
                        usersDtoList.add(usersDto);
                    });

        } catch (Exception exception) {
            log.error(" {0} ", exception);
        }
        return usersDtoList;
    }

    private List<BooksDto> executeQueryForBooksDto(String query) {
        List<BooksDto> booksDtoList = new ArrayList<>();
        try {
            jdbcTemplate.query(query,
                    resultSet -> {
                        BooksDto booksDto = new BooksDto();
                        booksDto.setTitle(resultSet.getString(1));
                        booksDto.setAuthor(resultSet.getString(2));
                        booksDto.setGenre(resultSet.getString(3));
                        booksDto.setPublisher(resultSet.getString(4));
                        booksDtoList.add(booksDto);
                    });

        } catch (Exception exception) {
            log.error(" {0} ", exception);
        }
        return booksDtoList;
    }
}
