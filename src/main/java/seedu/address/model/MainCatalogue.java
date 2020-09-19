package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MainCatalogue implements ReadOnlyAddressBook {

    private final UniqueProjectList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueProjectList();
    }

    public MainCatalogue() {}

    /**
     * Creates an MainCatalogue using the Persons in the {@code toBeCopied}
     */
    public MainCatalogue(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setPersons(List<Project> projects) {
        this.persons.setPersons(projects);
    }

    /**
     * Resets the existing data of this {@code MainCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// project-level operations

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    public boolean hasPerson(Project project) {
        requireNonNull(project);
        return persons.contains(project);
    }

    /**
     * Adds a project to the address book.
     * The project must not already exist in the address book.
     */
    public void addPerson(Project p) {
        persons.add(p);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the address book.
     * The project identity of {@code editedProject} must not be the same as another existing project in the address book.
     */
    public void setPerson(Project target, Project editedProject) {
        requireNonNull(editedProject);

        persons.setPerson(target, editedProject);
    }

    /**
     * Removes {@code key} from this {@code MainCatalogue}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Project key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Project> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MainCatalogue // instanceof handles nulls
                && persons.equals(((MainCatalogue) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
