package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ItemList itemList;
    private final FilteredList<Item> filteredItems;
    private final LocationList locationList;
    private final FilteredList<Location> filteredLocations;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.itemList = null;
        filteredItems = null;
        locationList = null;
        filteredLocations = null;
    }

    /**
     * Initializes a ModelManager with the given itemList and userPrefs.
     */
    public ModelManager(ReadOnlyItemList itemList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(itemList, userPrefs);

        logger.fine("Initializing with item list: " + itemList + " and user prefs " + userPrefs);

        this.itemList = new ItemList(itemList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.itemList.getItemList());
        addressBook = null;
        filteredPersons = null;
        locationList = null;
        filteredLocations = null;
    }

    /**
     * Initializes a ModelManager with the given locationList and userPrefs.
     */
    public ModelManager(ReadOnlyLocationList locationList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(locationList, userPrefs);

        logger.fine("Initializing with location list: " + locationList + " and user prefs " + userPrefs);

        this.locationList = new LocationList(locationList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredLocations = new FilteredList<>(this.locationList.getLocationList());
        addressBook = null;
        filteredPersons = null;
        itemList = null;
        filteredItems = null;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getItemListFilePath() {
        return userPrefs.getItemListFilePath();
    }

    @Override
    public Path getLocationListFilePath() {
        return null;
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setItemListFilePath(Path itemListFilePath) {
        requireNonNull(itemListFilePath);
        userPrefs.setItemListFilePath(itemListFilePath);
    }

    //=========== ItemList ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setItemList(ReadOnlyItemList itemList) {
        this.itemList.resetData(itemList);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyItemList getItemList() {
        return itemList;
    }

    @Override
    public ReadOnlyLocationList getLocationList() {
        return locationList;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return itemList.hasItem(item);
    }

    @Override
    public boolean hasLocation(Location location) {
        requireNonNull(location);
        return locationList.hasLocation(location);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteItem(Item target) {
        itemList.removeItem(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addItem(Item item) {
        itemList.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void addLocation(Location location) {
        locationList.addLocation(location);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        itemList.setItem(target, editedItem);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        return null;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLocationList(Predicate<Location> predicate) {
        requireNonNull(predicate);
        filteredLocations.setPredicate(predicate);
    }

    @Override
    public int findLocationID(Location toFind) {
        requireNonNull(toFind);
        return locationList.findLocationID(toFind);
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
