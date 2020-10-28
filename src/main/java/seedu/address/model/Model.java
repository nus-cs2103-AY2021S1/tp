package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.food.Food;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.vendor.Vendor;


/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' menu manager file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces menu manager data with the data in {@code menuManager}.
     */

    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the address book.
     */
    boolean hasVendor(Vendor vendor);

    /**
     * Selects the vendor with index {@code vendorIndex} .
     * {@code vendorIndex} must be a valid index in the model.
     */
    void selectVendor(int vendorIndex);

    ObservableList<Vendor> getObservableVendorList();

    /**
     * Replaces address book data with the data in {@code menuManager}.
     */
    void setMenuManager(ReadOnlyMenuManager menuManager, int index);

    /**
     * Returns the MenuManager at the ith index
     */
    ReadOnlyMenuManager getMenuManager(int index);

    void sortFoodBy(String sortedBy, boolean ascending, boolean toggle);

    void showDefaultMenu();

    /**
     * Replaces address book data with the data in {@code orderManager}.
     */
    void setOrderManager(ReadOnlyOrderManager orderManager);

    /**
     * Returns the MenuManager at the ith index
     */
    ReadOnlyOrderManager getOrderManager();

    /**
     * Returns true if an orderItem with the same identity as {@code orderItem} exists in the address book.
     */
    boolean hasOrderItem(OrderItem orderItem);

    /**
     * Deletes the given orderItem.
     * The orderItem must exist in the order manager.
     */
    void removeOrderItem(OrderItem target);

    /**
     * Adds the given orderItem.
     * {@code orderItem} if orderItem exists in order manageradd to the current quantity.
     * @throws CommandException if after adding, there is more than 100 of the same order item
     */
    void addOrderItem(OrderItem orderItem) throws CommandException;

    /**
     * Returns an unmodifiable view of the filtered food list at the corresponding index
     */
    ObservableList<Food> getFilteredFoodList();

    int getFilteredFoodListSize();

    /**
     * Clears the order.
     */
    void clearOrder();

    /**
     * Clears the orderHistory.
     */
    void resetOrder();

    /**
     * Updates the filter of the filtered food list at the corresponding index to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);

    /**
     * Returns an unmodifiable view of the filtered orderItem list at the corresponding index
     */
    ObservableList<OrderItem> getObservableOrderItemList();

    int getOrderSize();

    int getVendorIndex();

    int getOrderHistorySize();

    int getOrderItemQuantity(int index);

    void undoOrder();

    boolean isSelected();

}
