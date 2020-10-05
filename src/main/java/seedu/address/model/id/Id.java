package seedu.address.model.id;

/**
 * Represents an Id to uniquely identify elements in a list.
 */
public class Id {

    private final String prefix;
    private final int idNumber;

    /**
     * Constructs the Id with a prefix and id number.
     *
     * @param prefix The prefix representing the type of object.
     * @param idNumber The id number.
     */
    public Id(String prefix, int idNumber) {
        this.prefix = prefix;
        this.idNumber = idNumber;
    }

    /**
     * Gets the next id with the same prefix.
     *
     * @return The next id.
     */
    public Id increment() {
        return new Id(prefix, idNumber + 1);
    }

    @Override
    public String toString() {
        return prefix + idNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof Id
                && this.prefix.equals(((Id) obj).prefix)
                && this.idNumber == ((Id) obj).idNumber);
    }
}
