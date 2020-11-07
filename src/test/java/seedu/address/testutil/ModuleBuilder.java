package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_ID = "CS2100";

    private ModuleId moduleId;

    private UniqueTutorialGroupList ugl = new UniqueTutorialGroupList();

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleId = new ModuleId(DEFAULT_MODULE_ID);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleId = moduleToCopy.getModuleId();
    }

    /**
     * Sets the {@code ModuleId} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleId(String moduleId) {
        this.moduleId = new ModuleId(moduleId);
        return this;
    }

    /**
     * Sets the {@code UniqueTutorialGroupList} of the {@code Module} that we are building.
     */
    public ModuleBuilder withUniqueTutorialGroupList(UniqueTutorialGroupList ugl) {
        this.ugl = ugl;
        return this;
    }

    public Module build() {
        return new Module(moduleId, ugl);
    }

}

