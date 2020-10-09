package seedu.address.model.id;

/**
 * Represents an Id management system that gives the next Id for a list.
 */
public class IdManager {

    /** Next Id to be allocated. */
    private Id nextId;

    /**
     * Constructs the IdManager object.
     *
     * @param initialId The last id allocated.
     */
    public IdManager(Id initialId) {
        this.nextId = initialId.increment();
    }

    /**
     * Returns an IdManager that starts incrementing id from 0.
     *
     * @param prefix The prefix of the ids.
     * @return The IdManager whose id starts from 0.
     */
    public static IdManager initialize(String prefix) {
        return new IdManager(new Id(prefix, 0));
    }

    /**
     * Gets the next Id.
     *
     * @return The next Id.
     */
    public Id getNextId() {
        Id currentId = nextId;
        nextId = nextId.increment();
        return currentId;
    }

}
