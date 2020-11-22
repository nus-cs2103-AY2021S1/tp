package seedu.forgetfulnus.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.logic.commands.Command;
import seedu.forgetfulnus.logic.commands.CommandResult;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.logic.parser.GlossaryParser;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final GlossaryParser glossaryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        glossaryParser = new GlossaryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, ParseZeroException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = glossaryParser.parseCommand(commandText);
        commandResult = command.executeWithChecks(model);

        try {
            storage.saveGlossary(model.getGlossary());
            storage.saveScores(model.getScoreList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyGlossary getGlossary() {
        return model.getGlossary();
    }

    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return model.getFilteredFlashCardList();
    }

    @Override
    public Path getGlossaryFilePath() {
        return model.getGlossaryFilePath();
    }

    /**
     * Returns the user prefs' scores file path
     */
    @Override
    public Path getScoresFilePath() {
        return model.getScoreFilePath();
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
