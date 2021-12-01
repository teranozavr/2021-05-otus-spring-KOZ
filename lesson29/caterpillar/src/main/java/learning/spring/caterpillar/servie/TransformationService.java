package learning.spring.caterpillar.servie;

import learning.spring.caterpillar.domain.Butterfly;
import learning.spring.caterpillar.domain.Caterpillar;
import org.springframework.stereotype.Component;

@Component
public class TransformationService {
    public Butterfly transformation(Caterpillar caterpillar) throws Exception {
        System.out.println("Гусеница " + caterpillar.getName() + " начала превращение");
        Thread.sleep(3000);
        System.out.println("Гусеница " + caterpillar.getName() + " превратилась в бабочку");
        return new Butterfly(caterpillar.getName());
    }
}
