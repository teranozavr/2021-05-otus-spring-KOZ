package learning.spring.caterpillar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.util.concurrent.ForkJoinPool;

import static learning.spring.caterpillar.helpers.ExecuteHelper.execute;

@IntegrationComponentScan
@Configuration
@EnableIntegration
@SpringBootApplication
public class CaterpillarSpringBootApplication {

	private static CaterpillarGateway caterpillarGateway;
	private static ForkJoinPool forkJoinPool;

	public CaterpillarSpringBootApplication(CaterpillarGateway caterpillarGateway, ForkJoinPool forkJoinPool){
		CaterpillarSpringBootApplication.caterpillarGateway = caterpillarGateway;
		CaterpillarSpringBootApplication.forkJoinPool = forkJoinPool;
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CaterpillarSpringBootApplication.class);
		execute(forkJoinPool, caterpillarGateway);
	}


}