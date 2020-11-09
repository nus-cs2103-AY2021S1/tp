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
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.property.Property;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveBidBook(model.getBidBook());
            storage.saveBidderAddressBook(model.getBidderAddressBook());
            storage.saveSellerAddressBook(model.getSellerAddressBook());
            storage.saveMeetingBook(model.getMeetingBook());
            storage.savePropertyBook(model.getPropertyBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    // ===================== BID =====================

    @Override
    public ReadOnlyBidBook getBidBook() {
        return model.getBidBook();
    }

    @Override
    public ObservableList<Bid> getFilteredBidList() {
        return model.getFilteredBidList();
    }

    // ===================== BIDDER =====================

    @Override
    public ReadOnlyBidderAddressBook getBidderAddressBook() {
        return model.getBidderAddressBook();
    }

    @Override
    public ObservableList<Bidder> getFilteredBidderList() {
        return model.getFilteredBidderList();
    }

    @Override
    public Path getBidderAddressBookFilePath() {
        return model.getBidderAddressBookFilePath();
    }


    // ===================== SELLER =====================

    @Override
    public ReadOnlySellerAddressBook getSellerAddressBook() {
        return model.getSellerAddressBook();
    }

    @Override
    public ObservableList<Seller> getFilteredSellerList() {
        return model.getFilteredSellerList();
    }

    @Override
    public Path getSellerAddressBookFilePath() {
        return model.getSellerAddressBookFilePath();
    }

    // ===================== MEETING =====================

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return model.getMeetingBook();
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    // ===================== PROPERTY =====================

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return model.getPropertyBook();
    }

    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return model.getFilteredPropertyList();
    }

}
