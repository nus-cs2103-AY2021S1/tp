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
    private final Code code;

    // Data fields
    private final Name name;
    private final Set<Person> persons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Module(Code code, Name name, Set<Person> persons) {
        requireAllNonNull(code, name, persons);
        this.code = code;
        this.name = name;
        this.persons.addAll(persons);
    }

    public Code getModuleCode() {
        return code;
    }

    public Name getModuleName() {
        return name;
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
                && otherModule.code.equals(code);
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
        return m.code.equals(code) && m.name.equals(name) && m.persons.equals(persons);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code + " " + name;
    }
}
