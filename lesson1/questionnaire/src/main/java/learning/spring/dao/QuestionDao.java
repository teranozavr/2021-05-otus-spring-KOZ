package learning.spring.dao;
import learning.spring.domain.*;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getData() throws IOException;
}
