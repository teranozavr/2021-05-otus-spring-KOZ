package learning.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private final long id;
    private final String firstName;
    private final String surName;
    private final String middleName;
}
