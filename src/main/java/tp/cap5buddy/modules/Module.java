package tp.cap5buddy.modules;

/**
 * Represents the Module creation class.
 */
public class Module {
    private final String name;
    private final String zoomLink;

    /**
     * Represents the module object constructor.
     * @param name name of module
     * @param zoomLink zoom link attached to module
     */
    public Module(String name, String zoomLink) {
        this.name = name;
        this.zoomLink = zoomLink;
    }

    /**
     * Represents the module object constructor.
     * @param name name of module
     */
    public Module(String name) {
        this.name = name;
        this.zoomLink = null;
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
        return this.zoomLink;
    }

    /**
     * Adds the zoom link for this module.
     * @param zoomLink zoom link.
     * @return Module a new Module with the input zoom link.
     */
    public Module addZoomLink(String zoomLink) {
        return new Module(this.getName(), zoomLink);
    }

    @Override
    public String toString() {
        return String.format("The zoom link for %s is %s", getName(), getLink());
    }
}
