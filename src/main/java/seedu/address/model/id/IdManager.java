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
     * @param prefix The prefix of the id.
     */
    public IdManager(String prefix) {
        this.nextId = new Id(prefix, 0);
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
