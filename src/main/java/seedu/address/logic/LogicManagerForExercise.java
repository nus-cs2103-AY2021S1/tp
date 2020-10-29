package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.CommandForExercise;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ExerciseBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.exercise.Exercise;
import seedu.address.storage.StorageForExercise;
import seedu.address.storage.StorageForGoal;

/**
 * The main LogicManager of the app.
 */
public class LogicManagerForExercise implements LogicForExercise {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final ExerciseModel model;
    private final StorageForExercise storage;
    private final StorageForGoal goalStorage;
    private final ExerciseBookParser exerciseBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManagerForExercise(ExerciseModel model, StorageForExercise storage, StorageForGoal goalStorage) {
        this.model = model;
        this.storage = storage;
        this.goalStorage = goalStorage;
        exerciseBookParser = new ExerciseBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult = null;
        CommandForExercise command = exerciseBookParser.parseCommand(commandText);

        if (command instanceof ArchiveCommand) {
            ArchiveCommand cmd = (ArchiveCommand) command;
            cmd.setStorage(storage);
        }
        
        commandResult = command.execute(model);

        try {
            storage.saveExerciseBook(model.getExerciseBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        try {
            goalStorage.saveGoalBook(model.getGoalBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    private void archiveMethod(Path path) throws CommandException {
        try {
            storage.saveExerciseBook(model.getExerciseBook(), path);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public ReadOnlyExerciseBook getExerciseBook() {
        return model.getExerciseBook();
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return model.getFilteredExerciseList();
    }

    @Override
    public Path getExerciseBookFilePath() {
        return model.getExerciseBookFilePath();
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

