package com.eva.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.model.person.Person;
import com.eva.model.person.UniquePersonList;

import com.eva.model.person.staff.Staff;
import com.eva.storage.EvaStorage;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EvaDatabase implements ReadOnlyEvaDatabase {

    private final UniquePersonList staff;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        staff = new UniquePersonList();
    }

    public EvaDatabase() {}

    /**
     * Creates an EvaDatabase using the Persons in the {@code toBeCopied}
     */
    public EvaDatabase(ReadOnlyEvaDatabase toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.staff.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code EvaDatabase} with {@code newData}.
     */
    public void resetData(ReadOnlyEvaDatabase newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return staff.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        staff.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        staff.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code EvaDatabase}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        staff.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return staff.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return staff.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EvaDatabase // instanceof handles nulls
                && staff.equals(((EvaDatabase) other).staff));
    }

    @Override
    public int hashCode() {
        return staff.hashCode();
    }
}
