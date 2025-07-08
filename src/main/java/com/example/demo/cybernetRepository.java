@Repository
public class cybernetRepository {
    @NotEmpty
    private String name;
    private String description;
    private String url;
    private String type;
    private String version;

    public cybernetRepository(String name, String description, String url, String type, String version) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.type = type;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

}