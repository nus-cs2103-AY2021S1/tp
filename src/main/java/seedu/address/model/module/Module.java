package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Module in the Cap5.0Buddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final Name name;

    // Data fields
    private final ZoomLink zoomLink;

    /**
     * Every field must be present and not null.
     */
    public Module(Name name, ZoomLink zoomLink) {
        requireAllNonNull(name, zoomLink);
        this.name = name;
        this.zoomLink = zoomLink;
    }

    public Name getName() {
        return name;
    }

    public ZoomLink getZoomLink() {
        return zoomLink;
    }

    /**
     * Returns true if both Modules of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getName().equals(getName());
    }

    /**
     * Returns true if both Modules have the same identity and data fields.
     * This defines a stronger notion of equality between two Modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getName().equals(getName())
                && otherModule.getZoomLink().equals(getZoomLink());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, zoomLink);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Zoom Link: ")
                .append(getZoomLink());
        return builder.toString();
    }

}
