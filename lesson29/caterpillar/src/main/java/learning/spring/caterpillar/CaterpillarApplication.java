package learning.spring.caterpillar;

import learning.spring.caterpillar.domain.Butterfly;
import learning.spring.caterpillar.domain.Caterpillar;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static learning.spring.caterpillar.helpers.ItemGeneratorHelper.generateSubSequence;
import static  learning.spring.caterpillar.helpers.NameHelper.getCaterpillarNames;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class CaterpillarApplication {

	public static void main(String[] args) throws InterruptedException {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( CaterpillarApplication.class );
		CaterpillarGateway caterpillarGateway = ctx.getBean( CaterpillarGateway.class );
		ForkJoinPool pool = ForkJoinPool.commonPool();
		execute(pool, caterpillarGateway);
	}

	public static void execute(ForkJoinPool forkJoinPool ,CaterpillarGateway caterpillarGateway) throws InterruptedException {

		while ( true ) {
			Thread.sleep( 7000 );

			forkJoinPool.execute( () -> {
				Collection<Caterpillar> items = generateSubSequence(getCaterpillarNames());
				System.out.println( "Гусеницы: " +
						items.stream().map( Caterpillar::getName )
								.collect( Collectors.joining( "," ) ) );
				Collection<Butterfly> food = caterpillarGateway.process( items );
				System.out.println( "Бабочки: " + food.stream()
						.map( Butterfly::getName )
						.collect( Collectors.joining( "," ) ) );
			} );
		}
	}
}