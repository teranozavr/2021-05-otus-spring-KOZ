package learning.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "questions.file")
@Component
public class QuestionsConfig {
    private String path;
    private String template;
    private String extension;

    public String getPath() {
        return this.path;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
