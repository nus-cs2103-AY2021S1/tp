// ArgName.java

package chopchop.logic.parser;

/**
 * A helper class to abtract away the menial task of handling '/' when printing argument names
 */
public class ArgName {

    private final String name;

    /**
     * Constructs a new argument name from the given string. Note that it *should not* include
     * the leading slash.
     *
     * @param name the name of the argument.
     */
    public ArgName(String name) {
        assert name != null
            && !name.isEmpty()
            && !name.startsWith("/");

        this.name = name;
    }

    /**
     * Returns the un-decorated prefix name.
     */
    public String name() {
        return this.name;
    }

    /**
     * Returns the decorated (ie. with a slash) prefix name.
     */
    @Override
    public String toString() {
        return "/" + this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ArgName)
            && ((ArgName) obj).name.equals(this.name);
    }
}
