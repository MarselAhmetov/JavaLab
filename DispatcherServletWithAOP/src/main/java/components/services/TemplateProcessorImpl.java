package components.services;

import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

@Component
public class TemplateProcessorImpl implements TemplateProcessor {
    Configuration cfg = new Configuration();

    public TemplateProcessorImpl() {
        cfg.setClassForTemplateLoading(TemplateProcessorImpl.class, "ftl");
        cfg.setClassForTemplateLoading(TemplateProcessor.class, "/");
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    }

    public String processTemplate(String templateName, Map<String, Object> input) {
        try {
            Template template = cfg.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(input, writer);
            writer.flush();
            return writer.toString();
        } catch (TemplateException | IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }
}
