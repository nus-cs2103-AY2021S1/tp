package chopchop.model;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import chopchop.model.attributes.Name;

public abstract class FoodEntry {
    protected final Name name;

    protected FoodEntry(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public abstract int hashCode();
}
