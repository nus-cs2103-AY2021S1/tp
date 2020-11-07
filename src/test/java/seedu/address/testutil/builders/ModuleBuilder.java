package seedu.address.testutil.builders;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class ModuleBuilder {
    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_NAME = "Software Engineering";

    private ModuleCode moduleCode;
    private ModuleName moduleName;
    private Set<Person> instructors;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_CODE);
        moduleName = new ModuleName(DEFAULT_NAME);
        instructors = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleCode = moduleToCopy.getModuleCode();
        moduleName = moduleToCopy.getModuleName();
        instructors = new HashSet<>(moduleToCopy.getInstructors());
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.moduleCode = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code instructors} into a {@code Set<Person>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withInstructors(Person ... instructors) {
        this.instructors = SampleDataUtil.getPersonSet(instructors);
        return this;
    }

    public Module build() {
        return new Module(moduleCode, moduleName, instructors);
    }
}
