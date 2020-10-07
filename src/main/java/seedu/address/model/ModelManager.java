package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final List<MenuManager> menuManagers;
    private final OrderManager orderManager;

    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private FilteredList<Food> filteredFoods;
    private FilteredList<OrderItem> filteredOrder;
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
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

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
        requireAllNonNull(addressBook, userPrefs, menuManagers);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.menuManagers = menuManagers;
        this.orderManager = orderManager;

        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
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

    @Override
    public Path getMenuManagerFolderPath() {
        return userPrefs.getMenuManagerFolderPath();
    }

    @Override
    public void setMenuManagerFolderPath(Path menuManagerFolderPath) {
        requireNonNull(menuManagerFolderPath);
        userPrefs.setAddressBookFilePath(menuManagerFolderPath);
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setMenuManager(ReadOnlyMenuManager menuManager, int index) {
        this.menuManagers.get(index).resetData(menuManager);
    }

    @Override
    public ReadOnlyMenuManager getMenuManager(int index) {
        return menuManagers.get(index);
    }

    @Override
    public boolean hasFood(Food food, int index) {
        requireNonNull(food);
        return menuManagers.get(index).hasFood(food);
    }

    @Override
    public void deleteFood(Food target, int index) {
        menuManagers.get(index).removeFood(target);
    }

    @Override
    public void addFood(Food food, int index) {
        menuManagers.get(index).addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS, index);
    }

    @Override
    public void setFood(Food target, Food editedFood, int index) {
        requireAllNonNull(target, editedFood);

        menuManagers.get(index).setFood(target, editedFood);
    }

    @Override
    public void setOrderManager(ReadOnlyOrderManager orderManager, int index) {
        this.orderManager.resetData(orderManager);
    }

    @Override
    public ReadOnlyOrderManager getOrderManager(int index) {
        return orderManager;
    }

    @Override
    public boolean hasOrderItem(OrderItem orderItem, int index) {
        requireNonNull(orderItem);
        return orderManager.hasOrderItem(orderItem);
    }

    @Override
    public void deleteOrderItem(OrderItem target, int index) {
        orderManager.removeOrderItem(target);
    }

    @Override
    public void addOrderItem(OrderItem orderItem, int index) {
        orderManager.addOrderItem(orderItem);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERITEMS, index);
    }

    @Override
    public void setOrderItem(OrderItem target, OrderItem editedOrderItem, int index) {
        requireAllNonNull(target, editedOrderItem);
        orderManager.setOrderItem(target, editedOrderItem);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    @Override
    public ObservableList<Food> getFilteredFoodList(int index) {
        if (filteredFoods == null) {
            updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS, index);
        }
        return filteredFoods;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate, int index) {
        filteredFoods = new FilteredList<>(this.menuManagers.get(index).getFoodList());;
    }

    @Override
    public ObservableList<OrderItem> getFilteredOrderList(int index) {
        if (filteredOrder == null) {
            updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERITEMS, index);
        }
        return filteredOrder;
    }

    @Override
    public void updateFilteredOrderList(Predicate<OrderItem> predicate, int index) {
        filteredOrder = new FilteredList<>(this.orderManager.getOrderItemList());;
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
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
