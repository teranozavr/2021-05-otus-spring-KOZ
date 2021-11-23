package learning.spring.service.Impl;

import learning.spring.service.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

        private final MessageSource messageSource;

        public MessageServiceImpl(MessageSource messageSource) {
            this.messageSource = messageSource;
        }

        public String getMessage(String message) {
            return messageSource.getMessage(message, null, Locale.getDefault());
        }
}
