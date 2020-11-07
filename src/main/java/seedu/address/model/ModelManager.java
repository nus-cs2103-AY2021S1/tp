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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;


/**
 * Represents the in-memory model of the supper strikers data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VendorManager vendorManager;
    private final List<MenuManager> menuManagers;
    private final OrderManager orderManager;

    private final UserPrefs userPrefs;
    private ObservableList<MenuItem> filteredMenuItems;

    private boolean isSortedAsc = false;

    /**
     * Initializes a ModelManager with the given vendorManager, userPrefs, menuManager and orderManager.
     */
    public ModelManager(
            ReadOnlyVendorManager vendorManager,
            ReadOnlyUserPrefs userPrefs,
            List<MenuManager> menuManagers,
            OrderManager orderManager
    ) {
        super();
        requireAllNonNull(vendorManager, userPrefs, menuManagers, orderManager);

        logger.fine("Initializing with address book: " + vendorManager + " and user prefs " + userPrefs);

        this.vendorManager = new VendorManager(vendorManager);
        this.userPrefs = new UserPrefs(userPrefs);
        this.menuManagers = menuManagers;
        this.orderManager = orderManager;
    }

    public ModelManager() {
        this(new VendorManager(), new UserPrefs(), new ArrayList<>(), new OrderManager());
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
    public Path getVendorManagerFilePath() {
        return userPrefs.getVendorManagerFilePath();
    }

    @Override
    public void setVendorManagerFilePath(Path vendorManagerFilePath) {
        requireNonNull(vendorManagerFilePath);
        userPrefs.setVendorManagerFilePath(vendorManagerFilePath);
    }

    //=========== VendorManager ================================================================================

    @Override
    public void setVendorManager(ReadOnlyVendorManager vendorManager) {
        this.vendorManager.resetData(vendorManager);
    }

    @Override
    public ReadOnlyVendorManager getVendorManager() {
        return vendorManager;
    }

    @Override
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendorManager.hasVendor(vendor);
    }

    @Override
    public void selectVendor(int vendorIndex) {
        this.vendorManager.selectVendor(vendorIndex);
        if (vendorIndex != -1) {
            this.filteredMenuItems = new FilteredList<>(this.menuManagers.get(vendorIndex).getMenuItemList());
        }
    }

    @Override
    public int getVendorIndex() {
        return this.vendorManager.getVendorIndex();
    }

    @Override
    public boolean isSelected() {
        return this.vendorManager.getVendorIndex() != -1;
    }

    @Override
    public ObservableList<Vendor> getObservableVendorList() {
        return vendorManager.getVendorList();
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
    public void sortMenuItemBy(String sortedBy, boolean ascending, boolean toggle) {
        int index = getVendorIndex();
        if (index < 0 || index >= menuManagers.size()) {
            return;
        }
        // not suppose to modify menumanager's menus
        Comparator<MenuItem> itemComparator;
        switch (sortedBy) {
        case SortCommand.NAME:
            itemComparator = Comparator.comparing(MenuItem::getName);
            break;
        case SortCommand.PRICE:
            itemComparator = Comparator.comparing(MenuItem::getPrice);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + sortedBy);
        }

        filteredMenuItems = filteredMenuItems.sorted(itemComparator);
        if ((toggle && isSortedAsc) || (!toggle && !ascending)) {
            filteredMenuItems = filteredMenuItems.sorted(itemComparator.reversed());
        }
        isSortedAsc = toggle ? !isSortedAsc : ascending;
    }

    /**
     * Shows the current menu at the default state.
     */
    public void showDefaultMenu() {
        filteredMenuItems = new FilteredList<>(getMenuManager(getVendorIndex()).getMenuItemList());
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
    public void addOrderItem(OrderItem orderItem) throws CommandException {
        requireNonNull(orderItem);
        try {
            orderManager.addOrderItem(orderItem);
        } catch (CommandException e) {
            throw e;
        }
    }

    @Override
    public void tagOrderItem(OrderItem orderItem, Tag tag) {
        requireAllNonNull(orderItem, tag);
        orderManager.tagOrderItem(orderItem, tag);
    }

    @Override
    public void untagOrderItem(OrderItem orderItem) {
        requireNonNull(orderItem);
        orderManager.untagOrderItem(orderItem);
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
    public ObservableList<MenuItem> getFilteredMenuItemList() {
        return filteredMenuItems;
    }

    @Override
    public int getFilteredMenuItemListSize() {
        return filteredMenuItems == null ? 0 : getFilteredMenuItemList().size();
    }

    @Override
    public void updateFilteredMenuItemList(Predicate<MenuItem> predicate) {
        requireNonNull(predicate);
        int index = getVendorIndex();
        if (index < 0 || index >= menuManagers.size()) {
            return;
        }
        // not suppose to modify menumanager's menus
        filteredMenuItems = filteredMenuItems.filtered(predicate);
    }

    @Override
    public void updateFilteredMenuItemList(Comparator<MenuItem> comparator, boolean isSortedAsc) {
        requireNonNull(comparator);
        int index = getVendorIndex();
        if (index < 0 || index >= menuManagers.size()) {
            return;
        }
        // not suppose to modify menumanager's menus
        filteredMenuItems = filteredMenuItems.sorted(comparator);
        this.isSortedAsc = isSortedAsc;
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
        return vendorManager.equals(other.vendorManager)
                && userPrefs.equals(other.userPrefs)
                && menuManagers.equals(other.menuManagers)
                && orderManager.equals(other.orderManager)
                && filteredMenuItems.equals(other.filteredMenuItems)
                && isSortedAsc == other.isSortedAsc;
    }

}
