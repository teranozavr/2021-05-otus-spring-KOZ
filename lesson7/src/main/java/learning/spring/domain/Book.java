package learning.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {

    private final long id;
    private final long authorId;
    private final long genreId;
    private final String title;
}
