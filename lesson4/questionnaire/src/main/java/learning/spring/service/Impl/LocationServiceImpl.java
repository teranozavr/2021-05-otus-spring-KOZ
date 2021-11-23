package learning.spring.service.Impl;

import learning.spring.service.LocationService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocationServiceImpl implements LocationService {
    private final MessageSource messageSource;

    public LocationServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getLocation() {
        return messageSource.getMessage("location", null, Locale.getDefault());
    }
}
