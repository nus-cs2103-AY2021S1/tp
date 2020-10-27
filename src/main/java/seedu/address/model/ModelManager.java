package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.vendor.Vendor;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final List<MenuManager> menuManagers;
    private final OrderManager orderManager;

    private final UserPrefs userPrefs;
    private ObservableList<Food> filteredFoods;

    private boolean isSortedAsc = false;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.menuManagers = new ArrayList<>();
        this.orderManager = new OrderManager();

        this.userPrefs = new UserPrefs(userPrefs);
    }

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs, menuManager and orderManager.
     */
    public ModelManager(
            ReadOnlyAddressBook addressBook,
            ReadOnlyUserPrefs userPrefs,
            List<MenuManager> menuManagers,
            OrderManager orderManager
    ) {
        super();
        requireAllNonNull(addressBook, userPrefs, menuManagers, orderManager);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.menuManagers = menuManagers;
        this.orderManager = orderManager;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new ArrayList<>(), new OrderManager());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return addressBook.hasVendor(vendor);
    }

    @Override
    public void selectVendor(int vendorIndex) {
        this.addressBook.selectVendor(vendorIndex);
        if (vendorIndex != -1) {
            this.filteredFoods = new FilteredList<>(this.menuManagers.get(vendorIndex).getFoodList());
        }
    }

    @Override
    public int getVendorIndex() {
        return this.addressBook.getVendorIndex();
    }

    @Override
    public boolean isSelected() {
        return this.addressBook.getVendorIndex() != -1;
    }

    @Override
    public ObservableList<Vendor> getObservableVendorList() {
        return addressBook.getVendorList();
    }
    //=========== MenuManager ================================================================================

    @Override
    public void setMenuManager(ReadOnlyMenuManager menuManager, int index) {
        this.menuManagers.get(index).resetData(menuManager);
    }

    @Override
    public ReadOnlyMenuManager getMenuManager(int index) {
        return menuManagers.get(index);
    }

    @Override
    public void sortFoodBy(String sortedBy, boolean ascending, boolean toggle) {
        int index = getVendorIndex();
        if (index < 0 || index >= menuManagers.size()) {
            return;
        }
        // not suppose to modify menumanager's menus
        Comparator<Food> foodComparator;
        switch (sortedBy) {
        case SortCommand.NAME:
            foodComparator = Comparator.comparing(Food::getName);
            break;
        case SortCommand.PRICE:
            foodComparator = Comparator.comparing(Food::getPrice);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + sortedBy);
        }

        filteredFoods = filteredFoods.sorted(foodComparator);
        if ((toggle && isSortedAsc) || (!toggle && !ascending)) {
            filteredFoods = filteredFoods.sorted(foodComparator.reversed());
        }
        isSortedAsc = toggle ? !isSortedAsc : ascending;
    }

    /**
     * Shows the current menu at the default state.
     */
    public void showDefaultMenu() {
        filteredFoods = new FilteredList<>(menuManagers.get(getVendorIndex()).getFoodList());
        isSortedAsc = false;
    }


    //=========== OrderManager ================================================================================

    @Override
    public void setOrderManager(ReadOnlyOrderManager orderManager) {
        this.orderManager.resetData(orderManager);
    }

    @Override
    public void setOrder(List<OrderItem> orderItems) {
        this.orderManager.setOrder(orderItems);
    }

    @Override
    public ReadOnlyOrderManager getOrderManager() {
        return orderManager;
    }

    @Override
    public boolean hasOrderItem(OrderItem orderItem) {
        requireNonNull(orderItem);
        return orderManager.hasOrderItem(orderItem);
    }

    @Override
    public void removeOrderItem(OrderItem target) {
        requireNonNull(target);
        orderManager.removeOrderItem(target);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        requireNonNull(orderItem);
        orderManager.addOrderItem(orderItem);
    }

    @Override
    public int getOrderHistorySize() {
        return orderManager.getOrderHistorySize();
    }

    @Override
    public int getOrderItemQuantity(int index) {
        return orderManager.getQuantity(index);
    }

    @Override
    public void undoOrder() {
        orderManager.undoChanges();
    }

    @Override
    public void clearOrder() {
        orderManager.setOrder(new ArrayList<>());
    }

    @Override
    public void resetOrder() {
        orderManager.resetOrder();
    }

    @Override
    public int getOrderSize() {
        return orderManager.getOrderItemList().size();
    }

    @Override
    public ObservableList<OrderItem> getObservableOrderItemList() {
        return orderManager.getOrderItemList();
    }

    //=========== Filtered Food List Accessors =============================================================

    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoods;
    }

    @Override
    public int getFilteredFoodListSize() {
        return filteredFoods == null ? 0 : getFilteredFoodList().size();
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        int index = getVendorIndex();
        if (index < 0 || index >= menuManagers.size()) {
            return;
        }
        // not suppose to modify menumanager's menus
        filteredFoods = filteredFoods.filtered(predicate);
    }

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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs);
    }

}
