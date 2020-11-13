package seedu.bagel.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.logic.commands.Command;
import seedu.bagel.logic.commands.CommandResult;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.logic.parser.BagelParser;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.Model;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final BagelParser bagelParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        bagelParser = new BagelParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bagelParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveBagel(model.getBagel());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBagel getBagel() {
        return model.getBagel();
    }

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return model.getFilteredFlashcardList();
    }

    @Override
    public Path getBagelFilePath() {
        return model.getBagelFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public boolean hasSet() {
        return !model.getBagel().getSetOfFlashcardSets().get().isEmpty();
    }

    @Override
    public Set<FlashcardSet> getSets() {
        return model.getBagel().getSetOfFlashcardSets().get();
    }
}
