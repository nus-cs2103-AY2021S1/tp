package seedu.address.model.deliverymodel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.UndoRedoLimitReachedException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.delivery.Delivery;

public class DeliveryModelManager implements DeliveryModel {
    private static final Logger logger = LogsCenter.getLogger(DeliveryModelManager.class);

    private final DeliveryBook deliveryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Delivery> filteredDeliveries;

    /**
     * Initializes a DeliveryModelManager with the given deliveryBook and userPrefs.
     */
    public DeliveryModelManager(ReadOnlyDeliveryBook deliveryBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(deliveryBook, userPrefs);

        logger.fine("Initializing with delivery book: " + deliveryBook + " and user prefs " + userPrefs);

        this.deliveryBook = new DeliveryBook(deliveryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDeliveries = new FilteredList<>(this.deliveryBook.getDeliveryList());
    }

    public DeliveryModelManager() {
        this(new DeliveryBook(), new UserPrefs());
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
    public Path getDeliveryBookFilePath() {
        return userPrefs.getDeliveryBookFilePath();
    }

    @Override
    public void setDeliveryBookFilePath(Path deliveryBookFilePath) {
        requireAllNonNull(deliveryBookFilePath);
        userPrefs.setDeliveryBookFilePath(deliveryBookFilePath);
    }

    //=========== DeliveryBook ================================================================================

    @Override
    public void setDeliveryBook(ReadOnlyDeliveryBook deliveryBook) {
        this.deliveryBook.resetData(deliveryBook);
    }

    @Override
    public ReadOnlyDeliveryBook getDeliveryBook() {
        return deliveryBook;
    }

    @Override
    public boolean hasDelivery(Delivery delivery) {
        requireNonNull(delivery);
        return deliveryBook.hasDelivery(delivery);
    }

    @Override
    public void deleteDelivery(Delivery target) {
        deliveryBook.removeDelivery(target);
    }

    @Override
    public void addDelivery(Delivery delivery) {
        deliveryBook.addDelivery(delivery);
        updateFilteredDeliveryList(PREDICATE_SHOW_ALL_DELIVERIES);
    }

    @Override
    public void setDelivery(Delivery target, Delivery editedDelivery) {
        requireAllNonNull(target, editedDelivery);
        deliveryBook.setDelivery(target, editedDelivery);
    }

    //=========== Filtered Delivery List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Delivery} backed by the internal list of
     * {@code versionedDeliveryBook}
     */
    @Override
    public ObservableList<Delivery> getFilteredDeliveryList() {
        return filteredDeliveries;
    }

    @Override
    public void updateFilteredDeliveryList(Predicate<Delivery> predicate) {
        requireNonNull(predicate);
        filteredDeliveries.setPredicate(predicate);
    }

    //=========== Redo/Undo ===============================================================================

    @Override
    public void commit() {
        deliveryBook.commit();
    }

    @Override
    public void undo() throws UndoRedoLimitReachedException {
        deliveryBook.undo();
    }

    @Override
    public void redo() throws UndoRedoLimitReachedException {
        deliveryBook.redo();
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof DeliveryModelManager)) {
            return false;
        }

        // state check
        DeliveryModelManager other = (DeliveryModelManager) obj;
        return deliveryBook.equals(other.deliveryBook)
                && userPrefs.equals(other.userPrefs)
                && filteredDeliveries.equals(other.filteredDeliveries);
    }
}
