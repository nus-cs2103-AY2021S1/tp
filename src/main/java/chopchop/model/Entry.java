package chopchop.model;

import static java.util.Objects.requireNonNull;

import chopchop.model.attributes.Name;

public abstract class Entry {
    protected final Name name;

    protected Entry(String name) {
        requireNonNull(name);
        this.name = new Name(name);
    }

    public String getName() {
        return this.name.toString();
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
