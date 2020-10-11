package com.eva.model;

import com.eva.model.person.Person;

import com.eva.model.person.staff.Staff;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an Eva database
 */
public interface ReadOnlyEvaDatabase {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
