package seedu.address.model.deliverymodel;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.inventorymodel.ReadOnlyInventoryBook;

/**
 * API of the DeliveryModel component
 */
public interface DeliveryModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Delivery> PREDICATE_SHOW_ALL_DELIVERIES = unused -> true;

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
     * Returns the user prefs' delivery book file path.
     */
    Path getDeliveryBookFilePath();

    /**
     * Sets the user prefs' delivery book file path.
     */
    void setDeliveryBookFilePath(Path deliveryBookFilePath);

    /**
     * Replaces delivery book data with the data in {@code deliveryBook}.
     */
    void setDeliveryBook(ReadOnlyDeliveryBook deliveryBook);

    /** Returns the DeliveryBook */
    ReadOnlyDeliveryBook getDeliveryBook();

    //================================ METHODS STARTS HERE ================================

    /**
     * Adds the given delivery.
     * {@code delivery} must not already exist in the inventory book.
     */
    void addDelivery(Delivery delivery);

    /**
     * Deletes the given delivery.
     * The delivery must exist in the inventory book.
     */
    void deleteDelivery(Delivery delivery);

    /**
     * Returns true if a delivery with the same identity as {@code delivery} exists in the inventory book.
     */
    boolean hasDelivery(Delivery delivery);

    /**
     * Replaces the given delivery {@code target} with {@code editedDelivery}.
     * {@code target} must exist in the inventory book.
     * The delivery identity of {@code editedDelivery} must not
     * be the same as another existing delivery in the inventory book.
     */
    void setDelivery(Delivery target, Delivery editedDelivery);

    /** Returns an unmodifiable view of the filtered delivery list */
    ObservableList<Delivery> getFilteredDeliveryList();

    /**
     * Updates the filter of the filtered delivery list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDeliveryList(Predicate<Delivery> predicate);
}
