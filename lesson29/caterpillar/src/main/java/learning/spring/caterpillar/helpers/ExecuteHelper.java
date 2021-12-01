package learning.spring.caterpillar.helpers;

import learning.spring.caterpillar.CaterpillarGateway;
import learning.spring.caterpillar.domain.Butterfly;
import learning.spring.caterpillar.domain.Caterpillar;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static learning.spring.caterpillar.helpers.ItemGeneratorHelper.generateSubSequence;
import static learning.spring.caterpillar.helpers.NameHelper.getCaterpillarNames;

public class ExecuteHelper {

    public static void execute(ForkJoinPool forkJoinPool , CaterpillarGateway caterpillarGateway) throws InterruptedException {
        while ( true ) {
            Thread.sleep( 7000 );

            forkJoinPool.execute( () -> {
                Collection<Caterpillar> items = generateSubSequence(getCaterpillarNames());
                System.out.println( "Гусеницы: " +
                        items.stream().map( Caterpillar::getName )
                                .collect( Collectors.joining( "," ) ) );
                Collection<Butterfly> butterflies = caterpillarGateway.process( items );
                System.out.println( "Бабочки: " + butterflies.stream()
                        .map( Butterfly::getName )
                        .collect( Collectors.joining( "," ) ) );
            } );
        }
    }
}
