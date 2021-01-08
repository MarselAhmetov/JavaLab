package team404.annotation;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormService {
    public static void processForm(Path out, String method, String action, List<HtmlInputDto> fields) throws IOException, TemplateException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
        Configuration configuration = new Configuration(new Version("2.3.23"));

        configuration.setClassForTemplateLoading(HtmlProcessor.class, "/");
        configuration.setDefaultEncoding("UTF-8");

        Template template = configuration.getTemplate("form.ftl");

        Map<String, Object> params = new HashMap<>();
        params.put("action", action);
        params.put("method", method);
        params.put("htmlInputs", fields);

        template.process(params, writer);
        writer.close();
    }
}
