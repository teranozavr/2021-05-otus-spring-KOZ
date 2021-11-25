package learning.spring.service.Impl;

import learning.spring.config.LocaleConfig;
import learning.spring.service.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

        private final MessageSource messageSource;
        private final LocaleConfig localeConfig;

        public MessageServiceImpl(MessageSource messageSource, LocaleConfig localeConfig) {
            this.messageSource = messageSource;
            this.localeConfig = localeConfig;
        }

        public String getMessage(String message) {
            return messageSource.getMessage(message, null, localeConfig.getLocale());
        }
}
