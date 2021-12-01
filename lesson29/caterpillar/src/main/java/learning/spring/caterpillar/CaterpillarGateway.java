package learning.spring.caterpillar;

import learning.spring.caterpillar.domain.Butterfly;
import learning.spring.caterpillar.domain.Caterpillar;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@MessagingGateway
public interface CaterpillarGateway {

    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
    Collection<Butterfly> process(Collection<Caterpillar> item);
}
