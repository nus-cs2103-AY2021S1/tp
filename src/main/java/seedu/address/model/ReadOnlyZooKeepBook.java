package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.animal.Animal;

/**
 * Unmodifiable view of a ZooKeep book
 */
public interface ReadOnlyZooKeepBook {

    /**
     * Returns an unmodifiable view of the animals list.
     * This list will not contain any duplicate animals.
     */
    ObservableList<Animal> getAnimalList();
}
