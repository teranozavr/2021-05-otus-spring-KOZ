package learning.spring.caterpillar;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CaterpillarApplicationTests {

	@Configuration
	static class TestConfiguration {
	}

	@Test
	void contextLoads() {
		assertNotNull(new AnnotationConfigApplicationContext( CaterpillarSpringBootApplication.class ).getBean("caterpillarChannel"));
	}

}
