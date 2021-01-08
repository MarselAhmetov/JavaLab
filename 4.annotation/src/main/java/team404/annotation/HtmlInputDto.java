package team404.annotation;

public class HtmlInputDto {
    private String name;
    private String type;
    private String placeholder;

    public HtmlInputDto() {
    }

    public HtmlInputDto(String name, String type, String placeholder) {
        this.name = name;
        this.type = type;
        this.placeholder = placeholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
