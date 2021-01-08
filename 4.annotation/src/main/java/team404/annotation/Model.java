package team404.annotation;

@HtmlForm(method = "post", action = "/model")
public class Model {
    @HtmlInput(type = "text", name = "names", placeholder = "Name")
    private String name;
    @HtmlInput(type = "text", name = "text", placeholder = "Text")
    private String text;
    @HtmlInput(type = "number", name = "number", placeholder = "Number")
    private String number;
    @HtmlInput(type = "password", name = "password", placeholder = "Password")
    private String password;
}
