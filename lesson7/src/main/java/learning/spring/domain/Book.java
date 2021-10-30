package learning.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {

    private final long id;
    private final Author author;
    private final Genre genre;
    private final String title;
}
