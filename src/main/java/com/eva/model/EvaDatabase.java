package com.eva.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.model.person.Person;
import com.eva.model.person.UniquePersonList;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EvaDatabase<P extends Person> implements ReadOnlyEvaDatabase<P> {

    private final UniquePersonList<P> persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList<>();
    }

    public EvaDatabase() {}

    /**
     * Creates an EvaDatabase using the Persons in the {@code toBeCopied}
     */
    public EvaDatabase(ReadOnlyEvaDatabase<P> toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<P> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code EvaDatabase} with {@code newData}.
     */
    public void resetData(ReadOnlyEvaDatabase<P> newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the eva database.
     */
    public boolean hasPerson(P person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the eva database.
     * The person must not already exist in the eva database.
     */
    public void addPerson(P p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the eva database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the eva database.
     */
    public void setPerson(P target, P editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code EvaDatabase}.
     * {@code key} must exist in the eva database.
     */
    public void removePerson(P key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<P> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EvaDatabase // instanceof handles nulls
                && persons.equals(((EvaDatabase<?>) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
