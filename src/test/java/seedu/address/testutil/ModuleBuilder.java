package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.Name;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ZOOM_LINK = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private ZoomLink zoomLink;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        name = new Name(DEFAULT_NAME);
        zoomLink = new ZoomLink(DEFAULT_ZOOM_LINK);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code ModuleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        name = moduleToCopy.getName();
        zoomLink = moduleToCopy.getZoomLink();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(String ZoomLink) {
        this.zoomLink = new ZoomLink(ZoomLink);
        return this;
    }


    public Module build() {
        return new Module(name, zoomLink);
    }

}
