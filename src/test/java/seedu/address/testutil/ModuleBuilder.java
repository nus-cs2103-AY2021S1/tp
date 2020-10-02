package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class ModuleBuilder {
    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_NAME = "Software Engineering";

    private Code code;
    private Name name;
    private Set<Person> persons;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        code = new seedu.address.model.module.Code(DEFAULT_CODE);
        name = new seedu.address.model.module.Name(DEFAULT_NAME);
        persons = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getModuleCode();
        name = moduleToCopy.getModuleName();
        persons = new HashSet<>(moduleToCopy.getPersons());
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new seedu.address.model.module.Code(code);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new seedu.address.model.module.Name(name);
        return this;
    }

    /**
     * Parses the {@code persons} into a {@code Set<Person>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withPersons(Person ... persons) {
        this.persons = SampleDataUtil.getPersonSet(persons);
        return this;
    }

    public Module build() {
        return new Module(code, name, persons);
    }
}
