package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ItemListParser;
import seedu.address.logic.parser.exceptions.ParseException;
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
public class ItemLogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(ItemLogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ItemListParser itemListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public ItemLogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        itemListParser = new ItemListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = itemListParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveItemList(model.getItemList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        logger.warning("Asking for getAddressBook in item list");
        return new AddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        logger.warning("Asking for getFilteredPersonList in item list");
        return null;
    }

    @Override
    public Path getAddressBookFilePath() {
        logger.warning("Asking for getAddressBookFilePath in item list");
        return model.getItemListFilePath();
    }


    @Override
    public ReadOnlyItemList getItemList() {
        return model.getItemList();
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return model.getFilteredItemList();
    }

    @Override
    public Path getItemListFilePath() {
        return model.getItemListFilePath();
    }

    @Override
    public ReadOnlyLocationList getLocationList() {
        logger.warning("Asking for getLocationList in item list");
        return null;
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        logger.warning("Asking for getFilteredLocationList in item list");
        return null;
    }

    @Override
    public Path getLocationListFilePath() {
        logger.warning("Asking for getLocationListFilePath in item list");
        return null;
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
