package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.LogicManagerForExercise.FILE_OPS_ERROR_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.storage.StorageForExercise;

public class ArchiveCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": To archive current Progress to a separate file.\n"
            + "Parameters: "
            + PREFIX_PATH + "File Location"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATH + "data\\archive";

    public static final String MESSAGE_SUCCESS = "File has been archived to the specified location";

    private final Path specifiedLocation;

    private StorageForExercise storage;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ArchiveCommand(Path fileLocation) {
        requireNonNull(fileLocation);
        this.specifiedLocation = fileLocation;
    }

    /**
     * Set the storage which contains the content of the apps.
     * @param storage The storage.
     */
    public void setStorage(StorageForExercise storage) {
        this.storage = storage;
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);

        try {
            this.storage.saveExerciseBook(model.getExerciseBook(), specifiedLocation);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && specifiedLocation.equals(((ArchiveCommand) other).specifiedLocation));
    }
}
