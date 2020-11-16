package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.UniqueConsultationList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Tasker implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueConsultationList consults;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        consults = new UniqueConsultationList();
    }

    public Tasker() {}

    /**
     * Creates an TAsker using the Persons in the {@code toBeCopied}
     */
    public Tasker(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the consultation list with {@code consults}.
     * {@code consults} must not contain duplicate consults.
     */
    public void setConsults(List<Consultation> consults) {
        this.consults.setConsultations(consults);
    }

    /**
     * Resets the existing data of this {@code Tasker} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setConsults(newData.getConsultationList());
    }
    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Tasker}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }
    //// consult-level operations
    /**
     * Returns true if a consultation with the same identity as {@code consultation} exists in the address book.
     */
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consults.contains(consultation);
    }

    /**
     * Returns true if a personal consultation conflicts with other {@code consultation} at the same time and date.
     */
    public boolean hasConflictingPersonalConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return consults.hasConflictingPersonalConsult(consultation);
    }

    /**
     * Adds a consultation to the address book.
     * The consultation must not already exist in the address book.
     */
    public void addConsultation(Consultation c) {
        consults.add(c);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setConsult(Consultation target, Consultation editedPerson) {
        requireNonNull(editedPerson);

        consults.setConsultation(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Tasker}.
     * {@code key} must exist in the address book.
     */
    public void removeConsultation(Consultation key) {
        consults.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Consultation> getConsultationList() {
        return consults.asUnmodifiableObservableList();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tasker // instanceof handles nulls
                && persons.equals(((Tasker) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
