package seedu.address.model.deliverable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Represents the in-memory model of the deliverable book data
 */
public class ModelDeliverableManager implements ModelDeliverable {

    private static final Logger logger = LogsCenter.getLogger(ModelDeliverableManager.class);
    private final DeliverableBook deliverableBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Deliverable> filteredDeliverables;
    private Deliverable deliverableInView;

    /**
     * Initializes a ModelDeliverableManager with the given deliverableBook and userPrefs.
     */
    public ModelDeliverableManager(ReadOnlyDeliverableBook deliverableBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(deliverableBook, userPrefs);

        logger.fine("Initializing with deliverable book: " + deliverableBook + " and user prefs " + userPrefs);

        this.deliverableBook = new DeliverableBook(deliverableBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDeliverables = new FilteredList<>(this.deliverableBook.getDeliverableList());
    }

    public ModelDeliverableManager() {
        this(new DeliverableBook(), new UserPrefs());
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
    public Path getDeliverableBookFilePath() {
        return userPrefs.getDeliverableBookFilePath();
    }

    @Override
    public void setDeliverableBookFilePath(Path deliverableBookFilePath) {
        requireNonNull(deliverableBookFilePath);
        userPrefs.setDeliverableBookFilePath(deliverableBookFilePath);
    }

    //=========== DeliverableBook ================================================================================

    @Override
    public void setDeliverableBook(ReadOnlyDeliverableBook deliverableBook) {
        this.deliverableBook.resetData(deliverableBook);
        setDeliverableInView(null);
    }

    @Override
    public ReadOnlyDeliverableBook getDeliverableBook() {
        return deliverableBook;
    }

    @Override
    public ObservableList<Deliverable> getInternalDeliverableList() {
        return deliverableBook.getInternalDeliverableList();
    }

    @Override
    public boolean hasDeliverable(Deliverable deliverable) {
        requireNonNull(deliverable);
        return deliverableBook.hasDeliverable(deliverable);
    }

    @Override
    public void deleteDeliverable(Deliverable target) {
        if (target.isSameDeliverable(deliverableInView)) {
            setDeliverableInView(null);
        }
        deliverableBook.removeDeliverable(target);
    }

    @Override
    public void addDeliverable(Deliverable deliverable) {
        deliverableBook.addDeliverable(deliverable);
        updateFilteredDeliverableList(PREDICATE_SHOW_ALL_DELIVERABLES);
        setDeliverableInView(deliverable);
    }

    @Override
    public void setDeliverable(Deliverable target, Deliverable editedDeliverable) {
        requireAllNonNull(target, editedDeliverable);
        deliverableBook.setDeliverable(target, editedDeliverable);
        updateFilteredDeliverableList(PREDICATE_SHOW_ALL_DELIVERABLES);
        setDeliverableInView(editedDeliverable);
    }

    @Override
    public void updateDeliverableStatus(Deliverable target, Deliverable updatedDeliverable) {
        requireAllNonNull(target, updatedDeliverable);
        deliverableBook.setDeliverable(target, updatedDeliverable);
        setDeliverableInView(updatedDeliverable);
    }

    @Override
    public Deliverable getDeliverableInView() {
        return deliverableInView;
    }

    @Override
    public void setDeliverableInView(Deliverable deliverableInView) {
        this.deliverableInView = deliverableInView;
    }

    //=========== Filtered Deliverable List Accessors =============================================================

    @Override
    public ObservableList<Deliverable> getFilteredDeliverableList() {
        return filteredDeliverables;
    }

    @Override
    public void updateFilteredDeliverableList(Predicate<Deliverable> predicate) {
        requireNonNull(predicate);
        filteredDeliverables.setPredicate(predicate);
        setDeliverableInView(null);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelDeliverableManager)) {
            return false;
        }

        // state check
        ModelDeliverableManager other = (ModelDeliverableManager) obj;
        return deliverableBook.equals(other.deliverableBook)
                && userPrefs.equals(other.userPrefs)
                && filteredDeliverables.equals(other.filteredDeliverables);
    }
}
