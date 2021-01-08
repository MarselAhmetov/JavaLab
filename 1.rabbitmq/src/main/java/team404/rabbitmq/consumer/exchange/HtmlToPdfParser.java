package team404.rabbitmq.consumer.exchange;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;

public class HtmlToPdfParser {
    public void createPdfFromTemplate(String path, Context context, String outputFile) throws IOException, DocumentException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        String k = templateEngine.process(path, context);
        File file = new File(outputFile);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(k));
        document.close();
        outputStream.close();
    }
}
