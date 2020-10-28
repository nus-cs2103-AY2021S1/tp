package seedu.address.testutil;

import java.util.Set;

import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;



/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULENAME = "CS2103T";
    public static final String DEFAULT_ZOOMLINK = "www.zoom.us";
    public static final double DEFAULT_MODULARCREDITS = 4.0;

    private ModuleName moduleName;
    private ZoomLink zoomLink;
    private ModularCredits modularCredits;
    private GradeTracker gradeTracker;
    private Set<Tag> tags;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULENAME);
        zoomLink = new ZoomLink(DEFAULT_ZOOMLINK);
        modularCredits = new ModularCredits(DEFAULT_MODULARCREDITS);
        gradeTracker = new GradeTracker();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(String zoomLink) {
        this.zoomLink = new ZoomLink(zoomLink);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModularCredits(double modularCredits) {
        this.modularCredits = new ModularCredits(modularCredits);
        return this;
    }
    /**
     * Sets the {@code ModuleCredits} of the {@code Module} that we are building.
     */
    public ModuleBuilder withMC(double value) {
        this.modularCredits = new ModularCredits(value);
        return this;
    }
    public Module build() {
        return new Module(moduleName, zoomLink, modularCredits);
    }



}
