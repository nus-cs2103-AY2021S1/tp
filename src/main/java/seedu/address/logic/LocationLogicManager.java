package seedu.address.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LocationLogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LocationLogicManager.class);

    private final Model model;
    private final Storage storage;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LocationLogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }


    @Override
    public CommandResult execute(String commandText) {
        return null;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        logger.warning("Asking for getAddressBook in location list");
        return new AddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        logger.warning("Asking for getFilteredPersonList in location list");
        return null;
    }

    @Override
    public Path getAddressBookFilePath() {
        logger.warning("Asking for getAddressBookFilePath in location list");
        return model.getLocationListFilePath();
    }

    @Override
    public ReadOnlyItemList getItemList() {
        logger.warning("Asking for getItemList in location list");
        return null;
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        logger.warning("Asking for getFilteredItemList in location list");
        return null;
    }

    @Override
    public Path getItemListFilePath() {
        logger.warning("Asking for getItemListFilePath in location list");
        return null;
    }

    @Override
    public ReadOnlyLocationList getLocationList() {
        return model.getLocationList();
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        return model.getFilteredLocationList();
    }

    @Override
    public Path getLocationListFilePath() {
        return model.getLocationListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
