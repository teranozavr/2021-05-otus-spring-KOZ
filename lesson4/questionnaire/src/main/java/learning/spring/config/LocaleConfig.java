package learning.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties(prefix = "locale")
public class LocaleConfig {
    private String lang;
    private String country;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Locale getLocale() {
        return (lang.isEmpty() && country.isEmpty()) ? Locale.ROOT : new Locale(lang, country);
    }
}
