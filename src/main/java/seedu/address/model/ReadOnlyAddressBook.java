package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    UniqueModuleList getModuleList();

    /**
     * Returns an unmodifiable view of the semester one modules list.
     * This list will not contain any duplicate modules.
     */
    UniqueModuleList getSemOneModuleList();

    /**
     * Returns an unmodifiable view of the semester two modules list.
     * This list will not contain any duplicate modules.
     */
    UniqueModuleList getSemTwoModuleList();


}
