package tp.cap5buddy.modules;

public class Module {
    private final String name;
    private final String link;

    public Module(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Module(String name) {
        this.name = name;
        this.link = null;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }

    public Module setLink(String link) {
        return new Module(this.getName(), link);
    }
}
