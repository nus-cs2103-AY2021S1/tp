package seedu.address.model;

import javafx.collections.ObservableList;
<<<<<<< Updated upstream:src/main/java/seedu/address/model/ReadOnlyAddressBook.java
import seedu.address.model.person.Person;
=======
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.person.Person;
>>>>>>> Stashed changes:src/main/java/seedu/address/model/ReadOnlyMcGymmy.java

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyMcGymmy {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Food> getFoodList();

}
