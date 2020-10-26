package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.IsArchivePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that filters active persons */
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE = new IsArchivePredicate().negate();

    /** {@code Predicate} that filters archived persons */
    Predicate<Person> PREDICATE_SHOW_ALL_ARCHIVE = new IsArchivePredicate();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' client list file path.
     */
    Path getClientListFilePath();

    /**
     * Sets the user prefs' client list file path.
     */
    void setClientListFilePath(Path clientListFilePath);

    /**
     * Replaces client list data with the data in {@code clientList}.
     */
    void setClientList(ReadOnlyClientList clientList);

    /** Returns the ClientList */
    ReadOnlyClientList getClientList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the client list.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the client list.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the client list.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the client list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the client list.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns the archive mode.
     * @return Archive mode.
     */
    BooleanProperty getIsArchiveModeProperty();

    /**
     * Returns the boolean value of the archive mode.
     * @return Boolean value of archive mode.
     */
    boolean getIsArchiveMode();

    /**
     * Sets the archive mode.
     * @param isArchiveMode Archive mode to be set.
     */
    void setIsArchiveMode(boolean isArchiveMode);

    /**
     * Returns the user prefs' policy list file path.
     */
    Path getPolicyListFilePath();

    /**
     * Sets the user prefs' policy list file path.
     */
    void setPolicyListFilePath(Path policyListFilePath);

    /** Returns the PolicyList */
    PolicyList getPolicyList();

    /**
     * Adds the given policy.
     * {@code policy} must not already exist in the client list.
     */
    void addPolicy(Policy policy);

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in the policy list.
     */
    boolean hasPolicy(Policy policy);

    /**
     * Returns true if a policy with the same policy name as {@code policy} exists in the policy list.
     */
    boolean hasPolicy(PolicyName policyName);

    /**
     * Clear policy list data.
     * Additionally, clears all policy field in client list.
     */
    void clearPolicyList();
}
