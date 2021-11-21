package learning.spring.service.Impl;

import learning.spring.service.ExceptionPrinterService;
import learning.spring.service.ExceptionProcessor;
import org.springframework.stereotype.Service;

@Service
public class ExceptionProcesorImpl implements ExceptionProcessor {
    private final ExceptionPrinterService exceptionPrinterService;
    private final HealthServiceImpl healthService;

    public ExceptionProcesorImpl(ExceptionPrinterService exceptionPrinterService, HealthServiceImpl healthService) {
        this.exceptionPrinterService = exceptionPrinterService;
        this.healthService = healthService;
    }

    public void processException(Exception exception){
        exceptionPrinterService.printException(exception);
        healthService.setHealth(true);
    }
}
