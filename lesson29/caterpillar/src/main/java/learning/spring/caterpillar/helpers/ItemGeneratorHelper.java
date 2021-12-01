package learning.spring.caterpillar.helpers;

import learning.spring.caterpillar.domain.Caterpillar;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemGeneratorHelper {
    private static  Caterpillar generateCaterpillar(List<String> strings) {

        return new Caterpillar( strings.get( RandomUtils.nextInt(0 ,strings.size())));
    }

    public static Collection<Caterpillar> generateSubSequence(List<String> strings) {
        List<Caterpillar> items = new ArrayList<>();
        for ( int i = 0; i < RandomUtils.nextInt( 1, 5 ); ++ i ) {
            items.add( generateCaterpillar(strings) );
        }
        return items;
    }
}
