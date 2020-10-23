package chopchop.model;

import static java.util.Objects.requireNonNull;

import chopchop.model.attributes.Name;

public abstract class FoodEntry {
    protected final Name name;

    protected FoodEntry(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public abstract int hashCode();
}
