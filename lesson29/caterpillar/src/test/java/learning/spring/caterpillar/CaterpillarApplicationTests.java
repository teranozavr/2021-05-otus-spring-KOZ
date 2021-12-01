package learning.spring.caterpillar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CaterpillarApplicationTests {

	@Configuration
	static class TestConfiguration {
	}

	@Test
	void contextLoads() {
		assertNotNull(new AnnotationConfigApplicationContext( CaterpillarApplication.class ).getBean("caterpillarChannel"));
		assertNotNull(new AnnotationConfigApplicationContext( CaterpillarApplication.class ).getBean("butterflyChannel"));
		assertNotNull(new AnnotationConfigApplicationContext( CaterpillarApplication.class ).getBean("cafeFlow"));
	}

}
