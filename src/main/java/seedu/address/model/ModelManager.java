package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;

/**
 * Represents the in-memory model of the client list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClientList clientList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final BooleanProperty isArchiveMode;
    private final PolicyList policyList;

    /**
     * Initializes a ModelManager with the given clientList and userPrefs.
     */
    public ModelManager(ReadOnlyClientList clientList, ReadOnlyUserPrefs userPrefs, PolicyList policyList) {
        super();
        requireAllNonNull(clientList, userPrefs);

        logger.fine("Initializing with client list: " + clientList + " and user prefs " + userPrefs);

        this.clientList = new ClientList(clientList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.clientList.getPersonList());
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_ACTIVE); // initialised to show all active persons
        isArchiveMode = new SimpleBooleanProperty(false);
        this.policyList = policyList;
    }

    public ModelManager() {
        this(new ClientList(), new UserPrefs(), new PolicyList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getClientListFilePath() {
        return userPrefs.getClientListFilePath();
    }

    @Override
    public void setClientListFilePath(Path clientListFilePath) {
        requireNonNull(clientListFilePath);
        userPrefs.setClientListFilePath(clientListFilePath);
    }

    @Override
    public Path getPolicyListFilePath() {
        return userPrefs.getPolicyListFilePath();
    }

    @Override
    public void setPolicyListFilePath(Path policyListFilePath) {
        requireAllNonNull(policyListFilePath);
        userPrefs.setPolicyListFilePath(policyListFilePath);
    }

    //=========== ClientList ================================================================================

    @Override
    public void setClientList(ReadOnlyClientList clientList) {
        this.clientList.resetData(clientList);
    }

    @Override
    public ReadOnlyClientList getClientList() {
        return clientList;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return clientList.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        clientList.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        clientList.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        clientList.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedClientList}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Archive Mode =============================================================

    @Override
    public boolean getIsArchiveMode() {
        return isArchiveMode.get();
    }

    @Override
    public BooleanProperty getIsArchiveModeProperty() {
        return isArchiveMode;
    }

    @Override
    public void setIsArchiveMode(boolean isArchiveMode) {
        this.isArchiveMode.set(isArchiveMode);
    }

    //=========== PolicyList ================================================================================

    @Override
    public PolicyList getPolicyList() {
        return policyList;
    }

    @Override
    public void addPolicy(Policy policy) {
        policyList.add(policy);
    }

    @Override
    public boolean hasPolicy(Policy policy) {
        return policyList.contains(policy);
    }

    @Override
    public boolean hasPolicy(PolicyName policyName) {
        return policyList.contains(policyName);
    }

    @Override
    public void clearPolicyList() {
        clientList.clearPolicy();
        policyList.clear();
    }

    //=========== Equals =============================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }
        // state check
        ModelManager other = (ModelManager) obj;
        return clientList.equals(other.clientList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && policyList.equals(other.policyList)
                && (isArchiveMode.get() == other.isArchiveMode.get());
    }
}
