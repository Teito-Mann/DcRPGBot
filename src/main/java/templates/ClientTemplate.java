package templates;

public class ClientTemplate {
    private String id;
    private String name;
    private String description;
    private String icon;
    private String secret;
    private String[] redirect_urls;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String[] getRedirect_urls() {
        return redirect_urls;
    }
    public void setRedirect_urls(String[] redirect_urls) {
        this.redirect_urls = redirect_urls;
    }
}
