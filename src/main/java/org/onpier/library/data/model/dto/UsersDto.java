package org.onpier.library.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/*
this class is as DTO for return result Users entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    String firstName;
    String lastName;
    LocalDate memberSince;
    LocalDate memberTill;
    String gender;
}
