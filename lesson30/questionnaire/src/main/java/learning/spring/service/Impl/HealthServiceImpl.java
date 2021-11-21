package learning.spring.service.Impl;

import learning.spring.service.HealthService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService, HealthIndicator {

    private boolean serviceIsDown = false;

    @Override
    public Health health() {
        if(serviceIsDown) {
            return Health.down()
                    .status(Status.DOWN)
                    .build();
        }
        return Health.up().build();
    }

    public void setHealth(boolean isServiceDown){
        serviceIsDown = isServiceDown;
    }
}
