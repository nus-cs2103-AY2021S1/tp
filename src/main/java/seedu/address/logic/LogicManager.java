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
import seedu.address.logic.parser.TrackPadParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackPadParser trackPadParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        trackPadParser = new TrackPadParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = trackPadParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAttractionList(model.getAttractionList());
            storage.saveItineraryList(model.getItineraryList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    //=========== Attraction List ================================================================================

    @Override
    public ReadOnlyAttractionList getAttractionList() {
        return model.getAttractionList();
    }

    @Override
    public ObservableList<Attraction> getFilteredAttractionList() {
        return model.getFilteredAttractionList();
    }

    @Override
    public Path getAttractionListFilePath() {
        return model.getAttractionListFilePath();
    }

    //=========== Itinerary List ================================================================================

    @Override
    public ReadOnlyItineraryList getItineraryList() {
        return model.getItineraryList();
    }

    @Override
    public ObservableList<Itinerary> getFilteredItineraryList() {
        return model.getFilteredItineraryList();
    }

    @Override
    public Path getItineraryListFilePath() {
        return model.getItineraryListFilePath();
    }

    //=========== Itinerary Attraction List ===========================================================================

    @Override
    public ReadOnlyItineraryAttractionList getItineraryAttractionList() {
        return model.getItineraryAttractionList();
    }

    @Override
    public ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList() {
        return model.getFilteredItineraryAttractionList();
    }
    //=========== GUI Settings ==================================================================================

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
