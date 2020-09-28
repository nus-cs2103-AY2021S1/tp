package tp.cap5buddy.modules;

/**
 * Represents the Module creation class.
 */
public class Module {
    private final String name;
    private final String link;

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param link zoom link attached to module
     */
    public Module(String name, String link) {
        this.name = name;
        this.link = link;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     */
    public Module(String name) {
        this.name = name;
        this.link = null;
    }

    /**
     * Returns the module name.
     * @return String module name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the zoom link of the module.
     * @return String zoom link.
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Sets the zoom link for that module.
     * @param link zoom link.
     * @return Module a new Module.
     */
    public Module setLink(String link) {
        return new Module(this.getName(), link);
    }
}
