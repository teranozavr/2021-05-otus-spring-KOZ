package learning.spring.service;

import org.springframework.boot.actuate.health.Health;

public interface HealthService {
    public Health health();
    void setHealth(boolean isServiceDown);
}
