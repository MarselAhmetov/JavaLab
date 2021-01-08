package team404.annotation;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 30.10.2020
 * 46. Annotations
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"team404.annotation.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                List<HtmlInputDto> fields = element.getEnclosedElements().stream()
                        .filter(x -> ((Element) x).getKind().isField())
                        .filter(x -> ((Element) x).getAnnotation(HtmlInput.class) != null)
                        .map(x -> ((Element) x).getAnnotation(HtmlInput.class))
                        .map(x -> new HtmlInputDto(x.name(), x.type(), x.placeholder()))
                        .collect(Collectors.toList());

                FormService.processForm(out, annotation.method(), annotation.action(), fields);

            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }

}
