package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a module in FaculType.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final Code moduleCode;

    // Data fields
    private final Name moduleName;
    private final Set<Person> persons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Module(Code moduleCode, Name moduleName, Set<Person> persons) {
        requireAllNonNull(moduleCode, moduleName, persons);
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.persons.addAll(persons);
    }

    public Code getModuleCode() {
        return moduleCode;
    }

    public Name getModuleName() {
        return moduleName;
    }

    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(persons);
    }

    /**
     * Returns true if both modules have the same code.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.moduleCode.equals(moduleCode);
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module m = (Module) other;
        return m.moduleCode.equals(moduleCode) && m.moduleName.equals(moduleName) && m.persons.equals(persons);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode);
    }

    @Override
    public String toString() {
        return moduleCode + " " + moduleName;
    }
}
