package seedu.internhunter.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 's/' in 'edit app s/applied'.
 */
public class Prefix {

    private final String prefix;

    /**
     * Initializes the prefix object.
     *
     * @param prefix Prefix given as the input.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves the prefix.
     *
     * @return Prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns the string representation of this Prefix object.
     *
     * @return String representation of this Prefix object.
     */
    public String toString() {
        return getPrefix();
    }

    /**
     * Returns the hashcode of this Prefix object, which is the hashcode of the prefix if the prefix is not null,
     * and 0 if prefix is null.
     *
     * @return Hashcode of this Prefix object.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Returns true if both Prefixes have the same prefix.
     *
     * @param obj Other object to compare to.
     * @return True if the other Prefix object has the same prefix as this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
